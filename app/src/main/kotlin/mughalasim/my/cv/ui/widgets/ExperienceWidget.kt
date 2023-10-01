package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ExperienceEntity
import cv.domain.entities.getFakeExperience
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.toMonthYearString
import mughalasim.my.cv.ui.utils.toYearMonthDuration

@Composable
fun ExperienceWidget(
    experiences: List<ExperienceEntity> = getFakeExperience()
) {
    Column(verticalArrangement = Arrangement.Top) {
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
                        stringResource(R.string.txt_present)
                    } else {
                        entity.start_date.toYearMonthDuration(entity.end_date)
                    }
                    TextSmall(text = timeSpentString)
                }
                TextRegular(text = entity.position_title)
                val timeString = if (entity.ongoing)
                    stringResource(R.string.txt_present) else entity.end_date.toMonthYearString()

                TextRegular(
                    modifier = Modifier.padding(bottom = padding_screen),
                    text = "${entity.start_date.toMonthYearString()} - ${timeString}, ${entity.location}"
                )
                TextRegular(
                    modifier = Modifier.padding(bottom = padding_screen),
                    text = entity.description
                )
            }
            LinksWidget(entity.links)
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ExperienceWidgetPreviewNight(){
    AppThemeComposable {
        ExperienceWidget()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ExperienceWidgetPreview(){
    AppThemeComposable {
        ExperienceWidget()
    }
}
