package com.example.momoexam.data

import com.example.momoexam.data.network.NetworkDataSourceImpl
import com.example.momoexam.utils.TestUtils.createApiBeanWithAnimalInfo
import com.example.momoexam.utils.TestUtils.createApiBeanWithAreaIntroductions
import com.example.momoexam.utils.TestUtils.testAnimalInfo
import com.example.momoexam.utils.TestUtils.testAreaIntroduction
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class OpenDataRepositoryImplTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    // Mock NetworkDataSource
    private var networkDataSource = mockk<NetworkDataSourceImpl>()
    private lateinit var repository: OpenDataRepositoryImpl

    @Before
    fun setUp() {
        repository = OpenDataRepositoryImpl(networkDataSource, testDispatcher)
    }

    @Test
    fun testGetAreaIntroduction() = testScope.runTest {
        // 模擬數據
        val mockAreaIntroduction = listOf(
            testAreaIntroduction,
            testAreaIntroduction
        )

        // 模擬 networkDataSource
        coEvery { networkDataSource.loadAreaIntroduction() } returns flow {
            emit(
                createApiBeanWithAreaIntroductions(mockAreaIntroduction)
            )
        }

        val result = repository.getAreaIntroduction().first()

        // 驗證結果
        assertEquals(mockAreaIntroduction, result)
    }


    @Test
    fun testGetAnimalInfo() = testScope.runTest {
        // 測試資料
        val mockAnimalInfo = listOf(
            testAnimalInfo,
            testAnimalInfo
        )

        // 模擬 networkDataSource
        coEvery { networkDataSource.loadAnimalInfo() } returns flow {
            emit(createApiBeanWithAnimalInfo(mockAnimalInfo))
        }

        val result = repository.getAnimalInfo().first()

        // 驗證資料
        assertEquals(mockAnimalInfo, result)
    }
}