package cv.domain.usecase

import cv.domain.Variables.EVENT_NAME_BANNER
import cv.domain.Variables.EVENT_NAME_LINK
import cv.domain.Variables.PARAM_BANNER_NAME
import cv.domain.Variables.PARAM_LINK
import cv.domain.repositories.AnalyticsRepository
import cv.domain.repositories.DataRepository
import cv.domain.repositories.LanguageRepository

class DataUseCase(
    private val dataRepository: DataRepository,
    private val languageRepository: LanguageRepository,
    private val analyticsRepository: AnalyticsRepository,
) {
    suspend fun getData() = dataRepository.getData()

    suspend fun getLanguage() = languageRepository.getLanguage()

    fun onBannerTapped(bannerName: String) {
        analyticsRepository.logEvent(
            EVENT_NAME_BANNER,
            listOf(Pair(PARAM_BANNER_NAME, bannerName)),
        )
    }

    fun onLinkTapped(url: String) {
        analyticsRepository.logEvent(
            EVENT_NAME_LINK,
            listOf(Pair(PARAM_LINK, url)),
        )
    }
}
