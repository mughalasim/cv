package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.getTypography

private val padding_bottom = 5.dp

@Composable
fun TextRegular(
    text: String,
    modifier: Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).body2,
            modifier = modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = padding_bottom)
        )
}

@Composable
fun TextSmall(
    text: String,
    modifier: Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).body1,
            modifier = modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = padding_bottom)
        )
}


@Composable
fun TextLarge(
    text: String,
    modifier: Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).h1,
            modifier = modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = padding_bottom)
        )
}