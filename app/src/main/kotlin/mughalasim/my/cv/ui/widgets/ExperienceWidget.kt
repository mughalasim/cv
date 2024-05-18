package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ExperienceEntity
import cv.domain.entities.getFakeExperience
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.utils.toMonthYearString
import mughalasim.my.cv.ui.utils.toYearMonthDuration
import org.joda.time.DateTime

@Composable
fun ExperienceWidget(experiences: List<ExperienceEntity> = getFakeExperience()) {
    Column(verticalArrangement = Arrangement.Top) {
        repeat(experiences.size) {
            val entity = experiences[it]
            Row(
                modifier =
                    Modifier
                        .padding(bottom = padding_screen_large)
                        .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier =
                        Modifier
                            .width(padding_screen_small)
                            .fillMaxHeight()
                            .background(color = AppTheme.colors.highLight),
                ) {}
                Column(
                    modifier =
                        Modifier
                            .padding(start = padding_screen_small),
                ) {
                    Column {
                        val timeSpentString =
                            if (entity.ongoing) {
                                DateTime.now().toYearMonthDuration(entity.startDate)
                            } else {
                                entity.startDate.toYearMonthDuration(entity.endDate)
                            }
                        val timeString =
                            if (entity.ongoing) {
                                stringResource(R.string.txt_present)
                            } else {
                                entity.endDate.toMonthYearString()
                            }

                        TextSmall(text = entity.title)
                        TextRegular(text = entity.location)
                        TextRegular(text = entity.positionTitle)
                        TextSmall(
                            modifier = Modifier.padding(bottom = padding_screen),
                            text = "${entity.startDate.toMonthYearString()} - $timeString ($timeSpentString)",
                        )
                        TextRegular(
                            modifier = Modifier.padding(bottom = padding_screen),
                            text = entity.description,
                        )
                    }
                    entity.links?.let { links ->
                        LinksWidget(links)
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ExperienceWidgetPreviewNight() {
    AppThemeComposable {
        ExperienceWidget()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun ExperienceWidgetPreview() {
    AppThemeComposable {
        ExperienceWidget()
    }
}
