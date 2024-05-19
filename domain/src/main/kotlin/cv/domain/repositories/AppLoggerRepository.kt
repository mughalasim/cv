package cv.domain.repositories

interface AppLoggerRepository {
    fun log(
        message: String,
        appLogLevel: AppLogLevel = AppLogLevel.DEBUG,
    )
}

enum class AppLogLevel {
    DEBUG,
    ERROR
}
