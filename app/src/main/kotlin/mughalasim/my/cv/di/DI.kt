package mughalasim.my.cv.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import cv.data.repository.AnalyticsRepository
import cv.domain.repositories.IAnalyticsRepository
import mughalasim.my.cv.services.IServiceNavigation
import mughalasim.my.cv.services.ServiceNavigation

object DI {

    val analytics: IAnalyticsRepository by lazy {
        AnalyticsRepository(Firebase.analytics)
    }

    val serviceNavigation: IServiceNavigation by lazy {
        ServiceNavigation()
    }
}