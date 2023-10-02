package cv.data.repository

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import cv.domain.repositories.IAnalyticsRepository

class AnalyticsRepository(
    private val firebaseAnalytics: FirebaseAnalytics
) : IAnalyticsRepository {

    override fun logEvent(eventName: String, params: List<Pair<String, String>>) {
        val bundle = Bundle()
        for (param in params) {
            bundle.putString(param.first, param.second)
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    companion object {

    }

}