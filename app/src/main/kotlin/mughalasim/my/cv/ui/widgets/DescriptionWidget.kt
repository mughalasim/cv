package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cv.domain.entities.DescriptionEntity
import cv.domain.entities.getFakeDescription
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.line_thickness_medium
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun DescriptionWidget(
    entity: DescriptionEntity = getFakeDescription(),
    onLinkTapped: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Min),
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxHeight()
                .width(line_thickness_medium)
                .background(color = AppTheme.colors.highLight),
        ) {}
        Column(
            modifier = Modifier.padding(start = padding_screen_small),
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth(),
            ) {
                TextSmall(stringResource(R.string.txt_address))
                TextRegular(
                    modifier = Modifier.padding(bottom = padding_screen),
                    text = entity.address,
                )

                TextSmall(stringResource(R.string.txt_telephone))
                TextRegular(
                    modifier = Modifier.padding(bottom = padding_screen),
                    text = "+${entity.areaCode} ${entity.phone}",
                )

                TextSmall(stringResource(R.string.txt_email))
                TextRegular(
                    modifier = Modifier.padding(bottom = padding_screen),
                    text = entity.email,
                )
            }
            // Links
            LinksWidget(entity.links, onLinkTapped)
        }
    }
}

@AppPreview
@Composable
fun DescriptionWidgetPreview() {
    AppThemeComposable {
        DescriptionWidget()
    }
}
