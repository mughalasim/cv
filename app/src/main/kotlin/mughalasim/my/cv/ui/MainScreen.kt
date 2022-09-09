package mughalasim.my.cv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import cv.domain.entities.ResponseEntity
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.widgets.TextRegular
import mughalasim.my.cv.ui.widgets.WidgetLoading

class MainScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]

        setContent {
            Surface(
                color = AppTheme.colors.background,
                modifier = Modifier.fillMaxWidth()
            ){
                MainLoadingScreen(viewModel)
            }
        }

        viewModel.fetchData()
    }
}

@Composable
fun MainLoadingScreen(vm: MainScreenViewModel) {

    val vmData by vm.vmData.observeAsState(initial = MainScreenViewModel.VmData())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.size(4.dp))

        when(vmData.state){
            MainScreenViewModel.State.LOADING  -> WidgetLoading()
            MainScreenViewModel.State.LIST  -> ListScreen(data = vmData.data)
            MainScreenViewModel.State.ERROR -> TextRegular(text = vmData.errorMessage, modifier = Modifier.fillMaxSize())
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    data: ResponseEntity
) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

        }
}