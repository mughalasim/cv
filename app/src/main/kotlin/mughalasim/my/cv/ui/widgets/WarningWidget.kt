package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun WarningWidget(title: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)
            .padding(padding_screen)
            .shadow(elevation = elevation)
            .background(color = AppTheme.colors.backgroundError),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextRegular(
            modifier = Modifier.padding(padding_screen),
            text = title,
            color = AppTheme.colors.backgroundScreen
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWarningBanner(){
    AppThemeComposable {
        WarningWidget("Warning message will be placed here!")
    }
}