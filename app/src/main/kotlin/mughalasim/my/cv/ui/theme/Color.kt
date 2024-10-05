package mughalasim.my.cv.ui.theme

import androidx.compose.ui.graphics.Color

@Suppress("detekt.MagicNumber")
val Red = Color(0xFFC90C0C)

@Suppress("detekt.MagicNumber")
val LightPurple = Color(0xFFE8EAF6)

@Suppress("detekt.MagicNumber")
val LightTeal = Color(0xFFE0F7FA)

@Suppress("detekt.MagicNumber")
val DarkBlue = Color(0xFF1A237E)

@Suppress("detekt.MagicNumber")
val LightBlue = Color(0xFFCBD0D8)

@Suppress("detekt.MagicNumber")
val DarkGrey = Color(0xFF3B3A3C)

@Suppress("detekt.MagicNumber")
val LightGrey = Color(0xFF8D939C)

val DarkAppColors =
    AppColors(
        textPrimary = Color.White,
        textSecondary = LightGrey,
        backgroundTitleBar = Color.DarkGray,
        backgroundScreen = Color.Black,
        backgroundChip = DarkGrey,
        backgroundError = Red,
        backgroundBottomNavigation = DarkGrey,
        highLight = LightTeal,
    )

val LightAppColors =
    AppColors(
        textPrimary = DarkBlue,
        textSecondary = DarkGrey,
        backgroundTitleBar = LightPurple,
        backgroundScreen = Color.White,
        backgroundChip = LightBlue,
        backgroundError = Red,
        backgroundBottomNavigation = LightTeal,
        highLight = LightPurple,
    )

@Suppress("detekt.LongParameterList")
class AppColors(
    val textPrimary: Color,
    val textSecondary: Color,
    val backgroundTitleBar: Color,
    val backgroundScreen: Color,
    val backgroundChip: Color,
    val backgroundError: Color,
    val backgroundBottomNavigation: Color,
    val highLight: Color,
    val black: Color = Color.Black,
)
