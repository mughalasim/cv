package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.line_thickness
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun LinksWidget(
    links: List<LinkEntity> = getFakeLinks()
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = padding_screen, end = padding_screen, bottom = padding_screen)
    ) {
        if (links.isNotEmpty()){
            TextSmall(text = stringResource(R.string.txt_links))
            FlowRow (horizontalArrangement = Arrangement.Start){
                repeat(links.size){
                    Chip (entity = links[it])
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Chip(
    modifier: Modifier = Modifier,
    entity: LinkEntity = getFakeLinks()[0],
    context: Context = LocalContext.current
) {
    Surface(
        modifier = modifier
            .padding(end = padding_chips, top = padding_chips)
            .border(
                width = line_thickness,
                color = AppTheme.colors.backgroundChip,
                shape = RoundedCornerShape(border_radius)
            ),
        color = Color.Transparent
    ) {
        Row (
            modifier = Modifier
                .toggleable(
                    value = false,
                    onValueChange = {
                        val url = entity.url
                        if (URLUtil.isValidUrl(url)) {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(browserIntent)
                        } else {
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.error_invalid_link),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
        ) {
            TextRegular(
                modifier = Modifier.padding(padding_chips),
                text = entity.text
            )
        }
    }
}