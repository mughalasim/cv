package mughalasim.my.cv.data.models

import mughalasim.my.cv.utils.DateTimeExtensions.dateFormat
import org.joda.time.DateTime

data class ExperienceModel(
    val title: String,
    val start_date: String,
    val end_date: String,
    val position_title: String,
    val location: String,
    val description: String,
    val links: List<LinksModel>,
    val ongoing: Boolean = false
    ){
    constructor() : this("", "", "", "", "", "", listOf()) {}

    fun getStartDate(): DateTime {
        return DateTime.parse(start_date, dateFormat)
    }

    fun getEndDate(): DateTime {
        return DateTime.parse(end_date, dateFormat)
    }
}