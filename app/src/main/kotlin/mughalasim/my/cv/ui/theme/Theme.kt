package mughalasim.my.cv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object AppTheme{
    val colors: AppColors
        @Composable
        get() = getColors()

    val textStyles: AppTextStyles
        @Composable
        get() = LocalTextStyles.current
}

@Composable
fun getColors(): AppColors {
    val systemUiController = rememberSystemUiController()
    val isDarkMode = isSystemInDarkTheme()
    val currentColors = remember {if (isDarkMode) DarkAppColors else LightAppColors }

    DisposableEffect(systemUiController, isSystemInDarkTheme()){
        systemUiController.setSystemBarsColor(
            color = currentColors.backgroundTitleBar
        )
        systemUiController.setStatusBarColor(
            color = currentColors.backgroundTitleBar
        )
        systemUiController.setNavigationBarColor(
            color = currentColors.backgroundTitleBar
        )
        onDispose {  }
    }

    return currentColors
}

@Composable
fun AppThemeComposable(
    textStyles: AppTextStyles = AppTheme.textStyles,
    content: @Composable () -> Unit
) {
    val currentColors = getColors()

    CompositionLocalProvider {
        ProvideTextStyle(textStyles.small.copy(color = currentColors.textPrimary), content)
        ProvideTextStyle(textStyles.regular.copy(color = currentColors.textPrimary), content)
        ProvideTextStyle(textStyles.large.copy(color = currentColors.textSecondary), content)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = currentColors.backgroundScreen,
        content = content
    )
}