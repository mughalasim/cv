package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.theme.padding_screen_small

@Composable
fun BannerWidget(
    title: String,
    hasFilter: Boolean = false,
    sortAscending: Boolean = false,
    onFilterClicked: () -> Unit = {},
    onExpandedClicked: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding_screen_large, bottom = padding_screen_large)
            .clip(RoundedCornerShape(border_radius))
            .background(color = AppTheme.colors.highLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.highLight)
                .clip(RoundedCornerShape(border_radius))
                .clickable { onExpandedClicked() },
            horizontalArrangement =  Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextRegular(
                modifier = Modifier
                    .padding(padding_screen),
                text = title,
                color = AppTheme.colors.black
            )
            Spacer(modifier = Modifier.weight(1f))
            if (hasFilter){
               IconButton(
                   modifier = Modifier.padding(end = padding_screen_small),
                   onClick = { onFilterClicked() }
               ) {
                   Icon(
                       painter = painterResource(
                           id = if(sortAscending)
                               mughalasim.my.cv.R.drawable.ic_sort_ascending
                           else mughalasim.my.cv.R.drawable.ic_sort_descending),
                       contentDescription = null,
                       tint = AppTheme.colors.black,
                       modifier = Modifier.padding(padding_screen_small)
                   )
               }
            }
        }

    }
}


@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BannerWidgetPreviewNight(){
    AppThemeComposable {
        Column(verticalArrangement = Arrangement.Top) {
            BannerWidget("Banner")
            BannerWidget("Banner with filter 1", hasFilter = true, sortAscending = true)
            BannerWidget("Banner with filter 2", hasFilter = true, sortAscending = false)
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun BannerWidgetPreview(){
    AppThemeComposable {
        Column(verticalArrangement = Arrangement.Top) {
            BannerWidget("Banner")
            BannerWidget("Banner with filter 1", hasFilter = true, sortAscending = true)
            BannerWidget("Banner with filter 2", hasFilter = true, sortAscending = false)
        }
    }
}