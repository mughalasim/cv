package mughalasim.my.cv.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.getTypography


@Preview(showBackground = true)
@Composable
fun TextRegular(
    text: String = "Test String",
    modifier: Modifier = Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).body1,
            modifier = modifier
        )
}

@Preview(showBackground = true)
@Composable
fun TextSmall(
    text: String = "Test String",
    modifier: Modifier = Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.error).body2,
            modifier = modifier
        )
}


@Preview(showBackground = true)
@Composable
fun TextLarge(
    text: String = "Test String",
    modifier: Modifier = Modifier
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = getTypography(AppTheme.colors.primary).h1,
            modifier = modifier
        )
}