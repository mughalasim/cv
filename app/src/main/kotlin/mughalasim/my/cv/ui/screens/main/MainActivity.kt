package mughalasim.my.cv.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.ConnectionState
import cv.domain.State
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.R
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
            WarningWidget(title = stringResource(R.string.error_internet_connection))
        }
        when(state.value){
            is State.Loading  ->
                LoadingWidget()

            is State.Success ->
                ListScreen(response = (state.value as State.Success<ResponseEntity>).data)

            is State.Failed ->
                WarningWidget(title = (state.value as State.Failed<ResponseEntity>).message)
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

        var isExpandedContacts by remember { mutableStateOf(false) }
        var isExpandedSkills by remember { mutableStateOf(false) }
        var isExpandedWork by remember { mutableStateOf(false) }
        var isExpandedEducation by remember { mutableStateOf(false) }
        var isExpandedReference by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.padding(top = padding_screen))

        // Name and Job title
        TextLarge(text = response.description.full_name)
        TextRegular(text = response.description.position_title)


        // [Banner] Contact information ------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_contact_info),
            isExpanded = isExpandedContacts,
            onExpandedClicked = { isExpandedContacts = !isExpandedContacts }
        )

        Spacer(modifier = Modifier.padding(top = padding_screen))
        if (isExpandedContacts){
            // Basic information
            DescriptionWidget(response.description)
            // Links
            LinksWidget(response.description.links)
        }


        // [Banner] Skills--------------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_skills),
            isExpanded = isExpandedSkills,
            onExpandedClicked = { isExpandedSkills = !isExpandedSkills }
        )
        // SKill list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        if (isExpandedSkills)
            SkillWidget(response.skills)



        // [Banner] Work experience ----------------------------------------------------------------
        var sortAscending by remember { mutableStateOf(false) }
        BannerWidget(
            title = stringResource(R.string.txt_work_experience),
            hasFilter = isExpandedWork,
            sortAscending = sortAscending,
            onFilterClicked = { sortAscending = !sortAscending },
            isExpanded = isExpandedWork,
            onExpandedClicked = { isExpandedWork = !isExpandedWork }
        )
        // Work list and links for each experience list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        if (isExpandedWork)
            ExperienceWidget(response.getOrderedWork(sortAscending))



        // [Banner] Education ----------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_education),
            isExpanded = isExpandedEducation,
            onExpandedClicked = { isExpandedEducation = !isExpandedEducation }
        )
        // Education list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        if (isExpandedEducation)
            ExperienceWidget(response.educations)



        // [Banner] References ---------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_references),
            isExpanded = isExpandedReference,
            onExpandedClicked = { isExpandedReference = !isExpandedReference }
        )
        // Reference list
        Spacer(modifier = Modifier.padding(top = padding_screen))
        if (isExpandedReference)
            ReferenceWidget(response.references)

    }
}