package mughalasim.my.cv.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cv.domain.entities.SettingsEntity
import cv.domain.entities.getFakeSettingsEntity
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.utils.AppPreview
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegularBold
import mughalasim.my.cv.ui.widgets.TextSmall
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel = koinViewModel<SettingsScreenViewModel>()) {
    val settingsEntity = viewModel.settings.observeAsState(initial = viewModel.getState())

    SettingsScreenItems(
        settingsEntity = settingsEntity.value,
        onSettingsChanged = {
            when (it) {
                is OnSettingsChanged.ExpandListOnStartUp -> viewModel.setExpandListOnStartUp(it.isEnabled)
                is OnSettingsChanged.IsVerticalScreen -> viewModel.setListOrientation(it.isVertical)
            }
        },
    )
}

sealed class OnSettingsChanged {
    data class ExpandListOnStartUp(val isEnabled: Boolean) : OnSettingsChanged()

    data class IsVerticalScreen(val isVertical: Boolean) : OnSettingsChanged()
}

@Composable
fun SettingsScreenItems(
    settingsEntity: SettingsEntity,
    onSettingsChanged: (OnSettingsChanged) -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(padding_screen),
    ) {
        TextLarge("Settings", modifier = Modifier.padding(bottom = padding_screen))
        // Setting for Vertical or horizontal view pager on the main screen
        Row(
            modifier =
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Switch(
                modifier = Modifier.padding(end = padding_screen),
                checked = settingsEntity.isVerticalScreen,
                onCheckedChange = {
                    onSettingsChanged(OnSettingsChanged.IsVerticalScreen(it))
                },
            )
            Column {
                TextRegularBold(stringResource(R.string.txt_setting_is_vertical))
                TextSmall(
                    if (settingsEntity.isVerticalScreen) {
                        stringResource(R.string.txt_setting_is_vertical_true)
                    } else {
                        stringResource(R.string.txt_setting_is_vertical_false)
                    },
                )
            }
        }

        // Settings for all category collapsible state only for vertical list view
        if (settingsEntity.isVerticalScreen) {
            Spacer(modifier = Modifier.padding(top = padding_screen_large))
            Row(
                modifier =
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Switch(
                    modifier = Modifier.padding(end = padding_screen),
                    checked = settingsEntity.expandListOnStartUp,
                    onCheckedChange = {
                        onSettingsChanged(OnSettingsChanged.ExpandListOnStartUp(it))
                    },
                )
                Column {
                    TextRegularBold(stringResource(R.string.txt_setting_expanded_list))
                    TextSmall(
                        if (settingsEntity.expandListOnStartUp) {
                            stringResource(R.string.txt_setting_expanded_list_true)
                        } else {
                            stringResource(R.string.txt_setting_expanded_list_false)
                        },
                    )
                }
            }
        }
    }
}

@AppPreview
@Composable
fun SettingsScreenPreview() {
    AppThemeComposable {
        SettingsScreenItems(getFakeSettingsEntity()) {}
    }
}
