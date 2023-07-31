package mughalasim.my.cv.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import cv.domain.ConnectionState
import cv.domain.State
import cv.domain.entities.LanguageEntity
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.navigation.NavigationHost
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
import mughalasim.my.cv.ui.widgets.LoadingWidget
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class MainScreen : ComponentActivity() {

    private val vm: MainScreenViewModel by viewModel()

    override fun getResources() = Restring.wrapResources(this, super.getResources())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemeComposable {
                val stateLanguage = vm.getLanguage().collectAsState(initial = State.Loading())
                val navController = rememberNavController()
                vm.setNavController(navController)

                when(val response = stateLanguage.value){
                    is State.Loading -> LoadingWidget()

                    is State.Failed -> WarningWidget(title = LocalContext.current.getString(R.string.error_server))

                    is State.Success<*> -> {
                        response as State.Success<LanguageEntity>
                        val locale = Locale(response.data.locale)
                        Restring.putStrings(locale, response.data.singleTexts)
                        Restring.putStringArrays(locale, response.data.pluralTexts)
                        NavigationHost(
                            navController = navController,
                            serviceNavigation = vm.getServiceNavigation()
                        )
                    }
                }
                val connectionState = LocalContext.current.observeConnectivityAsFlow()
                    .collectAsState(initial = LocalContext.current.currentConnectivityState)

                if (connectionState.value == ConnectionState.Unavailable) {
                    WarningWidget(title = stringResource(R.string.error_internet_connection))
                }
            }
        }
    }
}