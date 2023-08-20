package mughalasim.my.cv.ui.theme

import androidx.compose.ui.graphics.Color

val Red = Color(0xFFC90C0C)
val DarkBlue = Color(0xFF31078F)
val LightBlue = Color(0xFFCBD0D8)
val DarkGrey = Color(0xFF3B3A3C)
val LightGrey = Color(0xFF8D939C)

val DarkAppColors = AppColors (
    textRegular = Color.White,
    textSecondary = LightGrey,
    backgroundTitleBar = Color.Black,
    backgroundScreen = Color.Black,
    backgroundChip = DarkGrey,
    backgroundError = Red,
    backgroundBanner = DarkGrey
)

val LightAppColors = AppColors (
    textRegular = DarkBlue,
    textSecondary = DarkGrey,
    backgroundTitleBar = DarkBlue,
    backgroundScreen = Color.White,
    backgroundChip = LightBlue,
    backgroundError = Red,
    backgroundBanner = LightBlue
)

class AppColors (
    val textRegular: Color,
    val textSecondary: Color,
    val backgroundTitleBar: Color,
    val backgroundScreen: Color,
    val backgroundChip: Color,
    val backgroundError: Color,
    val backgroundBanner: Color
)