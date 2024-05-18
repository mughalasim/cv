package mughalasim.my.cv.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import cv.domain.ConnectionState
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.navigation.NavigationHost
import mughalasim.my.cv.ui.screens.list.ListScreenViewModel
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
import mughalasim.my.cv.ui.widgets.LoadingWidget
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<MainScreenViewModel>()
    val uiState = viewModel.uiStateFlow.collectAsState(initial = ListScreenViewModel.UiState.Loading)

    viewModel.setNavController(navController)
    LaunchedEffect(true) {
        viewModel.getLanguage()
    }

    when (val response = uiState.value) {
        is MainScreenViewModel.UiState.Loading -> LoadingWidget()

        is MainScreenViewModel.UiState.Error ->
            NavigationHost(
                navController = navController,
                serviceNavigation = viewModel.getServiceNavigation(),
            )

        is MainScreenViewModel.UiState.ResultsReceived -> {
            val locale = Locale(response.languageEntity.locale)
            Restring.putStrings(locale, response.languageEntity.singleTexts)
            Restring.putStringArrays(locale, response.languageEntity.pluralTexts)
            NavigationHost(
                navController = navController,
                serviceNavigation = viewModel.getServiceNavigation(),
            )
        }
    }
    val connectionState =
        LocalContext.current.observeConnectivityAsFlow()
            .collectAsState(initial = LocalContext.current.currentConnectivityState)

    if (connectionState.value == ConnectionState.Unavailable) {
        WarningWidget(title = stringResource(R.string.error_internet_connection))
    }
}
