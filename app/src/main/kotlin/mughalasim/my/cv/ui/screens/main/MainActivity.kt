package mughalasim.my.cv.ui.screens.main

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cv.domain.ConnectionState
import cv.domain.State
import cv.domain.entities.LanguageEntity
import cv.domain.entities.ResponseEntity
import dev.b3nedikt.restring.Restring
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
import mughalasim.my.cv.ui.widgets.ListWidget
import mughalasim.my.cv.ui.widgets.LoadingWidget
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : ComponentActivity() {

    private val vm: MainActivityViewModel by viewModel()

    override fun getResources(): Resources {
        return Restring.wrapResources(this, super.getResources())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                color = AppTheme.colors.background,
                modifier = Modifier.fillMaxWidth()
            ){
                val stateData = vm.getData().collectAsState(initial = State.Loading())

                val stateLanguage = vm.getLanguage().collectAsState(initial = State.Loading())

                val connectionState = LocalContext.current.observeConnectivityAsFlow()
                    .collectAsState(initial = LocalContext.current.currentConnectivityState)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (connectionState.value == ConnectionState.Unavailable){
                        WarningWidget(title = stringResource(R.string.error_internet_connection))
                    }
                    if (stateData.value is State.Loading || stateLanguage.value is State.Loading){
                       LoadingWidget()
                    } else {
                        when(stateLanguage.value){
                            is State.Success<LanguageEntity> -> {
                                val language = stateLanguage.value as State.Success<LanguageEntity>
                                val locale = Locale(language.data.locale)
                                Restring.putStrings(locale, language.data.singleTexts)
                                Restring.putStringArrays(locale, language.data.pluralTexts)

                                when(stateData.value){
                                    is State.Success<ResponseEntity> ->
                                        ListWidget(response = (stateData.value as State.Success<ResponseEntity>).data)

                                    is State.Failed<ResponseEntity> ->
                                        WarningWidget(title = getString(R.string.error_server))
                                }
                            }

                            is State.Failed<LanguageEntity> ->
                                WarningWidget(title = getString(R.string.error_server))
                        }
                    }
                }
            }
        }
    }
}