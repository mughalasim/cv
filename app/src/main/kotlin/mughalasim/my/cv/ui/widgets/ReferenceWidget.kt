package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ReferenceEntity
import cv.domain.entities.getFakeReferences
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun ReferenceWidget(references: List<ReferenceEntity> = getFakeReferences()) {
    Column(
        modifier = Modifier
            .padding(start = padding_screen, end = padding_screen)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        repeat(references.size) {
            val entity = references[it]
            TextSmall(text = entity.name)
            TextRegular(text = entity.company)
            TextRegular(
                modifier = Modifier.padding(bottom = padding_screen),
                text = entity.contact
            )
        }
    }
}