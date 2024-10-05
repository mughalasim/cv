package mughalasim.my.cv.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cv.domain.entities.LinkEntity
import cv.domain.entities.ResponseEntity
import cv.domain.entities.getFakeResponse
import mughalasim.my.cv.BuildConfig
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.utils.AppPreview
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.ChipWidget
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.ExperienceWidget
import mughalasim.my.cv.ui.widgets.ImageAndNameWidget
import mughalasim.my.cv.ui.widgets.ReferenceWidget
import mughalasim.my.cv.ui.widgets.SkillWidget

@Composable
fun VerticalScreen(
    response: ResponseEntity,
    expandListOnStartUp: Boolean,
    scrollState: ScrollState,
    onBannerTapped: (String) -> Unit = {},
    onLinkTapped: (String) -> Unit = {},
) {
    var isExpandedContacts by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedSkills by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedWork by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedEducation by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedReference by remember { mutableStateOf(expandListOnStartUp) }

    Column(
        modifier =
        Modifier
            .padding(padding_screen),
        horizontalAlignment = Alignment.Start,
    ) {
        // Image, Name and Job title ---------------------------------------------------------------
        ImageAndNameWidget(response.description, scrollState)

        // [Banner] Contact information ------------------------------------------------------------
        val contactInfoTitle = stringResource(id = R.string.txt_contact_info)
        BannerWidget(
            title = contactInfoTitle,
            isExpanded = isExpandedContacts,
            onExpandedClicked = {
                isExpandedContacts = !isExpandedContacts
                onBannerTapped(contactInfoTitle)
            },
        )
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedContacts,
        ) {
            DescriptionWidget(response.description, onLinkTapped)
        }

        // [Banner] Skills--------------------------------------------------------------------------
        val skillsTitle = stringResource(R.string.txt_skills)
        BannerWidget(
            title = skillsTitle,
            isExpanded = isExpandedSkills,
            onExpandedClicked = {
                isExpandedSkills = !isExpandedSkills
                onBannerTapped(skillsTitle)
            },
        )
        // SKill list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedSkills,
        ) {
            SkillWidget(response.skills)
        }

        // [Banner] Work experience ----------------------------------------------------------------
        val workExperienceTitle = stringResource(R.string.txt_work_experience)
        var sortAscending by remember { mutableStateOf(false) }
        BannerWidget(
            title = workExperienceTitle,
            hasFilter = isExpandedWork,
            sortAscending = sortAscending,
            isExpanded = isExpandedWork,
            onFilterClicked = { sortAscending = !sortAscending },
            onExpandedClicked = {
                isExpandedWork = !isExpandedWork
                onBannerTapped(workExperienceTitle)
            },
        )
        // Work list and links for each experience list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedWork,
        ) {
            ExperienceWidget(response.getOrderedWork(sortAscending), onLinkTapped)
        }

        // [Banner] Education ----------------------------------------------------------------------
        val educationalExperienceTitle = stringResource(R.string.txt_education)
        BannerWidget(
            title = stringResource(R.string.txt_education),
            isExpanded = isExpandedEducation,
            onExpandedClicked = {
                isExpandedEducation = !isExpandedEducation
                onBannerTapped(educationalExperienceTitle)
            },
        )
        // Education list
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedEducation,
        ) {
            ExperienceWidget(response.educations, onLinkTapped)
        }

        // [Banner] References ---------------------------------------------------------------------
        val referencesTitle = stringResource(R.string.txt_references)
        BannerWidget(
            title = referencesTitle,
            isExpanded = isExpandedReference,
            onExpandedClicked = {
                isExpandedReference = !isExpandedReference
                onBannerTapped(referencesTitle)
            },
        )
        // Reference list
        AnimatedVisibility(
            modifier =
                Modifier
                    .fillMaxWidth(),
            visible = isExpandedReference,
        ) {
            ReferenceWidget(response.references)
        }

        // [App version] ---------------------------------------------------------------------------
        Spacer(modifier = Modifier.padding(top = padding_screen))
        ChipWidget(
            entity =
                LinkEntity(
                    text = stringResource(R.string.txt_version, BuildConfig.VERSION_NAME),
                    url = "https://github.com/mughalasim/cv/releases",
                ),
            onLinkTapped = onLinkTapped,
        )
    }
}

@AppPreview
@Composable
fun VerticalScreenPreview() {
    AppThemeComposable {
        VerticalScreen(
            response = getFakeResponse(),
            expandListOnStartUp = true,
            scrollState = rememberScrollState()
        )
    }
}
