package mughalasim.my.cv.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen_small

@Composable
fun WarningWidget(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)
            .background(color = AppTheme.colors.backgroundError),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextRegular(
            modifier = Modifier.padding(padding_screen_small),
            text = title,
            color = AppTheme.colors.backgroundScreen
        )
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun WarningWidgetPreviewNight() {
    AppThemeComposable {
        WarningWidget("Warning message will be placed here!")
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun WarningWidgetPreview() {
    AppThemeComposable {
        WarningWidget("Warning message will be placed here!")
    }
}