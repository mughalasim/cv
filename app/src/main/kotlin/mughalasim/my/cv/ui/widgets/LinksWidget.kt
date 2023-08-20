package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LinksWidget(
    links: List<LinkEntity>
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = padding_screen, end = padding_screen, bottom = padding_screen)
    ) {
        if (links.isNotEmpty()){
            TextSmall(text = stringResource(R.string.txt_links))
            FlowRow (horizontalArrangement = Arrangement.Start){
                repeat(links.size){
                    ChipWidget (entity = links[it])
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
fun LinksWidgetPreviewNight(){
    AppThemeComposable {
        LinksWidget(getFakeLinks())
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun LinksWidgetPreview(){
    AppThemeComposable {
        LinksWidget(getFakeLinks())
    }
}