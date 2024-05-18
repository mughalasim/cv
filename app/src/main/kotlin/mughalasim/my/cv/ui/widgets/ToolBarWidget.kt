package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun ToolBarWidget(
    modifier: Modifier = Modifier,
    title: String,
    buttonTitle: String,
    buttonIsEnabled: Boolean = true,
    onButtonClicked: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .background(color = AppTheme.colors.highLight)
                .padding(padding_screen)
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextLarge(
            modifier = Modifier.width(235.dp),
            text = title,
            color = AppTheme.colors.black,
        )
        ButtonWidget(
            modifier = modifier,
            title = buttonTitle,
            isEnabled = buttonIsEnabled,
            onButtonClicked = onButtonClicked,
        )
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ToolBarWidgetPreviewNight() {
    AppThemeComposable {
        Column {
            ToolBarWidget(
                title = "Test Long toolbar title, that may overflow",
                buttonTitle = "Settings",
                buttonIsEnabled = true,
            ) {}
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun ToolBarWidgetPreview() {
    AppThemeComposable {
        Column {
            ToolBarWidget(title = "Test Toolbar text", buttonTitle = "Save", buttonIsEnabled = true) {}
        }
    }
}
