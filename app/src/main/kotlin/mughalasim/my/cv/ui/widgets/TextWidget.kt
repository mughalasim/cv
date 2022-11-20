package mughalasim.my.cv.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.getTypography


@Preview(showBackground = true)
@Composable
fun TextRegular(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.primary
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(color).body1,
            modifier = modifier
        )
}

@Preview(showBackground = true)
@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    text: String = "Test String"
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primaryVariant).body2,
            modifier = modifier
        )
}


@Preview(showBackground = true)
@Composable
fun TextLarge(
    modifier: Modifier = Modifier,
    text: String = "Test String"
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).h1,
            modifier = modifier
        )
}