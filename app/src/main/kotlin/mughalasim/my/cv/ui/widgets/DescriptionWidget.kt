package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cv.domain.entities.DescriptionEntity
import cv.domain.entities.getFakeDescription
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun DescriptionWidget(entity: DescriptionEntity = getFakeDescription()) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth(),
    ) {
        TextSmall(text = stringResource(R.string.txt_address))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.address,
        )

        TextSmall(text = stringResource(R.string.txt_telephone))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = "+${entity.areaCode} ${entity.phone}",
        )

        TextSmall(text = stringResource(R.string.txt_email))
        TextRegular(
            modifier = Modifier.padding(bottom = padding_screen),
            text = entity.email,
        )
    }
}

@AppPreview
@Composable
fun DescriptionWidgetPreview() {
    AppThemeComposable {
        DescriptionWidget()
    }
}
