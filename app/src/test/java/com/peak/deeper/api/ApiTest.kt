package com.peak.deeper.api

import com.peak.deeper.DispatcherRule
import com.peak.deeper.utils.api.DeeperApi
import com.peak.deeper.utils.converter.network.NetworkConverters
import com.peak.deeper.utils.request.LoginRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DeeperApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var deeperApi: DeeperApi

    private val mockLoginResponse = """{"login":{"appId":null,"token":"sZVBFabi7jtI1RV7qIOBrXdotIIXuDIq","userId":323252,"validated":true,"validTill":"2025-09-04T16:41:50.080+00:00","registrationDate":"2017-05-05T11:18:11.217Z"},"user":{"userId":323252,"familyName":"Angler","name":"Deeper","email":"deeperangler@gmail.com","locale":"en","subscribe":true,"image":null,"lastUsedDeeperModel":"Deeper PRO+","strictMode":false,"country":"LT"},"scans":[{"id":2434141,"lat":55.277287,"lon":21.328197,"name":"","date":null,"scanPoints":1,"mode":0},{"id":2434142,"lat":52.627087,"lon":4.755619,"name":"","date":null,"scanPoints":1,"mode":0},{"id":2434143,"lat":52.55296,"lon":4.671235,"name":"","date":null,"scanPoints":1,"mode":0},{"id":2434145,"lat":55.358562,"lon":21.20529,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434146,"lat":55.358696,"lon":21.205835,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434147,"lat":54.697796,"lon":25.308693,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434150,"lat":54.315037,"lon":13.099473,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434153,"lat":54.697918,"lon":25.30936,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434144,"lat":54.69697148755037,"lon":25.304665465284074,"name":"      ","date":null,"scanPoints":255,"mode":3},{"id":2434148,"lat":55.27731900639113,"lon":21.32837113164141,"name":"99361 Šilutė, Šilutė Klaipėdos apskritis Lietuva","date":null,"scanPoints":87,"mode":3},{"id":2434149,"lat":54.31500025100745,"lon":13.09968823629808,"name":"Hafenstraße 12B, 18439 Stralsund, Stralsund Mecklenburg-Vorpommern Deutschland","date":null,"scanPoints":1081,"mode":3},{"id":2434154,"lat":54.698243626420734,"lon":25.309630058319364,"name":"Antakalnio gatvė 26A, Antakalnio seniūnija, 10305 Vilnius, Antakalnio seniūnija Vilnius Vilniaus apskritis Lietuva","date":null,"scanPoints":228,"mode":3},{"id":2434155,"lat":55.061640000000004,"lon":25.51090666666667,"name":null,"date":null,"scanPoints":9,"mode":1},{"id":2434156,"lat":54.95333942847439,"lon":25.186487298833203,"name":null,"date":null,"scanPoints":7,"mode":1},{"id":2434157,"lat":54.953133,"lon":25.186644,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434158,"lat":54.697872,"lon":25.309715,"name":null,"date":null,"scanPoints":1,"mode":1},{"id":2434159,"lat":54.880822987764525,"lon":25.14147488213768,"name":null,"date":null,"scanPoints":12464,"mode":2},{"id":2434160,"lat":55.35895424680707,"lon":21.205069726437344,"name":null,"date":null,"scanPoints":157,"mode":3},{"id":2434161,"lat":55.46500106783616,"lon":-2.9581425797493974,"name":null,"date":null,"scanPoints":5669,"mode":2},{"id":2434162,"lat":54.698246,"lon":25.30962,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434163,"lat":55.3001,"lon":21.380169,"name":null,"date":null,"scanPoints":1,"mode":2},{"id":2434164,"lat":55.39921,"lon":21.27525,"name":null,"date":null,"scanPoints":1,"mode":0},{"id":2434165,"lat":43.43364826639745,"lon":-72.0160289319684,"name":null,"date":null,"scanPoints":12504,"mode":2},{"id":2434166,"lat":54.6981295,"lon":25.3096225,"name":null,"date":null,"scanPoints":2,"mode":1},{"id":2434167,"lat":55.343544436561004,"lon":21.290873856542365,"name":null,"date":null,"scanPoints":27,"mode":2},{"id":2434168,"lat":54.69795359377834,"lon":25.309173149298744,"name":null,"date":null,"scanPoints":122,"mode":2},{"id":2434169,"lat":54.7106882852364,"lon":25.337526862645944,"name":null,"date":null,"scanPoints":183,"mode":2},{"id":2434151,"lat":54.315023100351844,"lon":13.099648526804113,"name":null,"date":null,"scanPoints":598,"mode":3},{"id":2434152,"lat":54.31510028303117,"lon":13.099412946767735,"name":null,"date":null,"scanPoints":54,"mode":2}],"makara":{"url":"https://couch.staging.deeper.eu/u_323252","user":"323252","password":"sZVBFabi7jtI1RV7qIOBrXdotIIXuDIq"}}"""
    private val mockBathymetryResponse = """{"bathymetry":{"type":"FeatureCollection","bbox":[55.27720238095238,21.32789690731827,55.27755952380952,21.328740991453298],"features":[{"type":"Feature","properties":{"depth":1.666666666665,"id":"1725468997480-0"},"geometry":{"type":"Polygon","bbox":[55.27720238095238,21.32789690731827,55.27744047619048,21.328243417139852],"coordinates":[[[21.3281041,55.2774405,1.666666666665],[21.3281814,55.2773642,1.666666666665],[21.3282064,55.2773256,1.666666666665],[21.3282434,55.2772174,1.666666666665],[21.3282282,55.2772024,1.666666666665],[21.3280175,55.2772024,1.666666666665],[21.3278969,55.2773214,1.666666666665],[21.3280175,55.2774405,1.666666666665],[21.3281041,55.2774405,1.666666666665]]]}},{"type":"Feature","properties":{"depth":1.9999999999980003,"id":"1725468997480-1"},"geometry":{"type":"Polygon","bbox":[55.27720238095238,21.32810406349793,55.27744047619048,21.328384409206585],"coordinates":[[[21.3281041,55.2774405,1.9999999999980003],[21.3281814,55.2773642,1.9999999999980003],[21.3282064,55.2773256,1.9999999999980003],[21.3282434,55.2772174,1.9999999999980003],[21.3282282,55.2772024,1.9999999999980003],[21.3282587,55.2772024,1.9999999999980003],[21.3283844,55.27721,1.9999999999980003],[21.3283121,55.2772814,1.9999999999980003],[21.3282951,55.2773309,1.9999999999980003],[21.3282376,55.2774197,1.9999999999980003],[21.3282165,55.2774405,1.9999999999980003],[21.3281041,55.2774405,1.9999999999980003]]]}},{"type":"Feature","properties":{"depth":2.3333333333310002,"id":"1725468997480-2"},"geometry":{"type":"Polygon","bbox":[55.27720999487232,21.328216495617145,55.27749076326935,21.32856653039375],"coordinates":[[[21.3282165,55.2774405,2.3333333333310002],[21.3282376,55.2774197,2.3333333333310002],[21.3282951,55.2773309,2.3333333333310002],[21.3283121,55.2772814,2.3333333333310002],[21.3283844,55.27721,2.3333333333310002],[21.3284682,55.2772151,2.3333333333310002],[21.3285665,55.2772838,2.3333333333310002],[21.3285238,55.2773259,2.3333333333310002],[21.3283968,55.2773685,2.3333333333310002],[21.3283605,55.2774405,2.3333333333310002],[21.3283096,55.2774908,2.3333333333310002],[21.3282587,55.2774405,2.3333333333310002],[21.3282165,55.2774405,2.3333333333310002]]]}},{"type":"Feature","properties":{"depth":2.666666666664,"id":"1725468997480-3"},"geometry":{"type":"Polygon","bbox":[55.277283772910444,21.328309593492424,55.27755952380952,21.328740991453298],"coordinates":[[[21.3283605,55.2774405,2.666666666664],[21.3283968,55.2773685,2.666666666664],[21.3285238,55.2773259,2.666666666664],[21.3285665,55.2772838,2.666666666664],[21.3286204,55.2773214,2.666666666664],[21.328741,55.2774405,2.666666666664],[21.3286204,55.2775595,2.666666666664],[21.3283792,55.2775595,2.666666666664],[21.3283096,55.2774908,2.666666666664],[21.3283605,55.2774405,2.666666666664]]]}}]},"scansGeoData":[{"id":2434148,"mode":3,"coordinates":null,"polygons":[[[21.3280175,55.2774405],[21.3278969,55.2773214],[21.3280175,55.2772024],[21.3282587,55.2772024],[21.3284682,55.2772151],[21.3286204,55.2773214],[21.328741,55.2774405],[21.3286204,55.2775595],[21.3283792,55.2775595],[21.3282587,55.2774405],[21.3280175,55.2774405]]],"startLocation":[21.32846,55.277287],"size":"1391"}]}"""
    private val mockToken = "sZVBFabi7jtI1RV7qIOBrXdotIIXuDIq"
    private val mockUserId = 323252

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val baseUrl = mockWebServer.url("/") // Get the base URL of the mock server

        // Create a Retrofit instance using the mock server's base URL
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .add(NetworkConverters())
                        .build()
                )
            )
            .build()

        deeperApi = retrofit.create(DeeperApi::class.java)
    }

    @Test
    fun testPostLoginRequest_isSuccessful() = runTest {
        // Prepare a mock response
        val mockResponse = mockLoginResponse
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        // Make the API call
        val loginRequest = LoginRequest("username", "password")
        val response = deeperApi.postLoginRequest(loginRequest)

        // Assertions
        assertTrue(response.isSuccessful)
    }

    @Test
    fun testPostLoginRequest_getToken() = runTest {
        val mockResponse = mockLoginResponse
        val token = mockToken
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val loginRequest = LoginRequest("username", "password")
        val response = deeperApi.postLoginRequest(loginRequest)

        assertEquals(response.body()?.login?.token, token)
    }

    @Test
    fun testPostLoginRequest_getScans() = runTest {
        val mockResponse = mockLoginResponse

        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val loginRequest = LoginRequest("username", "password")
        val response = deeperApi.postLoginRequest(loginRequest)

        assertEquals(response.body()?.scans?.size, 29)
    }

    @Test
    fun testPostLoginRequest_getUserId() = runTest {
        val mockResponse = mockLoginResponse
        val userId = mockUserId
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val loginRequest = LoginRequest("username", "password")
        val response = deeperApi.postLoginRequest(loginRequest)

        assertEquals(response.body()?.user?.userId, userId)
    }

    @Test
    fun testGetGeoData_isSuccessful() = runTest {
        val mockResponse = mockBathymetryResponse
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val response = deeperApi.getGeoData(scanIds = "123", token = mockToken)

        assertTrue(response.isSuccessful)
    }

    @Test
    fun testGetGeoData_getFeatureSize() = runTest {
        val mockResponse = mockBathymetryResponse
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val response = deeperApi.getGeoData(scanIds = "27", token = mockToken)

        assertEquals(response.body()?.bathymetry?.features?.size, 4)
    }

    @Test
    fun testGetGeoData_getPolygonSize() = runTest {
        val mockResponse = mockBathymetryResponse
        mockWebServer.enqueue(MockResponse().setBody(mockResponse))

        val response = deeperApi.getGeoData(scanIds = "27", token = mockToken)

        assertEquals(
            response.body()?.bathymetry?.features?.first()?.geometry?.coordinates?.first()?.size,
            9
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
