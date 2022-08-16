package cv.domain.entities

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

data class ExperienceEntity(
    val title: String,
    val start_date: String,
    val end_date: String,
    val position_title: String,
    val location: String,
    val description: String,
    val links: List<LinksEntity>,
    val ongoing: Boolean = false
    ){
    constructor() : this("", "", "", "", "", "", listOf()) {}

    fun getStartDate(): DateTime {
        return DateTime.parse(start_date, StaticValues.dateFormat)
    }

    fun getEndDate(): DateTime {
        return DateTime.parse(end_date, StaticValues.dateFormat)
    }
}

object StaticValues {
    val dateFormat: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
}
