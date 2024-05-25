package com.example.momoexam.ui.home

import androidx.lifecycle.SavedStateHandle
import com.example.momoexam.R
import com.example.momoexam.utils.FakeOpenDataRepository
import com.example.momoexam.utils.MainCoroutineRule
import com.example.momoexam.utils.TestUtils.testAreaIntroduction2
import com.example.momoexam.utils.TestUtils.testAreaIntroduction1
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeRepository: FakeOpenDataRepository

    @Before
    fun setUp() {
        fakeRepository = FakeOpenDataRepository()
        fakeRepository.addAreaIntroductions(
            testAreaIntroduction1,
            testAreaIntroduction2
        )
        val savedStateHandle = SavedStateHandle()
        viewModel = HomeViewModel(savedStateHandle, fakeRepository)
    }

    @Test
    fun testGetAreaIntroduction_Success() = runTest {
        // 準備模擬的成功數據
        val mockAreaIntroductions = listOf(
            testAreaIntroduction1,
            testAreaIntroduction2
        )

        // 驗證
        val result = viewModel.uiState.first()

        assertEquals(mockAreaIntroductions, result.items)
        assertFalse(result.isLoading)
        assertNull(result.message)
    }

    @Test
    fun testGetAreaIntroduction_repositoryError() = runTest {
        // 設定 FakeRepository Throw Error
        fakeRepository.setShouldThrowError(true)

        val uiState = viewModel.uiState
        // 測試錯誤並測試訊息
        assertEquals(emptyList<AreaIntroduction>(), uiState.value.items)
        assertFalse(uiState.first().isLoading)
        assertEquals(R.string.load_area_error, uiState.first().message)
    }

}