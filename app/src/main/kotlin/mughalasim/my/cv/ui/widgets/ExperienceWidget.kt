package mughalasim.my.cv.ui.widgets

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
import cv.domain.entities.ExperienceEntity
import cv.domain.entities.getFakeExperience
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.line_thickness_medium
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.utils.AppPreview
import mughalasim.my.cv.ui.utils.toMonthYearString
import mughalasim.my.cv.ui.utils.toYearMonthDuration
import org.joda.time.DateTime

@Composable
fun ExperienceWidget(
    experiences: List<ExperienceEntity> = getFakeExperience(),
    onLinkTapped:(String) -> Unit,
) {
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
                            .width(line_thickness_medium)
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

                        TextRegularBold(entity.title)
                        TextSmall(entity.location)
                        TextSmall(entity.positionTitle)
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
                        LinksWidget(links, onLinkTapped)
                    }
                }
            }
        }
    }
}

@AppPreview
@Composable
fun ExperienceWidgetPreview() {
    AppThemeComposable {
        ExperienceWidget(){}
    }
}
