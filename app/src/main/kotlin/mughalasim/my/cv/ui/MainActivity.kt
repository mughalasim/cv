package mughalasim.my.cv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.ConnectionState
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.currentConnectivityState
import mughalasim.my.cv.ui.utils.observeConnectivityAsFlow
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
    }
}

@Composable
fun MainActivityScreen(vm: MainActivityViewModel) {

    val context = LocalContext.current

    val state = vm.getData().collectAsState(initial = State.Loading())

    val connectionState = context.observeConnectivityAsFlow().collectAsState(initial = context.currentConnectivityState)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (connectionState.value == ConnectionState.Unavailable){
            TextLarge(text = "NO NETWORK")
        }
        when(state.value){
            is State.Loading  ->
                WidgetLoading()

            is State.Success ->
                ListScreen(response = (state.value as State.Success<ResponseEntity>).data)

            is State.Failed ->
                TextRegular(text = (state.value as State.Failed<ResponseEntity>).message, modifier = Modifier.fillMaxSize())
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