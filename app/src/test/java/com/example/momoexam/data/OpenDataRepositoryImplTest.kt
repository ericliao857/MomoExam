package com.example.momoexam.data

import com.example.momoexam.data.network.NetworkDataSourceImpl
import com.example.momoexam.utils.TestUtils.createApiBeanWithAnimalInfo
import com.example.momoexam.utils.TestUtils.createApiBeanWithAreaIntroductions
import com.example.momoexam.utils.TestUtils.testAnimalInfo
import com.example.momoexam.utils.TestUtils.testAreaIntroduction1
import com.example.momoexam.utils.TestUtils.testAreaIntroduction2
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

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
            testAreaIntroduction1,
            testAreaIntroduction2
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
    fun testGetAreaIntroduction_Error() = testScope.runTest {
        coEvery {
            networkDataSource.loadAreaIntroduction()
        } returns flow {
            throw RuntimeException("Network error")
        }

        val result = repository.getAreaIntroduction()
            .catch { emit(emptyList()) }
            .first()

        assertEquals(emptyList<AreaIntroduction>(), result)
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

    @Test
    fun testGetAnimalInfo_Error() = testScope.runTest {
        coEvery {
            networkDataSource.loadAnimalInfo()
        } returns flow {
            throw RuntimeException("Network error")
        }

        val result = repository.getAnimalInfo()
            .catch { emit(emptyList()) }
            .first()

        assertEquals(emptyList<AnimalInfo>(), result)
    }
}