package mughalasim.my.cv.ui.theme

import androidx.compose.ui.graphics.Color

val Red = Color(0xFFC90C0C)
val Yellow = Color(0xFFF1B252)
val LightGreen = Color(0xFFA0E294)
val DarkBlue = Color(0xFF31078F)
val LightBlue = Color(0xFFCBD0D8)
val DarkGrey = Color(0xFF3B3A3C)
val LightGrey = Color(0xFF8D939C)

val DarkAppColors = AppColors (
    textPrimary = Color.White,
    textSecondary = LightGrey,
    backgroundTitleBar = Color.Black,
    backgroundScreen = Color.Black,
    backgroundChip = DarkGrey,
    backgroundError = Red,
    backgroundButtonDisabled = DarkGrey,
    backgroundButtonEnabled = Color.White,
    highLight = LightGreen
)

val LightAppColors = AppColors (
    textPrimary = DarkBlue,
    textSecondary = DarkGrey,
    backgroundTitleBar = DarkBlue,
    backgroundScreen = Color.White,
    backgroundChip = LightBlue,
    backgroundError = Red,
    backgroundButtonDisabled = LightBlue,
    backgroundButtonEnabled = DarkBlue,
    highLight = Yellow
)

class AppColors (
    val textPrimary: Color,
    val textSecondary: Color,
    val backgroundTitleBar: Color,
    val backgroundScreen: Color,
    val backgroundChip: Color,
    val backgroundError: Color,
    val backgroundButtonDisabled: Color,
    val backgroundButtonEnabled: Color,
    val highLight: Color,
    val black: Color = Color.Black
)