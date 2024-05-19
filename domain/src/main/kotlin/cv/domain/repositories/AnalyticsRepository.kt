package cv.domain.repositories

interface AnalyticsRepository {
    fun logEvent(
        eventName: String,
        params: List<Pair<String, String>>,
    )
}
