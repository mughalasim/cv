package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ExperienceEntity
import cv.domain.entities.getFakeExperience
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.toMonthYearString
import mughalasim.my.cv.ui.utils.toYearMonthDuration

@Preview(showBackground = true)
@Composable
fun ExperienceWidget(
    experiences: List<ExperienceEntity> = getFakeExperience()
) {
    repeat(experiences.size) {
        val entity = experiences[it]
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding_screen, end = padding_screen)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextSmall(text = entity.title)
                val timeSpentString = if (entity.ongoing) {
                    "Present"
                } else {
                    entity.start_date.toYearMonthDuration(entity.end_date)
                }
                TextSmall(text = timeSpentString)
            }
            TextRegular(text = entity.position_title)
            val timeAndLocationString = if (entity.ongoing) {
                "${entity.start_date.toMonthYearString()} - Present, ${entity.location}"
            } else {
                "${entity.start_date.toMonthYearString()} - ${entity.end_date.toMonthYearString()}, ${entity.location}"
            }
            TextRegular(
                modifier = Modifier.padding(bottom = padding_screen),
                text = timeAndLocationString
            )
            TextRegular(
                modifier = Modifier.padding(bottom = padding_screen),
                text = entity.description
            )
        }
        LinksWidget(entity.links)
    }
}