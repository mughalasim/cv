package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme

@Composable
fun BannerWidget(title: String){
    TextRegular(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 0.dp, end = 0.dp)
            .background(AppTheme.colors.secondary)
    )
}

@Preview
@Composable
fun PreviewBannerWidget(){
    BannerWidget("Text title")
}