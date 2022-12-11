package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun BannerWidget(
    title: String,
    hasFilter: Boolean = false,
    sortAscending: Boolean = false,
    onFilterClicked: () -> Unit = {},
    isExpanded: Boolean,
    onExpandedClicked: () -> Unit = {},
){
    Spacer(modifier = Modifier.padding(top = padding_screen))
    Surface(
        modifier = Modifier,
        elevation = elevation,
        color = AppTheme.colors.secondary
    ) {
        Row(
            horizontalArrangement =  Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextRegular(
                text = title,
                color = AppTheme.colors.primary,
                modifier = Modifier.padding(padding_screen)
            )
            Spacer(modifier =Modifier.weight(1f))
            if (hasFilter){
               IconButton(onClick = { onFilterClicked() },
                   modifier = Modifier.padding(end = padding_screen)) {
                   Icon(
                       painter = painterResource(
                           id = if(sortAscending)
                               mughalasim.my.cv.R.drawable.ic_sort_ascending
                           else mughalasim.my.cv.R.drawable.ic_sort_descending),
                       contentDescription = null,
                       tint = AppTheme.colors.primary
                   )
               }
            }
            IconButton(onClick = { onExpandedClicked() },
                modifier = Modifier.padding(end = padding_screen)) {
                Icon(
                    painter = painterResource(
                        id = if(isExpanded)
                            mughalasim.my.cv.R.drawable.ic_expand_less
                        else mughalasim.my.cv.R.drawable.ic_expand_more),
                    contentDescription = null,
                    modifier = Modifier.padding(end = padding_screen),
                    tint = AppTheme.colors.primary
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBanners(){
    Column(verticalArrangement = Arrangement.Top) {
        BannerWidget("Banner", isExpanded = false)
        BannerWidget("Banner with filter 1", hasFilter = true, sortAscending = true, isExpanded = true)
        BannerWidget("Banner with filter 2", hasFilter = true, sortAscending = false, isExpanded = false)
    }
}