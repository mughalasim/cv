package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.line_thickness_small
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun ChipWidget(
    modifier: Modifier = Modifier,
    entity: LinkEntity,
    onLinkTapped: (String) -> Unit,
    context: Context = LocalContext.current,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val colorAnimation = infiniteTransition.animateColor(
        initialValue = AppTheme.colors.backgroundChip,
        targetValue = AppTheme.colors.backgroundScreen,
        animationSpec = infiniteRepeatable(
            keyframes {
                Color(0xFF000000) at 0
                Color(0xFF03DAC5) at 1000
                Color(0xFF000000) at 2000
                durationMillis = 2000
            }
        ),
        label = "colorAnimation"
    )

    Surface(
        modifier =
        modifier
            .padding(end = padding_screen_small, top = padding_screen_small)
            .border(
                width = line_thickness_small,
                color = colorAnimation.value,
                shape = RoundedCornerShape(border_radius),
            ),
        color = Color.Transparent,
    ) {
        Row(
            modifier =
            Modifier
                .toggleable(
                    value = false,
                    onValueChange = {
                        val url = entity.url
                        if (URLUtil.isValidUrl(url)) {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(browserIntent)
                            onLinkTapped(url)
                        } else {
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.error_invalid_link),
                                Toast.LENGTH_LONG,
                            ).show()
                        }
                    },
                ),
        ) {
            TextRegular(
                modifier = Modifier.padding(padding_screen_small),
                text = entity.text,
            )
        }
    }
}

@AppPreview
@Composable
fun ChipWidgetPreview() {
    AppThemeComposable {
        ChipWidget(entity = getFakeLinks()[0], onLinkTapped = {})
    }
}
