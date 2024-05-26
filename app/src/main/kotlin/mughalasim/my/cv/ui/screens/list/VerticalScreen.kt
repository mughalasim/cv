package mughalasim.my.cv.ui.screens.list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import mughalasim.my.cv.ui.theme.AppTheme
import mughalasim.my.cv.ui.theme.AppThemeComposable
import mughalasim.my.cv.ui.theme.line_thickness_medium
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.ChipWidget
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.ExperienceWidget
import mughalasim.my.cv.ui.widgets.LinksWidget
import mughalasim.my.cv.ui.widgets.ReferenceWidget
import mughalasim.my.cv.ui.widgets.SkillWidget
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegular

@Composable
fun VerticalScreen(
    response: ResponseEntity,
    expandListOnStartUp: Boolean,
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
                .verticalScroll(rememberScrollState())
                .padding(padding_screen),
        horizontalAlignment = Alignment.Start,
    ) {
        // Name and Job title ----------------------------------------------------------------------
        TextLarge(text = response.description.fullName)
        TextRegular(text = response.description.positionTitle)

        // [Banner] Contact information ------------------------------------------------------------
        val contactInfoTitle = stringResource(id = R.string.txt_contact_info)
        BannerWidget(
            title = contactInfoTitle,
            onExpandedClicked = {
                isExpandedContacts = !isExpandedContacts
                onBannerTapped(contactInfoTitle)
            },
        )
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = isExpandedContacts,
        ) {
            Row(
                modifier =
                    Modifier
                        .height(IntrinsicSize.Min),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .width(line_thickness_medium)
                            .background(color = AppTheme.colors.highLight),
                ) {}
                Column(
                    modifier = Modifier.padding(start = padding_screen_small),
                ) {
                    // Basic information
                    DescriptionWidget(response.description)
                    // Links
                    LinksWidget(response.description.links, onLinkTapped)
                }
            }
        }

        // [Banner] Skills--------------------------------------------------------------------------
        val skillsTitle = stringResource(R.string.txt_skills)
        BannerWidget(
            title = skillsTitle,
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun VerticalScreenPreviewNight() {
    AppThemeComposable {
        VerticalScreen(getFakeResponse(), true)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun VerticalScreenPreview() {
    AppThemeComposable {
        VerticalScreen(getFakeResponse(), true)
    }
}
