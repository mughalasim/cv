package mughalasim.my.cv.data.models

import org.joda.time.DateTime

data class ExperienceModel(
    val title: String,
    val start_date: String,
    val end_date: String,
    val position_title: String,
    val location: String,
    val description: String,
    val links: List<LinksModel>,
    val is_ongoing: Boolean
    ){
    constructor() : this("", "", "", "", "", "", listOf(), false) {}
}