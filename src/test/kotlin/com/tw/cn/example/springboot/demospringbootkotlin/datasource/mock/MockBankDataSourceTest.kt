package com.tw.cn.example.springboot.demospringbootkotlin.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    private val mockBankDataSource: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        //when
        val banks = mockBankDataSource.getBanks()
        //then
        assertThat(banks).isNotEmpty
    }
}
