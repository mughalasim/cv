package mughalasim.my.cv.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val localFontFamily = FontFamily.Default

data class AppTextStyles(
    val small: TextStyle = TextStyle(
        fontFamily = localFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    val regular: TextStyle = TextStyle(
        fontFamily = localFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    val large: TextStyle = TextStyle(
        fontFamily = localFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)

val LocalTextStyles = staticCompositionLocalOf { AppTextStyles() }