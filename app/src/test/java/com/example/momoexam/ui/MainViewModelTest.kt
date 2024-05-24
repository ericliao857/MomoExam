package com.example.momoexam.ui

import com.example.momoexam.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun testSetToolbarTitle() = runTest {
        // Initial state should be empty title
        var currentState = viewModel.uiState.first()
        assertEquals("", currentState.toolbarTitle)

        // Update the toolbar title
        val newTitle = "臺北市立動物園"
        viewModel.setToolbarTitle(newTitle)

        // Verify the state is updated
        currentState = viewModel.uiState.first()
        assertEquals(newTitle, currentState.toolbarTitle)
    }
}
