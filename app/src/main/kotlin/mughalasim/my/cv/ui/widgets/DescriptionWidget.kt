package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.DescriptionEntity
import cv.domain.entities.getFakeDescription
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun DescriptionWidget(entity: DescriptionEntity = getFakeDescription()) {
    Column(
        modifier = Modifier.padding(start = padding_screen, end = padding_screen).fillMaxWidth()
    ) {
        TextSmall(text = stringResource(R.string.txt_address))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.address
        )

        TextSmall(text = stringResource(R.string.txt_telephone))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = "+${entity.area_code} ${entity.phone}"
        )

        TextSmall(text = stringResource(R.string.txt_email))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.email
        )
    }
}