package com.peak.deeper.utils.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class FirebaseAnalyticsServiceImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : FirebaseAnalyticsService {
    private val bundle = Bundle()

    override fun onScanDisplayed(scanId: Int, userId: Int?) {
        bundle.apply {
            putString(FirebaseAnalytics.Param.LOCATION, scanId.toString())
            putString(FirebaseAnalytics.Param.ITEM_ID, userId.toString())
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
    }
}
