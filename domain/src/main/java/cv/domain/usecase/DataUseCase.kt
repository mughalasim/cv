package cv.domain.usecase

import cv.domain.Variables.EVENT_NAME_BANNER
import cv.domain.Variables.PARAM_BANNER_NAME
import cv.domain.repositories.IAnalyticsRepository
import cv.domain.repositories.IDataRepository
import cv.domain.repositories.ILanguageRepository

class DataUseCase(
    private val dataRepository: IDataRepository,
    private val languageRepository: ILanguageRepository,
    private val analyticsRepository: IAnalyticsRepository,
) {
    fun getData() = dataRepository.getDataFromFirebase()

    fun getLanguage() = languageRepository.getLanguageFromFirebase()

    fun onBannerTapped(bannerName: String) {
        analyticsRepository.logEvent(
            EVENT_NAME_BANNER,
            listOf(Pair(PARAM_BANNER_NAME, bannerName)),
        )
    }
}
