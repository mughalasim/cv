package mughalasim.my.cv.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.screens.list.ListScreenViewModel.UiState
import mughalasim.my.cv.ui.utils.ConnectionState
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(viewModel: ListScreenViewModel = koinViewModel<ListScreenViewModel>()) {
    val uiState =
        viewModel.uiStateFlow.collectAsState(initial = UiState.Loading)
    val connectionState =
        LocalContext.current.observeConnectivityAsFlow()
            .collectAsState(initial = LocalContext.current.currentConnectivityState)
    val scrollState = rememberScrollState()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize() ,
        isRefreshing = (uiState.value is UiState.Loading),
        onRefresh = { viewModel.getData() }
    ){
        Column (modifier = Modifier.verticalScroll(scrollState)) {
            if (connectionState.value == ConnectionState.Unavailable) {
                WarningWidget(title = stringResource(R.string.error_internet_connection))
            }
            when (val response = uiState.value) {
                is UiState.Loading -> Unit

                is UiState.Error -> {
                    WarningWidget(title = response.message)
                }

                is UiState.LanguageReceived -> {
                    val locale = Locale(response.languageEntity.locale)
                    Restring.putStrings(locale, response.languageEntity.singleTexts)
                    Restring.putStringArrays(locale, response.languageEntity.pluralTexts)
                }

                is UiState.DataReceived -> {
                    if (viewModel.isVerticalOrientation()) {
                        VerticalScreen(
                            response = response.responseEntity,
                            expandListOnStartUp = viewModel.getExpandListOnStartUp(),
                            scrollState = scrollState,
                            onBannerTapped = viewModel::onBannerTapped,
                            onLinkTapped = viewModel::onLinkTapped,
                        )
                    } else {
                        HorizontalScreen(
                            response = response.responseEntity,
                            scrollState = scrollState,
                            onLinkTapped = viewModel::onLinkTapped,
                        )
                    }
                }
            }
        }
    }
}
