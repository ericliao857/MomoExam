package com.example.momoexam.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.example.momoexam.R
import com.example.momoexam.utils.FakeOpenDataRepository
import com.example.momoexam.utils.MainCoroutineRule
import com.example.momoexam.utils.TestUtils.testAnimalInfo
import com.example.momoexam.utils.TestUtils.testAreaIntroduction2
import com.example.momoexam.utils.TestUtils.testAreaIntroduction1
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel
    private lateinit var fakeRepository: FakeOpenDataRepository

    @Before
    fun setUp() {
        fakeRepository = FakeOpenDataRepository()
        fakeRepository.addAreaIntroductions(
            testAreaIntroduction1,
            testAreaIntroduction2
        )
        fakeRepository.addAnimalInfos(
            testAnimalInfo
        )
        val savedStateHandle = SavedStateHandle(
            mapOf(ARG_AREA to testAreaIntroduction1)
        )
        viewModel = DetailViewModel(savedStateHandle, fakeRepository)
    }

    @Test
    fun testGetAnimalInfo_filterSuccess() = runTest {
        // 準備模擬的成功數據
        val mockAnimalInfo = listOf(
            testAnimalInfo
        )

        // 驗證
        val result = viewModel.uiState.first()

        assertEquals(testAreaIntroduction1, result.area)
        assertEquals(mockAnimalInfo, result.animals)
        assertFalse(result.isLoading)
        assertNull(result.message)
    }

    @Test
    fun testGetAnimalInfo_repositoryError() = runTest {
        // 設定 FakeRepository Throw Error
        fakeRepository.setShouldThrowError(true)

        val result = viewModel.uiState.first()
        // 測試錯誤並測試訊息
        assertEquals(testAreaIntroduction1, result.area)
        assertEquals(emptyList<AnimalInfo>(), result.animals)
        assertFalse(result.isLoading)
        assertEquals(R.string.load_animal_error, result.message)
    }

    /**
     * 測試館區找不到動物的情境
     */
    @Test
    fun testGetAnimalInfo_notFoundAnimal() = runTest {
        // 設定其他館區
        val savedStateHandle = SavedStateHandle(
            mapOf(ARG_AREA to testAreaIntroduction2)
        )
        viewModel = DetailViewModel(savedStateHandle, fakeRepository)

        val result = viewModel.uiState.first()
        // 測試錯誤並測試訊息
        assertEquals(testAreaIntroduction2, result.area)
        assertEquals(emptyList<AnimalInfo>(), result.animals)
        assertFalse(result.isLoading)
    }
}