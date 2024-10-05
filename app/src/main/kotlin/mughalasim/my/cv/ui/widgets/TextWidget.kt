package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun TextRegular(
    text: String,
    modifier: Modifier = Modifier,
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


@Composable
fun TextRegularBold(
    text: String,
    modifier: Modifier = Modifier,
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

@Composable
fun TextSmall(
    text: String,
    modifier: Modifier = Modifier,
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

@Composable
fun TextLarge(
    text: String,
    modifier: Modifier = Modifier,
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

@AppPreview
@Composable
fun TextPreview(){
    AppThemeComposable {
        Column {
            Spacer(modifier = Modifier.padding(padding_screen_large))
            TextSmall("Asim test")
            Spacer(modifier = Modifier.padding(padding_screen_large))
            TextRegular("Asim test")
            Spacer(modifier = Modifier.padding(padding_screen_large))
            TextRegularBold("Asim test")
            Spacer(modifier = Modifier.padding(padding_screen_large))
            TextLarge("Asim test")
        }
    }
}
