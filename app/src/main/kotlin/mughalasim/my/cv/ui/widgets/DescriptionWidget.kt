package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
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
        TextSmall(text = "Address")
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.address
        )

        TextSmall(text = "Telephone/Mobile")
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = "+${entity.area_code} ${entity.phone}"
        )

        TextSmall(text = "Email")
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.email
        )
    }
}