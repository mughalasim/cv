package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun LinksWidget(
    links: List<LinkEntity> = getFakeLinks()
){
    Column(modifier = Modifier.fillMaxWidth()){
        if (links.isNotEmpty())
            TextSmall(
                modifier = Modifier.padding(start = padding_screen),
                text = stringResource(R.string.txt_links)
            )
        LazyRow {
            items(items = links) {
                Chip (entity = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Chip(
    entity: LinkEntity = getFakeLinks()[0],
    context: Context = LocalContext.current
) {
    Surface(
        modifier = Modifier.padding(padding_screen),
        elevation = elevation,
        shape = shapes.small,
        color = AppTheme.colors.secondary
    ) {
        Row (modifier = Modifier
            .toggleable(
                value = false,
                onValueChange = {
                    val url = entity.url
                    if (URLUtil.isValidUrl(url)) {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(browserIntent)
                    } else {
                        Toast.makeText(context, context.resources.getString(R.string.error_invalid_link), Toast.LENGTH_LONG).show()
                    }
                }
            )
        ) {
            TextRegular(text = entity.text,
                modifier = Modifier.padding(padding_chips)
            )
        }
    }
}