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
        const val EVENT_NAME_NAVIGATE = "navigation"
        const val EVENT_NAME_SETTING = "settings_tapped"
        const val EVENT_NAME_BANNER = "banner_tapped"
        const val EVENT_NAME_LINK = "link_tapped"

        const val PARAM_SCREEN_NAME = "screen_name"
        const val PARAM_BANNER_NAME = "banner_name"
        const val PARAM_LINK = "link"
        const val PARAM_EXPAND_LIST_ON_START_UP = "expand_list_on_start_up"
    }

}