package mughalasim.my.cv.ui.screens.horizontalPager

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import mughalasim.my.cv.ui.theme.padding_screen
import mughalasim.my.cv.ui.theme.padding_screen_small
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.ChipWidget
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.ExperienceWidget
import mughalasim.my.cv.ui.widgets.LinksWidget
import mughalasim.my.cv.ui.widgets.LoadingWidget
import mughalasim.my.cv.ui.widgets.ReferenceWidget
import mughalasim.my.cv.ui.widgets.SkillWidget
import mughalasim.my.cv.ui.widgets.TextLarge
import mughalasim.my.cv.ui.widgets.TextRegular
import mughalasim.my.cv.ui.widgets.ToolBarWidget
import mughalasim.my.cv.ui.widgets.WarningWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun HorizontalPagerScreen() {
    val viewModel = koinViewModel<HorizontalPagerScreenViewModel>()
    val stateData = viewModel.getData().collectAsState(initial = State.Loading())
    when (val response = stateData.value) {
        is State.Loading -> LoadingWidget()

        is State.Failed -> WarningWidget(title = stringResource(id = R.string.error_server))

        is State.Success<*> -> {
            response as State.Success<ResponseEntity>
            HorizontalPagerScreenItems(
                response = response.data,
                onOpenSettingsTapped = {
                    viewModel.openSettings()
                },
                onBannerTapped = {
                    viewModel.onBannerTapped(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScreenItems(
    response: ResponseEntity,
    onOpenSettingsTapped: () -> Unit = {},
    onBannerTapped: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Toolbar ---------------------------------------------------------------------------------
        ToolBarWidget(title = stringResource(id = R.string.app_name),
            buttonTitle = stringResource(id = R.string.txt_settings)
        ) {
            onOpenSettingsTapped()
        }

        Spacer(modifier = Modifier.padding(top = padding_screen))

        // Name and Job title ----------------------------------------------------------------------
        Column (modifier = Modifier
            .align(Alignment.Start)
            .padding(start = padding_screen, end = padding_screen)
        ){
            TextLarge(text = response.description.full_name)
            TextRegular(text = response.description.position_title)
        }

        val pagerState = rememberPagerState(pageCount = { 6 })
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            state = pagerState) { page ->
            when (page) {
                // [Banner] Contact information ----------------------------------------------------
                0 -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val contactInfoTitle = stringResource(id = R.string.txt_contact_info)
                        BannerWidget(
                            title = contactInfoTitle,
                            onExpandedClicked = {
                                onBannerTapped(contactInfoTitle)
                            }
                        )
                        Row(modifier = Modifier
                            .padding(start = padding_screen, end = padding_screen)
                            .height(IntrinsicSize.Min))
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(padding_screen_small)
                                    .background(color = AppTheme.colors.highLight)
                            ) {}
                            Column(
                                modifier = Modifier.padding(start = padding_screen_small),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Basic information
                                DescriptionWidget(response.description)
                                // Links
                                LinksWidget(response.description.links)
                            }
                        }
                    }
                }

                // [Banner] Skills------------------------------------------------------------------
                1 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                    ) {
                        val skillsTitle = stringResource(R.string.txt_skills)
                        BannerWidget(
                            title = skillsTitle,
                            onExpandedClicked = {
                                onBannerTapped(skillsTitle)
                            }
                        )
                        // Skill list
                        SkillWidget(response.skills)
                    }
                }

                // [Banner] Work experience --------------------------------------------------------
                2 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                    ) {
                        val workExperienceTitle = stringResource(R.string.txt_work_experience)
                        var sortAscending by remember { mutableStateOf(false) }
                        BannerWidget(
                            title = workExperienceTitle,
                            hasFilter = true,
                            sortAscending = sortAscending,
                            onFilterClicked = { sortAscending = !sortAscending },
                            onExpandedClicked = {
                                onBannerTapped(workExperienceTitle)
                            }
                        )
                        // Work list and links for each experience list
                        ExperienceWidget(response.getOrderedWork(sortAscending))
                    }
                }

                // [Banner] Education --------------------------------------------------------------
                3 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                    ) {
                        val educationalExperienceTitle = stringResource(R.string.txt_education)
                        BannerWidget(
                            title = stringResource(R.string.txt_education),
                            onExpandedClicked = {
                                onBannerTapped(educationalExperienceTitle)
                            }
                        )
                        // Education list
                        ExperienceWidget(response.educations)
                    }
                }

                // [Banner] References -------------------------------------------------------------
                4 -> {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                    ) {
                        val referencesTitle = stringResource(R.string.txt_references)
                        BannerWidget(
                            title = referencesTitle,
                            onExpandedClicked = {
                                onBannerTapped(referencesTitle)
                            }
                        )
                        // Reference list
                        ReferenceWidget(response.references)
                    }
                }

                // [App version] -------------------------------------------------------------------
                5 ->{
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.padding(top = padding_screen))
                        ChipWidget(
                            modifier = Modifier
                                .padding(start = padding_screen)
                                .align(Alignment.CenterHorizontally),
                            entity = LinkEntity(
                                text = stringResource(R.string.txt_version, BuildConfig.VERSION_NAME),
                                url = "https://github.com/mughalasim/cv/releases"
                            )
                        )
                    }

                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ListScreenPreviewNight() {
    AppThemeComposable {
        HorizontalPagerScreenItems(getFakeResponse())
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ListScreenPreview() {
    AppThemeComposable {
        HorizontalPagerScreenItems(getFakeResponse())
    }
}