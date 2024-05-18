package mughalasim.my.cv.ui.screens.list

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
fun ListScreen() {
    val viewModel = koinViewModel<ListScreenViewModel>()
    val uiState = viewModel.uiStateFlow.collectAsState(initial = ListScreenViewModel.UiState.Loading)
    LaunchedEffect(true) {
        viewModel.getData()
    }
    when (val response = uiState.value) {
        is ListScreenViewModel.UiState.Loading -> LoadingWidget()

        is ListScreenViewModel.UiState.Error -> WarningWidget(title = response.message)

        is ListScreenViewModel.UiState.ResultsReceived -> {
            if (viewModel.isVerticalOrientation()) {
                VerticalScreen(
                    response = response.responseEntity,
                    expandListOnStartUp = viewModel.getExpandListOnStartUp(),
                    onOpenSettingsTapped = { viewModel.openSettings() },
                    onBannerTapped = { viewModel.onBannerTapped(it) },
                )
            } else {
                HorizontalScreen(
                    response = response.responseEntity,
                    onOpenSettingsTapped = { viewModel.openSettings() },
                    onBannerTapped = { viewModel.onBannerTapped(it) },
                )
            }
        }
    }
}

@Composable
fun VerticalScreen(
    response: ResponseEntity,
    expandListOnStartUp: Boolean,
    onOpenSettingsTapped: () -> Unit = {},
    onBannerTapped: (String) -> Unit = {},
) {
    val enterAnimation = slideInVertically() + fadeIn()
    val exitAnimation = fadeOut()

    var isExpandedContacts by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedSkills by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedWork by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedEducation by remember { mutableStateOf(expandListOnStartUp) }
    var isExpandedReference by remember { mutableStateOf(expandListOnStartUp) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Toolbar ---------------------------------------------------------------------------------
        ToolBarWidget(
            title = stringResource(id = R.string.app_name),
            buttonTitle = stringResource(id = R.string.txt_settings),
        ) {
            onOpenSettingsTapped()
        }

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
                enter = enterAnimation,
                exit = exitAnimation,
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
                                .width(padding_screen_small)
                                .background(color = AppTheme.colors.highLight),
                    ) {}
                    Column(
                        modifier = Modifier.padding(start = padding_screen_small),
                    ) {
                        // Basic information
                        DescriptionWidget(response.description)
                        // Links
                        LinksWidget(response.description.links)
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
                enter = enterAnimation,
                exit = exitAnimation,
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
                enter = enterAnimation,
                exit = exitAnimation,
            ) {
                ExperienceWidget(response.getOrderedWork(sortAscending))
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
                enter = enterAnimation,
                exit = exitAnimation,
            ) {
                ExperienceWidget(response.educations)
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
                enter = enterAnimation,
                exit = exitAnimation,
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
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Suppress("detekt.MagicNumber")
@Composable
fun HorizontalScreen(
    response: ResponseEntity,
    onOpenSettingsTapped: () -> Unit = {},
    onBannerTapped: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Toolbar ---------------------------------------------------------------------------------
        ToolBarWidget(
            title = stringResource(id = R.string.app_name),
            buttonTitle = stringResource(id = R.string.txt_settings),
        ) {
            onOpenSettingsTapped()
        }

        Spacer(modifier = Modifier.padding(top = padding_screen))

        // Name and Job title ----------------------------------------------------------------------
        Column(
            modifier =
                Modifier
                    .align(Alignment.Start)
                    .padding(start = padding_screen, end = padding_screen),
        ) {
            TextLarge(text = response.description.fullName)
            TextRegular(text = response.description.positionTitle)
        }

        val pagerState = rememberPagerState(pageCount = { 6 })
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            state = pagerState,
        ) { page ->
            when (page) {
                // [Banner] Contact information ----------------------------------------------------
                0 -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen),
                    ) {
                        val contactInfoTitle = stringResource(id = R.string.txt_contact_info)
                        BannerWidget(
                            title = contactInfoTitle,
                            onExpandedClicked = {
                                onBannerTapped(contactInfoTitle)
                            },
                        )
                        Row(
                            modifier =
                                Modifier
                                    .height(IntrinsicSize.Min),
                        ) {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxHeight()
                                        .width(padding_screen_small)
                                        .background(color = AppTheme.colors.highLight),
                            ) {}
                            Column(
                                modifier = Modifier.padding(start = padding_screen_small),
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
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen)
                                .verticalScroll(rememberScrollState()),
                    ) {
                        val skillsTitle = stringResource(R.string.txt_skills)
                        BannerWidget(
                            title = skillsTitle,
                            onExpandedClicked = {
                                onBannerTapped(skillsTitle)
                            },
                        )
                        // Skill list
                        SkillWidget(response.skills)
                    }
                }

                // [Banner] Work experience --------------------------------------------------------
                2 -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen)
                                .verticalScroll(rememberScrollState()),
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
                            },
                        )
                        // Work list and links for each experience list
                        ExperienceWidget(response.getOrderedWork(sortAscending))
                    }
                }

                // [Banner] Education --------------------------------------------------------------
                3 -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen)
                                .verticalScroll(rememberScrollState()),
                    ) {
                        val educationalExperienceTitle = stringResource(R.string.txt_education)
                        BannerWidget(
                            title = stringResource(R.string.txt_education),
                            onExpandedClicked = {
                                onBannerTapped(educationalExperienceTitle)
                            },
                        )
                        // Education list
                        ExperienceWidget(response.educations)
                    }
                }

                // [Banner] References -------------------------------------------------------------
                4 -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen)
                                .verticalScroll(rememberScrollState()),
                    ) {
                        val referencesTitle = stringResource(R.string.txt_references)
                        BannerWidget(
                            title = referencesTitle,
                            onExpandedClicked = {
                                onBannerTapped(referencesTitle)
                            },
                        )
                        // Reference list
                        ReferenceWidget(response.references)
                    }
                }

                // [App version] -------------------------------------------------------------------
                5 -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(start = padding_screen, end = padding_screen),
                    ) {
                        Spacer(modifier = Modifier.padding(top = padding_screen))
                        ChipWidget(
                            modifier =
                                Modifier
                                    .padding(start = padding_screen)
                                    .align(Alignment.CenterHorizontally),
                            entity =
                                LinkEntity(
                                    text = stringResource(R.string.txt_version, BuildConfig.VERSION_NAME),
                                    url = "https://github.com/mughalasim/cv/releases",
                                ),
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

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun HorizontalScreenPreviewNight() {
    AppThemeComposable {
        HorizontalScreen(getFakeResponse())
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun HorizontalScreenPreview() {
    AppThemeComposable {
        HorizontalScreen(getFakeResponse())
    }
}
