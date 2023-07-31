package mughalasim.my.cv.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val colorModeDark = "DARK_MODE"
val colorModeLight = "LIGHT_MODE"
val Red = Color(0xFFC90C0C)
val DarkBlue = Color(0xFF31078F)
val LightBlue = Color(0xFFCBD0D8)
val DarkGrey = Color(0xFF3B3A3C)
val LightGrey = Color(0xFF8D939C)

val DarkMaterialAppColors = darkColors(
    primary = Color.White,
    primaryVariant = LightGrey,
    secondary = DarkGrey,
    secondaryVariant = Color.Black,
    error = Red
)

val LightMaterialAppColors = lightColors(
    primary = DarkBlue, // Text and statusBar
    primaryVariant = DarkGrey, // Subheading
    secondary = LightBlue, // Banner colors
    secondaryVariant = Color.Black,
    error = Red
)

val DarkAppColors = AppColors (
    mode = colorModeDark,
    textRegular = Color.White,
    textSecondary = LightGrey,
    backgroundTitleBar = Color.Black,
    backgroundScreen = Color.Black,
    backgroundChip = DarkGrey,
    backgroundError = Red,
    backgroundBanner = DarkGrey
)

val LightAppColors = AppColors (
    mode = colorModeLight,
    textRegular = DarkBlue,
    textSecondary = DarkGrey,
    backgroundTitleBar = DarkBlue,
    backgroundScreen = Color.White,
    backgroundChip = LightBlue,
    backgroundError = Red,
    backgroundBanner = LightBlue
)

class AppColors(
    mode: String,
    textRegular: Color,
    textSecondary: Color,
    backgroundTitleBar: Color,
    backgroundScreen: Color,
    backgroundChip: Color,
    backgroundError: Color,
    backgroundBanner: Color
){
    var mode by mutableStateOf(mode)
        private set

    var textRegular by mutableStateOf(textRegular)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var backgroundTitleBar by mutableStateOf(backgroundTitleBar)
        private set

    var backgroundScreen by mutableStateOf(backgroundScreen)
        private set

    var backgroundChip by mutableStateOf(backgroundChip)
        private set

    var backgroundError by mutableStateOf(backgroundError)
        private set

    var backgroundBanner by mutableStateOf(backgroundBanner)
        private set

    fun copy (
        mode: String = this.mode,
        textRegular: Color = this.textRegular,
        textSecondary: Color = this.textSecondary,
        backgroundTitleBar: Color = this.backgroundTitleBar,
        backgroundScreen: Color = this.backgroundScreen,
        backgroundChip: Color = this.backgroundChip,
        backgroundError: Color = this.backgroundError,
        backgroundBanner: Color = this.backgroundBanner,
    ): AppColors = AppColors(
        mode,
        textRegular,
        textSecondary,
        backgroundTitleBar,
        backgroundScreen,
        backgroundChip,
        backgroundError,
        backgroundBanner
    )

    fun updateColors(updated: AppColors){
        mode = updated.mode
        textRegular = updated.textRegular
        textSecondary = updated.textSecondary
        backgroundTitleBar = updated.backgroundTitleBar
        backgroundScreen = updated.backgroundScreen
        backgroundChip = updated.backgroundChip
        backgroundError = updated.backgroundError
        backgroundBanner = updated.backgroundBanner
    }
}

val LocalColors = staticCompositionLocalOf { LightAppColors }