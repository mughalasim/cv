package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.DescriptionEntity
import cv.domain.entities.getFakeDescription
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun DescriptionWidget(entity: DescriptionEntity = getFakeDescription()) {
    Column(
        modifier = Modifier.padding(start = padding_screen, end = padding_screen).fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.padding(top = padding_screen))

        TextSmall("Address")
        TextRegular(entity.address, modifier = Modifier.padding(bottom = padding_screen))

        TextSmall("Telephone/Mobile")
        TextRegular(
            "+${entity.area_code} ${entity.phone}",
            modifier = Modifier.padding(bottom = padding_screen)
        )

        TextSmall("Email")
        TextRegular(entity.email, modifier = Modifier.padding(bottom = padding_screen))
    }
}