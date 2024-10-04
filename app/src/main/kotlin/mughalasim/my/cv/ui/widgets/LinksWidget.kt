package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun LinksWidget(
    links: List<LinkEntity>,
    onLinkTapped: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (links.isNotEmpty()) {
            TextSmall(text = stringResource(R.string.txt_links))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                repeat(links.size) { ChipWidget(entity = links[it], onLinkTapped = onLinkTapped) }
            }
        }
    }
}

@AppPreview
@Composable
fun LinksWidgetPreview() {
    AppThemeComposable {
        LinksWidget(getFakeLinks()){}
    }
}
