package mughalasim.my.cv.data.models

import org.joda.time.DateTime

data class ExperienceModel(
    val title: String,
    val start_date: DateTime,
    val end_date: DateTime?,
    val position_title: String,
    val location: String,
    val description: String,
    val links: List<LinksModel>
    ){
    constructor() : this("", DateTime.now(), DateTime.now(), "", "", "", listOf()) {}
}