package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.elevation
import mughalasim.my.cv.ui.theme.padding_screen

@Composable
fun WarningWidget(
    title: String,
){
    Spacer(modifier = Modifier.padding(top = padding_screen))
    Surface(
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation,
        color = AppTheme.colors.error
    ) {
        TextRegular(
            text = title,
            color = AppTheme.colors.surface,
            modifier = Modifier.padding(padding_screen)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWarningBanner(){
    Column(verticalArrangement = Arrangement.Top) {
        WarningWidget("Banner without warning")
    }
}