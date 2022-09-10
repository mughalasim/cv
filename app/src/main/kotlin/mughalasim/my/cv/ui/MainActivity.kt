package mughalasim.my.cv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.widgets.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                color = AppTheme.colors.background,
                modifier = Modifier.fillMaxWidth()
            ){
                MainActivityScreen(viewModel)
            }
        }

        viewModel.fetchData()
    }
}

@Composable
fun MainActivityScreen(vm: MainActivityViewModel) {

    val vmData by vm.vmData.observeAsState(initial = MainActivityViewModel.VmData())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        when(vmData.state){
            MainActivityViewModel.State.LOADING  ->
                WidgetLoading()

            MainActivityViewModel.State.LIST  ->
                ListScreen(response = vmData.response)

            MainActivityViewModel.State.ERROR ->
                TextRegular(text = vmData.errorMessage, modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreen(
    response: ResponseEntity = getFakeResponse()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        // Name and Job title
        TextLarge(text = response.description.full_name)
        TextRegular(text = response.description.position_title)

        // [Banner] Contact information ------------------------------------------------------------
        BannerWidget(title = "Contact information")
        // Basic information
        DescriptionWidget(response.description)
        // Links
        LinksWidget(response.description.links)

        // [Banner] Skills--------------------------------------------------------------------------
        BannerWidget(title = "Skills")
        // SKill list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        SkillWidget(response.skills)

        // [Banner] Work experience ----------------------------------------------------------------
        BannerWidget(title = "Work experience")
        // work list and links for each experience list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        ExperienceWidget(response.works)

        // [Banner] Education ----------------------------------------------------------------------
        BannerWidget(title = "Education")
        // Education list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        ExperienceWidget(response.educations)

        // [Banner] References ---------------------------------------------------------------------
        BannerWidget(title = "References")
        // Reference list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        ReferenceWidget(response.references)

    }
}