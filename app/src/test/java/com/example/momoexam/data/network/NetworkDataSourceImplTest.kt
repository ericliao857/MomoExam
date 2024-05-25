package com.example.momoexam.data.network


import com.example.momoexam.data.api.ApiService
import com.example.momoexam.utils.TestUtils.createApiBeanWithAnimalInfo
import com.example.momoexam.utils.TestUtils.createApiBeanWithAreaIntroductions
import com.example.momoexam.utils.TestUtils.testAnimalInfo
import com.example.momoexam.utils.TestUtils.testAreaIntroduction1
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NetworkDataSourceImplTest {
    // 使用 testDispatcher 控制協程
    private val testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    // Mock ApiService
    private val apiService = mockk<ApiService>()
    private lateinit var networkDataSource: NetworkDataSourceImpl

    @Before
    fun setUp() {
        networkDataSource = NetworkDataSourceImpl(apiService, testDispatcher)
    }

    @Test
    fun testLoadAreaIntroduction_return_expected_data() = testScope.runTest {
        // 準備測試資料
        val expectedData = createApiBeanWithAreaIntroductions(
            listOfNotNull(testAreaIntroduction1)
        )

        // MockK行為
        coEvery { apiService.getAreaIntroduction() } returns expectedData

        // 測試資料
        val result = networkDataSource.loadAreaIntroduction().first()

        // 驗證
        assertNotNull(result)
        assertEquals(expectedData, result)
    }

    @Test
    fun testLoadAnimalInfo_return_expected_data() = testScope.runTest {
        // 準備測試資料
        val expectedData = createApiBeanWithAnimalInfo(
            listOfNotNull(testAnimalInfo)
        )

        // MockK
        coEvery { apiService.getAnimalInfo() } returns expectedData

        // 測試
        val result = networkDataSource.loadAnimalInfo().first()

        // 驗證
        assertNotNull(result)
        assertEquals(expectedData, result)
    }

}

