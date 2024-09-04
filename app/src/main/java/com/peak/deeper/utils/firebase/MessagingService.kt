package com.peak.deeper.utils.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("firebase", "Refreshed token: $token")
    }
}
