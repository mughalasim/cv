package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import cv.domain.entities.DescriptionEntity
import cv.domain.entities.getFakeDescription
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.theme.profile_picture_height
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun ImageAndNameWidget(
    descriptionEntity: DescriptionEntity = getFakeDescription(),
    scrollState: ScrollState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(profile_picture_height)
            .clip(RoundedCornerShape(border_radius))
    ) {
        ImageReloadWidget(descriptionEntity.profilePictureUrl, scrollState)

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundBottomNavigation.copy(alpha = 0.5f))
                .padding(padding_screen_small)
        ) {
            TextLarge(descriptionEntity.fullName)
            TextRegular(descriptionEntity.positionTitle)
        }
    }
}

@AppPreview
@Composable
fun ImageAndNameWidgetPreview() {
    AppThemeComposable {
        ImageAndNameWidget(
            getFakeDescription(),
            rememberScrollState()
        )
    }
}
