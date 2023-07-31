package mughalasim.my.cv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController

object AppTheme{
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val textStyles: AppTextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTextStyles.current
}

@Composable
fun AppThemeComposable(
    textStyles: AppTextStyles = AppTheme.textStyles,
    colors: AppColors = LightAppColors,
    darkColors: AppColors? = DarkAppColors,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val currentColor = remember { if (darkColors != null && isDarkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColors(currentColor) }
    val materialColors = remember {if (isDarkTheme) LightMaterialAppColors else DarkMaterialAppColors }

    DisposableEffect(systemUiController, isDarkTheme){
        systemUiController.setSystemBarsColor(
            color = rememberedColors.backgroundTitleBar
        )
        systemUiController.setStatusBarColor(
            color = rememberedColors.backgroundTitleBar
        )
        systemUiController.setNavigationBarColor(
            color = rememberedColors.backgroundTitleBar
        )
        onDispose {  }
    }

    MaterialTheme(
        colors = materialColors,
        typography = typography.copy(
            h1 = AppTheme.textStyles.large,
            body1 = AppTheme.textStyles.regular,
            body2 = AppTheme.textStyles.small
        ),
        shapes = shapes,
        content = content
    )

    CompositionLocalProvider(
        LocalColors provides rememberedColors
    ) {
        ProvideTextStyle(textStyles.small.copy(color = rememberedColors.textRegular), content)
        ProvideTextStyle(textStyles.regular.copy(color = rememberedColors.textRegular), content)
        ProvideTextStyle(textStyles.large.copy(color = rememberedColors.textSecondary), content)
        // https://gustav-karlsson.medium.com/extending-the-jetpack-compose-material-theme-with-more-colors-e1b849390d50
        // https://medium.com/@lucasyujideveloper/creating-a-custom-theme-in-jetpack-compose-54cbcbde1ace
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = rememberedColors.backgroundScreen,
        content = content
    )
}