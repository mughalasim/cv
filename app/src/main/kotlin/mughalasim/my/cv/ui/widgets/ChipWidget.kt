package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.Variables.EVENT_NAME_LINK
import cv.domain.Variables.PARAM_LINK
import cv.domain.entities.LinkEntity
import cv.domain.entities.getFakeLinks
import mughalasim.my.cv.R
import mughalasim.my.cv.di.DI
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.border_radius
import mughalasim.my.cv.ui.theme.line_thickness
import mughalasim.my.cv.ui.theme.padding_screen_small

@Composable
fun ChipWidget(
    modifier: Modifier = Modifier,
    entity: LinkEntity,
    context: Context = LocalContext.current
) {
    Surface(
        modifier = modifier
            .padding(end = padding_screen_small, top = padding_screen_small)
            .border(
                width = line_thickness,
                color = AppTheme.colors.backgroundChip,
                shape = RoundedCornerShape(border_radius)
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = false,
                    onValueChange = {
                        val url = entity.url
                        if (URLUtil.isValidUrl(url)) {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(browserIntent)
                            DI.analytics.logEvent(
                                EVENT_NAME_LINK,
                                listOf(Pair(PARAM_LINK, url))
                            )
                        } else {
                            Toast.makeText(
                                context,
                                context.resources.getString(R.string.error_invalid_link),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
        ) {
            TextRegular(
                modifier = Modifier.padding(padding_screen_small),
                text = entity.text
            )
        }
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ChipWidgetPreviewNight() {
    AppThemeComposable {
        ChipWidget(entity = getFakeLinks()[0])
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ChipWidgetPreview() {
    AppThemeComposable {
        ChipWidget(entity = getFakeLinks()[0])
    }
}