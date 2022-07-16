package com.tw.cn.example.springboot.demospringbootkotlin.service

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    private val bankDataSource: BankDataSource = mockk(relaxed = true)

    private val bankService: BankService = BankService(bankDataSource)

    @Test
    fun `should call its data source to retrieve data`() {
        //when
        bankService.getBanks()
        //then
        verify(exactly = 1) { bankDataSource.retrieveBanks() }
    }
}