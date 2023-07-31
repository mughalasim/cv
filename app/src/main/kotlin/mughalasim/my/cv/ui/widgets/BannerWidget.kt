package mughalasim.my.cv.ui.widgets

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
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun BannerWidget(
    title: String,
    hasFilter: Boolean = false,
    sortAscending: Boolean = false,
    onFilterClicked: () -> Unit = {},
    onExpandedClicked: () -> Unit = {},
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = padding_chips,
                end = padding_chips,
                top = padding_screen,
                bottom = padding_screen
            )
            .clip(RoundedCornerShape(border_radius)),
        elevation = elevation,
        color = AppTheme.colors.backgroundBanner
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandedClicked() },
            horizontalArrangement =  Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextRegular(
                modifier = Modifier
                    .padding(start = padding_chips, end = padding_chips, top = padding_screen, bottom = padding_screen),
                text = title
            )
            Spacer(modifier = Modifier.weight(1f))
            if (hasFilter){
               IconButton(
                   modifier = Modifier.padding(end = padding_chips),
                   onClick = { onFilterClicked() }
               ) {
                   Icon(
                       painter = painterResource(
                           id = if(sortAscending)
                               mughalasim.my.cv.R.drawable.ic_sort_ascending
                           else mughalasim.my.cv.R.drawable.ic_sort_descending),
                       contentDescription = null,
                       tint = AppTheme.colors.textRegular,
                       modifier = Modifier.padding(padding_chips)
                   )
               }
            }
        }

    }
}


@Preview(showBackground = false)
@Composable
fun PreviewBanners(){
    Column(verticalArrangement = Arrangement.Top) {
        BannerWidget("Banner")
        BannerWidget("Banner with filter 1", hasFilter = true, sortAscending = true)
        BannerWidget("Banner with filter 2", hasFilter = true, sortAscending = false)
    }
}