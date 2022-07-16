package com.tw.cn.example.springboot.demospringbootkotlin.datasource.mock

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.BankDataSource
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {
    val banks = listOf(Bank("", 0.0, 1))

    override fun getBanks(): Collection<Bank> = banks
}