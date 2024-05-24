package com.example.momoexam.ui.animal

import androidx.lifecycle.SavedStateHandle
import com.example.momoexam.utils.MainCoroutineRule
import com.example.momoexam.utils.TestUtils.testAnimalInfo
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
class AnimalDetailViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AnimalDetailViewModel

    @Before
    fun setUp() {
        val savedStateHandle = SavedStateHandle(
            mapOf(ARG_ANIMAL to testAnimalInfo)
        )
        viewModel = AnimalDetailViewModel(savedStateHandle)
    }

    @Test
    fun testGetAnimal_Success() = runTest {
        // 驗證
        val result = viewModel.uiState.first()

        assertEquals(testAnimalInfo, result.animal)
        assertFalse(result.isLoading)
    }
}