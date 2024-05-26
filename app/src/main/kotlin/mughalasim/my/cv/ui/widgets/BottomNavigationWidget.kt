package mughalasim.my.cv.ui.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import mughalasim.my.cv.navigation.Route
import mughalasim.my.cv.ui.theme.AppTheme

@Composable
fun BottomNavigationWidget(
    navHostController: NavHostController
) {
    BottomNavigation(
        backgroundColor = AppTheme.colors.highLight
    ) {
        val tabList = listOf(
            BottomNavItem.ListScreen,
            BottomNavItem.SettingsScreen,
        )
        val navStackBackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navStackBackEntry?.destination

        tabList.forEach { item ->
            val isCurrentSelection = currentDestination?.hierarchy?.any { it.route == item.route } == true
            val selectedColor = if (isCurrentSelection) AppTheme.colors.textPrimary else AppTheme.colors.backgroundScreen
            BottomNavigationItem(
                selected = isCurrentSelection,
                onClick = {
                    if (!isCurrentSelection)
                        navHostController.navigate(item.route){
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = selectedColor
                    )
                },
                label = {
                    Text(
                        item.label,
                        color = selectedColor
                    ) }
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