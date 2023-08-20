package mughalasim.my.cv.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme


@Preview(showBackground = true)
@Composable
fun TextRegular(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textRegular
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = AppTheme.textStyles.regular,
            color = color,
            modifier = modifier
        )
}

@Preview(showBackground = true)
@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textRegular
) {
    if (text.isNotEmpty())
        Text(
            text = text,
            style = AppTheme.textStyles.small,
            color = color,
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
            style = AppTheme.textStyles.large,
            color = AppTheme.colors.textRegular,
            modifier = modifier
        )
}