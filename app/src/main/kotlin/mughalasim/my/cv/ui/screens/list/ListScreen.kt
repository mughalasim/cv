package mughalasim.my.cv.ui.screens.list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.State
import cv.domain.entities.LinkEntity
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.BuildConfig
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.Chip
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.ExperienceWidget
import mughalasim.my.cv.ui.widgets.LinksWidget
import mughalasim.my.cv.ui.widgets.LoadingWidget
import mughalasim.my.cv.ui.widgets.ReferenceWidget
import mughalasim.my.cv.ui.widgets.SkillWidget
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegular
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen() {
    val viewModel = koinViewModel<ListScreenViewModel>()
    val stateData = viewModel.getData().collectAsState(initial = State.Loading())
    when (val response = stateData.value) {
        is State.Loading -> LoadingWidget()

        is State.Failed -> WarningWidget(title = LocalContext.current.getString(R.string.error_server))

        is State.Success<*> -> {
            response as State.Success<ResponseEntity>
            ListScreenItems(
                response = response.data,
                initialListExpandedState = viewModel.getInitialListExpandedState()
            ) {
                viewModel.openSettings()
            }
        }
    }
}

@Composable
fun ListScreenItems(
    response: ResponseEntity,
    initialListExpandedState: Boolean,
    onOpenSettingsClicked: () -> Unit = {}
) {
    val enterAnimation = slideInVertically() + fadeIn()
    val exitAnimation = fadeOut()

    var isExpandedContacts by remember { mutableStateOf(initialListExpandedState) }
    var isExpandedSkills by remember { mutableStateOf(initialListExpandedState) }
    var isExpandedWork by remember { mutableStateOf(initialListExpandedState) }
    var isExpandedEducation by remember { mutableStateOf(initialListExpandedState) }
    var isExpandedReference by remember { mutableStateOf(initialListExpandedState) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = padding_screen))

        // Name and Job title ----------------------------------------------------------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding_screen, end = padding_screen),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                TextLarge(text = response.description.full_name)
                TextRegular(text = response.description.position_title)
            }
            Column(verticalArrangement = Arrangement.Center) {
                Icon(
                    modifier = Modifier
                        .padding(padding_chips)
                        .clickable { onOpenSettingsClicked() },
                    painter = painterResource(id = R.drawable.ic_settings),
                    tint = AppTheme.colors.textRegular,
                    contentDescription = null
                )
            }
        }


        // [Banner] Contact information ------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_contact_info),
            onExpandedClicked = { isExpandedContacts = !isExpandedContacts }
        )
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedContacts,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Basic information
                DescriptionWidget(response.description)
                // Links
                LinksWidget(response.description.links)
            }
        }

        // [Banner] Skills--------------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_skills),
            onExpandedClicked = { isExpandedSkills = !isExpandedSkills }
        )
        // SKill list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedSkills,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            SkillWidget(response.skills)
        }


        // [Banner] Work experience ----------------------------------------------------------------
        var sortAscending by remember { mutableStateOf(false) }
        BannerWidget(
            title = stringResource(R.string.txt_work_experience),
            hasFilter = isExpandedWork,
            sortAscending = sortAscending,
            onFilterClicked = { sortAscending = !sortAscending },
            onExpandedClicked = { isExpandedWork = !isExpandedWork }
        )
        // Work list and links for each experience list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedWork,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            ExperienceWidget(response.getOrderedWork(sortAscending))
        }


        // [Banner] Education ----------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_education),
            onExpandedClicked = { isExpandedEducation = !isExpandedEducation }
        )
        // Education list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedEducation,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            ExperienceWidget(response.educations)
        }


        // [Banner] References ---------------------------------------------------------------------
        BannerWidget(
            title = stringResource(R.string.txt_references),
            onExpandedClicked = { isExpandedReference = !isExpandedReference }
        )
        // Reference list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedReference,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            ReferenceWidget(response.references)
        }

        // [App version] ---------------------------------------------------------------------------
        Spacer(modifier = Modifier.padding(top = padding_screen))
        Chip(
            modifier = Modifier
                .padding(start = padding_chips)
                .align(Alignment.Start),
            entity = LinkEntity(
                text = stringResource(R.string.txt_version, BuildConfig.VERSION_NAME),
                url = "https://github.com/mughalasim/cv/releases"
            )
        )
        Spacer(modifier = Modifier.padding(top = padding_chips))
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListScreenPreviewNight(){
    AppThemeComposable {
        ListScreenItems(getFakeResponse(), true)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ListScreenPreview(){
    AppThemeComposable {
        ListScreenItems(getFakeResponse(), true)
    }
}