package mughalasim.my.cv.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.SettingsEntity
import cv.domain.entities.getFakeSettingsEntity
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.widgets.ButtonWidget
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegular
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingsScreenViewModel>()
    val settingsEntity = viewModel.settings.observeAsState(initial = viewModel.getState())

    SettingsScreenItems(
        settingsEntity = settingsEntity.value,
        onSettingsChanged = {
            when (it) {
                is OnSettingsChanged.ExpandListOnStartUp -> viewModel.setExpandListOnStartUp(it.isEnabled)
            }
        },
        onNavigateBack = {
            viewModel.navigateBack()
        }
    )
}

sealed class OnSettingsChanged {
    data class ExpandListOnStartUp(val isEnabled: Boolean) : OnSettingsChanged()
}

@Composable
fun SettingsScreenItems (
    settingsEntity: SettingsEntity,
    onSettingsChanged: (OnSettingsChanged) -> Unit,
    onNavigateBack: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Row (
            modifier = Modifier
                .padding(padding_screen)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextLarge(
                text = stringResource(R.string.txt_settings)
            )
            ButtonWidget(
                modifier = Modifier.padding(start = padding_screen),
                title = "Save",
                isEnabled = true
            ){
                onNavigateBack()
            }
        }

        Row(modifier = Modifier
            .padding(start = padding_screen, end = padding_screen)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Switch(
                checked = settingsEntity.expandListOnStartUp,
                onCheckedChange = {
                    onSettingsChanged(OnSettingsChanged.ExpandListOnStartUp(it))
                }
            )
            Column {
                TextLarge(text = stringResource(R.string.txt_default_expanded_list))
                TextRegular(text = if(settingsEntity.expandListOnStartUp)
                    stringResource(R.string.txt_default_expanded_list_true)
                else
                    stringResource(R.string.txt_default_expanded_list_false)
                )
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SettingsScreenPreviewNight(){
    AppThemeComposable {
        SettingsScreenItems(getFakeSettingsEntity(), {}, {})
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SettingsScreenPreview(){
    AppThemeComposable {
        SettingsScreenItems(getFakeSettingsEntity(), {}, {})
    }
}