package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_screen

@Preview(showBackground = true)
@Composable
fun BannerWidget(title: String = "Sample Banner", isWarning: Boolean = false){
    Spacer(modifier = Modifier.padding(top = padding_screen))
    Surface(
        modifier = Modifier,
        elevation = elevation,
        color = if (isWarning) AppTheme.colors.error else AppTheme.colors.secondary
    ) {
        TextRegular(
            text = title,
            color = if (isWarning) AppTheme.colors.surface else AppTheme.colors.primary,
            modifier = Modifier.fillMaxWidth()
                .padding(padding_screen)
        )
    }
}