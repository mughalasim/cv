package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.icon_image
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun BannerWidget(
    title: String,
    hasFilter: Boolean = false,
    sortAscending: Boolean = false,
    isExpanded: Boolean = true,
    onFilterClicked: () -> Unit = {},
    onExpandedClicked: () -> Unit = {},
) {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(top = padding_screen, bottom = padding_screen_small)
            .clip(RoundedCornerShape(border_radius)),
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundTitleBar)
                .clickable { onExpandedClicked() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextRegularBold(
                modifier =
                Modifier
                    .padding(padding_screen_small),
                text = title,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (hasFilter) {
                IconButton(
                    modifier = Modifier.height(icon_image),
                    onClick = { onFilterClicked() },
                ) {
                    Icon(
                        painter =
                        painterResource(
                            id =
                            if (sortAscending) {
                                R.drawable.ic_sort_ascending
                            } else {
                                R.drawable.ic_sort_descending
                            },
                        ),
                        contentDescription = null,
                        tint = AppTheme.colors.black,
                    )
                }
            }
            Icon(
                modifier = Modifier.padding(end = padding_screen_small).height(icon_image),
                painter = painterResource(id = R.drawable.ic_down),
                tint = if (isExpanded) Color.Transparent else AppTheme.colors.backgroundScreen,
                contentDescription = "collapsible"
            )
        }
    }
}

@AppPreview
@Composable
fun BannerWidgetPreview() {
    AppThemeComposable {
        Column(verticalArrangement = Arrangement.Top) {
            BannerWidget("Banner")
            BannerWidget("Banner with filter 1", hasFilter = true, sortAscending = true)
            BannerWidget("Banner with filter 2", hasFilter = true, sortAscending = false)
        }
    }
}
