package mughalasim.my.cv.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegular
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingsScreenViewModel>()
    val settingsEntity = remember {viewModel.getSettings() }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = padding_screen))

        BannerWidget(
            title = stringResource(R.string.txt_settings)
        )

        Column {
            TextLarge(text = "Default expanded list")
            TextRegular(text = "When enabled, the main screen will expand all items under each category")
        }
        Column(verticalArrangement = Arrangement.Center) {
            Switch(
                checked = settingsEntity.expandListOnStartUp,
                onCheckedChange = {
                        //viewModel.setSettingsExpandedList()
                    }
            )
        }

    }
}