package cv.domain.repositories

interface IAnalyticsRepository {
    fun logEvent(
        eventName: String,
        params: List<Pair<String, String>>,
    )
}
