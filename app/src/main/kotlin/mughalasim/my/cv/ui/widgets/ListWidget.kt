package mughalasim.my.cv.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import cv.domain.entities.LinkEntity
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.BuildConfig
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.padding_chips
import mughalasim.my.cv.ui.theme.padding_screen


@Preview(showBackground = true)
@Composable
fun ListWidget(
    response: ResponseEntity = getFakeResponse()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val initialExpandedState = true
        val enterAnimation = slideInVertically() + fadeIn()
        val exitAnimation = slideOutVertically() + fadeOut()

        var isExpandedContacts by remember { mutableStateOf(initialExpandedState) }
        var isExpandedSkills by remember { mutableStateOf(initialExpandedState) }
        var isExpandedWork by remember { mutableStateOf(initialExpandedState) }
        var isExpandedEducation by remember { mutableStateOf(initialExpandedState) }
        var isExpandedReference by remember { mutableStateOf(initialExpandedState) }

        Spacer(modifier = Modifier.padding(top = padding_screen))

        // Name and Job title
        TextLarge(text = response.description.full_name)
        TextRegular(text = response.description.position_title)


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
            Column(horizontalAlignment = Alignment.CenterHorizontally){
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
                url = "https://github.com/mughalasim/cv/releases")
        )
        Spacer(modifier = Modifier.padding(top = padding_chips))
    }
}