package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.padding_chips

@Composable
fun ButtonWidget(modifier: Modifier = Modifier, title: String, isEnabled: Boolean, onButtonClicked: ()-> Unit) {
    Column(
        modifier = modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .background(
                color = if (isEnabled) AppTheme.colors.textRegular else AppTheme.colors.backgroundBanner,
                shape = RoundedCornerShape(border_radius)
            )
            .clickable {
                onButtonClicked()
            }
    ) {
        TextSmall(
            modifier = Modifier
                .padding(padding_chips),
            text = title.uppercase(),
            color = if (isEnabled) AppTheme.colors.backgroundScreen else AppTheme.colors.textRegular
        )
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ButtonWidgetPreviewNight() {
    AppThemeComposable {
        Column {
            ButtonWidget(title = "Enabled button", isEnabled = true) {}
            ButtonWidget(title = "Disabled button", isEnabled = false) {}
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ButtonWidgetPreview() {
    AppThemeComposable {
        Column {
            ButtonWidget(title = "Enabled button", isEnabled = true) {}
            ButtonWidget(title = "Disabled button", isEnabled = false) {}
        }
    }
}