package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ReferenceEntity
import cv.domain.entities.getFakeReferences
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun ReferenceWidget(references: List<ReferenceEntity> = getFakeReferences()) {
    repeat(references.size) {
        val entity = references[it]
        Column(modifier = Modifier.padding(start = padding_screen, end = padding_screen).fillMaxWidth()) {
            TextSmall(entity.name)
            TextRegular(entity.company)
            TextRegular(entity.contact, modifier = Modifier.padding(bottom = padding_screen))
        }
    }
}