package mughalasim.my.cv.ui.widgets

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
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.openLink

@Preview(showBackground = true)
@Composable
fun LinksWidget(
    links: List<LinkEntity> = getFakeLinks()
){
    Column(modifier = Modifier.fillMaxWidth()){
        if (links.isNotEmpty())
            TextSmall("Links", modifier = Modifier.padding(start = padding_screen))
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
    entity: LinkEntity = getFakeLinks()[0]
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
                    openLink(entity)
                }
            )
        ) {
            TextRegular(text = entity.text,
                modifier = Modifier.padding(padding_chips)
            )
        }
    }
}