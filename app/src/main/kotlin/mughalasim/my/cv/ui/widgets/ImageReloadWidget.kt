package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.icon_image
import mughalasim.my.cv.ui.theme.line_thickness_small
import mughalasim.my.cv.ui.theme.profile_picture_height
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun ImageReloadWidget(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(profile_picture_height),
        contentAlignment = Alignment.Center
    ) {
        var imageLoading by remember { mutableStateOf(true) }
        var showRetry by remember { mutableStateOf(false) }
        val context = LocalContext.current
        var imageRequest by remember { mutableStateOf(
            ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build()) }

        AsyncImage(
            model = imageRequest,
            contentDescription = "picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(border_radius))
                .border(
                    border = BorderStroke(line_thickness_small, AppTheme.colors.highLight),
                    shape = RoundedCornerShape(border_radius)
                )
                .fillMaxWidth()
                .height(profile_picture_height),
            onError = {
                imageLoading = false
                showRetry = true
            },
            onLoading = {
                imageLoading = true
                showRetry = false
            },
            onSuccess = {
                imageLoading = false
                showRetry = false
            }
        )

        if (imageLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(icon_image)
                    .height(icon_image)
                    .align(Alignment.Center)
            )
        }
        if (showRetry) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    imageRequest = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .crossfade(true)
                        .build()
                }
            ) {
                TextRegular(text = "Retry")
            }
        }
    }
}

@AppPreview
@Composable
fun ImageReloadWidgetPreview() {
    AppThemeComposable {
        ImageReloadWidget("")
    }
}
