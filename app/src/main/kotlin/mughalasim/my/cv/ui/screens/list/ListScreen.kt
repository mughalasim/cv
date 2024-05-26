package mughalasim.my.cv.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.utils.ConnectionState
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(viewModel: ListScreenViewModel = koinViewModel<ListScreenViewModel>()) {
    val uiState =
        viewModel.uiStateFlow.collectAsState(initial = ListScreenViewModel.UiState.Loading)
    val isRefreshing = (uiState.value is ListScreenViewModel.UiState.Loading)
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { viewModel.getData() })
    val connectionState =
        LocalContext.current.observeConnectivityAsFlow()
            .collectAsState(initial = LocalContext.current.currentConnectivityState)

    LaunchedEffect(true) {
        viewModel.getLanguage()
    }

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
    ) {
        when (val response = uiState.value) {
            is ListScreenViewModel.UiState.Loading -> Unit

            is ListScreenViewModel.UiState.Error -> {
                if (connectionState.value == ConnectionState.Unavailable) {
                    WarningWidget(title = stringResource(R.string.error_internet_connection))
                } else {
                    WarningWidget(title = response.message)
                }
            }

            is ListScreenViewModel.UiState.LanguageReceived -> {
                val locale = Locale(response.languageEntity.locale)
                Restring.putStrings(locale, response.languageEntity.singleTexts)
                Restring.putStringArrays(locale, response.languageEntity.pluralTexts)
                LaunchedEffect(true) {
                    viewModel.getData()
                }
            }

            is ListScreenViewModel.UiState.DataReceived -> {
                if (viewModel.isVerticalOrientation()) {
                    VerticalScreen(
                        response = response.responseEntity,
                        expandListOnStartUp = viewModel.getExpandListOnStartUp(),
                        onBannerTapped = viewModel::onBannerTapped,
                        onLinkTapped = viewModel::onLinkTapped,
                    )
                } else {
                    HorizontalScreen(
                        response = response.responseEntity,
                        onBannerTapped = viewModel::onBannerTapped,
                        onLinkTapped = viewModel::onLinkTapped,
                    )
                }
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}
