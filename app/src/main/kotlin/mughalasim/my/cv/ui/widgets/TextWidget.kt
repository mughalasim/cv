package mughalasim.my.cv.ui.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.utils.AppPreview

@AppPreview
@Composable
fun TextRegular(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textPrimary,
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            style = AppTheme.textStyles.regular,
            color = color,
            modifier = modifier,
        )
    }
}


@AppPreview
@Composable
fun TextRegularBold(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textPrimary,
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            style = AppTheme.textStyles.regularBold,
            color = color,
            modifier = modifier,
        )
    }
}

@AppPreview
@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textPrimary,
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            style = AppTheme.textStyles.small,
            color = color,
            modifier = modifier,
        )
    }
}

@AppPreview
@Composable
fun TextLarge(
    modifier: Modifier = Modifier,
    text: String = "Test String",
    color: Color = AppTheme.colors.textPrimary,
) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            style = AppTheme.textStyles.large,
            color = color,
            modifier = modifier,
        )
    }
}
