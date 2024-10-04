package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun ToolBarWidget(title: String) {
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
            text = title,
            color = AppTheme.colors.black,
        )
    }
}

@AppPreview
@Composable
fun ToolBarWidgetPreview() {
    AppThemeComposable {
        Column {
            ToolBarWidget(title = "Test Toolbar text")
        }
    }
}
