package com.example.momoexam.data.network


import com.example.momoexam.data.api.ApiService
import com.example.momoexam.utils.TestUtils.testAnimalInfo
import com.example.momoexam.utils.TestUtils.testAreaIntroduction
import com.example.momoexam.vo.ApiBean
import com.example.momoexam.vo.ResultBean
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class NetworkDataSourceImplTest {
    // 使用 testDispatcher 控制協程
    private val testDispatcher = UnconfinedTestDispatcher()

    // Mock ApiService
    private val apiService = mockk<ApiService>()
    private lateinit var networkDataSource: NetworkDataSourceImpl

    @Before
    fun setUp() {
        networkDataSource = NetworkDataSourceImpl(apiService, testDispatcher)
    }

    @Test
    fun testLoadAreaIntroduction_return_expected_data() = runTest {
        // 準備測試資料
        val expectedData = ApiBean(
            resultBean = ResultBean(
                count = 16,
                limit = 20,
                offset = 0,
                results = listOfNotNull(testAreaIntroduction),
                sort = ""
            )
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
    fun testLoadAnimalInfo_return_expected_data() = runTest {
        // 準備測試資料
        val expectedData = ApiBean(
            resultBean = ResultBean(
                count = 272,
                limit = 20,
                offset = 0,
                results = listOfNotNull(testAnimalInfo),
                sort = ""
            )
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

