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
fun BannerWidget(title: String = "Sample Banner"){
    Spacer(modifier = Modifier.padding(top = padding_screen))
    Surface(
        modifier = Modifier,
        elevation = elevation,
        color = AppTheme.colors.secondary
    ) {
        TextRegular(
            text = title,
            modifier = Modifier.fillMaxWidth()
                .padding(padding_screen)
        )
    }
}