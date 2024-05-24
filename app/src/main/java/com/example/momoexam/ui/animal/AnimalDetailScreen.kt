package com.example.momoexam.ui.animal

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.momoexam.R
import com.example.momoexam.ui.MainViewModel
import com.example.momoexam.utils.DateUtils
import com.example.momoexam.utils.Utils.mergeStringsWithSeparator
import com.example.momoexam.vo.animal.AnimalInfo

/**
 * Create by EricLiao on 2023/11/17.
 */
@Composable
fun AnimalDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimalDetailViewModel = hiltViewModel()
) {
    // Lifecycle 2.8 和 Compose 1.7 運行時會導致閃退
    // 問題說明與解決辦法：https://issuetracker.google.com/issues/336842920#comment8
    CompositionLocalProvider(
        androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold(
            modifier = modifier
        ) { paddingValues ->
            AnimalDetailContent(
                isLoading = uiState.isLoading,
                animal = uiState.animal!!,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
@Composable
fun AnimalDetailContent(
    isLoading: Boolean,
    animal: AnimalInfo,
    modifier: Modifier = Modifier
) {
    // 分隔符號
    val separate = stringResource(id = R.string.separate)
    val state = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state)
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp)
                )
            }
        } else {
            AnimalPic(picUrl = animal.aPic01Url)
            LabelAndContentItem(label = animal.aNameCh, content = animal.aNameEn)
            // 合併 alsoknown 跟 keywords (兩個都可能是別名)
            LabelAndContentItem(
                label = stringResource(id = R.string.alias),
                content = mergeStringsWithSeparator(
                    baseString = animal.aAlsoknown,
                    appendString = animal.aKeywords,
                    separator = separate
                )
            )
            LabelAndContentItem(
                label = stringResource(id = R.string.introduction),
                content = animal.aDiet
            )

            LabelAndContentItem(
                label = stringResource(id = R.string.feature),
                content = animal.aFeature
            )

            LabelAndContentItem(
                label = stringResource(id = R.string.behavior),
                content = animal.aBehavior
            )

            LabelAndContentItem(
                label = stringResource(
                    id = R.string.update_date,
                    DateUtils.formatData(
                        dateStr = animal.importdate.date,
                        oldPattern = DateUtils.PATTERN_yyyy_MM_dd_T_HH_mm_ss_SSSSSS_DASH,
                        newPattern = DateUtils.PATTERN_yyyy_MM_dd_SLASH,
                        timeZone = animal.importdate.timezone
                    )
                )
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimalPic(
    modifier: Modifier = Modifier,
    picUrl: String
) {
    Box(
        // 寬高比 4:3
        modifier = modifier
            .aspectRatio(4f / 3f)
            .fillMaxWidth()
    ) {
        GlideImage(
            model = picUrl,
            contentDescription = stringResource(R.string.image_animal_content_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun LabelAndContentItem(
    label: String,
    content: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp
        )

        content?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AnimalPicPreview() {
    MaterialTheme {
        AnimalPic(picUrl = demoPicUrl)
    }
}

@Preview(showBackground = true)
@Composable
fun LabelAndContentItemPreview() {
    MaterialTheme {
        LabelAndContentItem(label = "標籤", content = "內容")
    }
}

val demoPicUrl =
    "https://www.zoo.gov.tw/iTAP/03_Animals/PenguinHouse/KingPenguin/KingPenguin_Pic01.jpg"