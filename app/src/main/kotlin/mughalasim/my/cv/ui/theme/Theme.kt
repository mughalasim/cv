package mughalasim.my.cv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = LightGrey,
    secondary = DarkGrey,
)

private val LightColorPalette = lightColors(
    primary = DarkBlue, // Text and statusBar
    primaryVariant = DarkGrey, // Subheading
    secondary = LightBlue, // Banner colors

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

object AppTheme{
    val colors: Colors
        @Composable
        get() = getColors(isSystemInDarkTheme())
}

@Composable
fun getColors(isDarkTheme: Boolean): Colors {
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, isDarkTheme){
        systemUiController.setSystemBarsColor(
            color = if(isDarkTheme) DarkGrey else LightBlue
        )
        systemUiController.setStatusBarColor(
            color = if(isDarkTheme) DarkGrey else LightBlue
        )
        onDispose {  }
    }
    return if(isDarkTheme) DarkColorPalette else LightColorPalette
}