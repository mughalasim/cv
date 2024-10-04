package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mughalasim.my.cv.navigation.Route
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.utils.AppPreview

@Composable
fun BottomNavigationWidget(navHostController: NavHostController) {
    NavigationBar(
        modifier = Modifier
            .background(AppTheme.colors.backgroundBottomNavigation),
        containerColor = Color.Transparent
    ) {
        val tabList =
            listOf(
                BottomNavItem.ListScreen,
                BottomNavItem.SettingsScreen,
            )
        val navStackBackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navStackBackEntry?.destination

        tabList.forEach { item ->
            val isCurrentSelection = currentDestination?.hierarchy?.any { it.route == item.route } == true
            NavigationBarItem(
                selected = isCurrentSelection,
                onClick = {
                    if (!isCurrentSelection) {
                        navHostController.navigate(item.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(item.label)
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = AppTheme.colors.backgroundScreen,
                    selectedTextColor = AppTheme.colors.textPrimary,
                    selectedIndicatorColor = AppTheme.colors.textPrimary,
                    disabledIconColor = AppTheme.colors.backgroundError,
                    disabledTextColor = AppTheme.colors.backgroundError,
                    unselectedIconColor = AppTheme.colors.textSecondary,
                    unselectedTextColor = AppTheme.colors.textSecondary
                )
            )
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object ListScreen :
        BottomNavItem(Route.ListScreen.routeName, Icons.Default.Home, "Home")

    data object SettingsScreen :
        BottomNavItem(Route.SettingsScreen.routeName, Icons.Default.Settings, "Settings")
}

@AppPreview
@Composable
fun BottomNavigationWidgetPreview() {
    AppThemeComposable {
        Column {
            BottomNavigationWidget(
                navHostController = rememberNavController(),
            )
        }
    }
}

