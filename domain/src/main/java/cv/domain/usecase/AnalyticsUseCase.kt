package cv.domain.usecase
import cv.domain.repositories.IAnalyticsRepository

class AnalyticsUseCase (
    private val analyticsRepository: IAnalyticsRepository
) {

    fun logEvent(eventName: String, params: List<Pair<String, String>>){
        analyticsRepository.logEvent(eventName, params)
    }

    companion object {
        const val EVENT_NAME_NAVIGATE = "NAVIGATE"
        const val EVENT_NAME_SETTING = "SETTING_TAPPED"

        const val PARAM_SCREEN_NAME = "SCREEN_NAME"

    }
}