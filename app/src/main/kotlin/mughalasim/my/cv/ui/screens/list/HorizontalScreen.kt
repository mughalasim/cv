package mughalasim.my.cv.ui.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalScreen(
    response: ResponseEntity,
    onBannerTapped: (String) -> Unit = {},
    onLinkTapped: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(padding_screen),
    ) {
        TextLarge(text = response.description.fullName)
        TextRegular(text = response.description.positionTitle)

        val pagerState = rememberPagerState(pageCount = { pageList.size })
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            state = pagerState,
        ) { page ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
            ) {
                when (page) {
                    PAGE_1 -> {
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
                                        .width(line_thickness_medium)
                                        .background(color = AppTheme.colors.highLight),
                            ) {}
                            Column(
                                modifier = Modifier.padding(start = padding_screen_small),
                            ) {
                                DescriptionWidget(response.description)
                                LinksWidget(response.description.links, onLinkTapped)
                            }
                        }
                    }

                    PAGE_2 -> {
                        val skillsTitle = stringResource(R.string.txt_skills)
                        BannerWidget(
                            title = skillsTitle,
                            onExpandedClicked = {
                                onBannerTapped(skillsTitle)
                            },
                        )
                        SkillWidget(response.skills)
                    }

                    PAGE_3 -> {
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
                        ExperienceWidget(response.getOrderedWork(sortAscending), onLinkTapped)
                    }

                    PAGE_4 -> {
                        val educationalExperienceTitle = stringResource(R.string.txt_education)
                        BannerWidget(
                            title = stringResource(R.string.txt_education),
                            onExpandedClicked = {
                                onBannerTapped(educationalExperienceTitle)
                            },
                        )
                        ExperienceWidget(response.educations, onLinkTapped)
                    }

                    PAGE_5 -> {
                        val referencesTitle = stringResource(R.string.txt_references)
                        BannerWidget(
                            title = referencesTitle,
                            onExpandedClicked = {
                                onBannerTapped(referencesTitle)
                            },
                        )
                        ReferenceWidget(response.references)
                    }

                    PAGE_6 -> {
                        ChipWidget(
                            modifier =
                                Modifier
                                    .padding(start = padding_screen)
                                    .align(Alignment.CenterHorizontally),
                            entity =
                                LinkEntity(
                                    text =
                                        stringResource(
                                            R.string.txt_version,
                                            BuildConfig.VERSION_NAME,
                                        ),
                                    url = "https://github.com/mughalasim/cv/releases",
                                ),
                            onLinkTapped = onLinkTapped,
                        )
                    }
                }
            }
        }
    }
}

private const val PAGE_1 = 0
private const val PAGE_2 = 1
private const val PAGE_3 = 2
private const val PAGE_4 = 3
private const val PAGE_5 = 4
private const val PAGE_6 = 5

private val pageList = listOf(PAGE_1, PAGE_2, PAGE_3, PAGE_4, PAGE_5, PAGE_6)

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
