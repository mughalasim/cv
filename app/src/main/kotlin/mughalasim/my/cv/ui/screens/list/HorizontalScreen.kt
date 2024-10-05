package mughalasim.my.cv.ui.screens.list

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
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
import mughalasim.my.cv.ui.theme.padding_screen_large
import mughalasim.my.cv.ui.utils.AppPreview
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.ChipWidget
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.ExperienceWidget
import mughalasim.my.cv.ui.widgets.ImageAndNameWidget
import mughalasim.my.cv.ui.widgets.ReferenceWidget
import mughalasim.my.cv.ui.widgets.SkillWidget

@Composable
fun HorizontalScreen(
    response: ResponseEntity,
    scrollState: ScrollState,
    onLinkTapped: (String) -> Unit = {},
) {
    Column {
        val pagerState = rememberPagerState(pageCount = { pageList.size })

        HorizontalPager(
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(padding_screen_large),
            state = pagerState,
            pageSize = PageSize.Fill,
            snapPosition = SnapPosition.Center
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(start = padding_screen),
            ) {
                when (page) {
                    PAGE_1 -> {
                        ImageAndNameWidget(response.description, scrollState)
                        BannerWidget(title = stringResource(R.string.txt_contact_info))
                        DescriptionWidget(response.description, onLinkTapped)
                    }

                    PAGE_2 -> {
                        BannerWidget(title = stringResource(R.string.txt_skills))
                        SkillWidget(response.skills)
                    }

                    PAGE_3 -> {
                        var sortAscending by remember { mutableStateOf(false) }
                        BannerWidget(
                            title = stringResource(R.string.txt_work_experience),
                            hasFilter = true,
                            sortAscending = sortAscending,
                            onFilterClicked = { sortAscending = !sortAscending }
                        )
                        ExperienceWidget(response.getOrderedWork(sortAscending), onLinkTapped)
                    }

                    PAGE_4 -> {
                        BannerWidget(title = stringResource(R.string.txt_education),)
                        ExperienceWidget(response.educations, onLinkTapped)
                    }

                    PAGE_5 -> {
                        BannerWidget(title = stringResource(R.string.txt_references))
                        ReferenceWidget(response.references)
                    }

                    PAGE_6 -> {
                        ChipWidget(
                            modifier =
                            Modifier
                                .padding(top = padding_screen)
                                .align(Alignment.Start),
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

@AppPreview
@Composable
fun HorizontalScreenPreview() {
    AppThemeComposable {
        HorizontalScreen(getFakeResponse(), rememberScrollState())
    }
}
