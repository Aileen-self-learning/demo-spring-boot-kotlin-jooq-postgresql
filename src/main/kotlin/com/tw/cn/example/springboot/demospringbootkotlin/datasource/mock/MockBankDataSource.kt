package com.tw.cn.example.springboot.demospringbootkotlin.datasource.mock

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.BankDataSource
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf(
        Bank("123456", 3.14, 17),
        Bank("123457", 4.23, 20),
        Bank("123458", 2.11, 10)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBankById(id: String): Bank =
        banks.firstOrNull { it.accountNumber==id }
            ?:throw NoSuchElementException("Could not find a bank with account number $id")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists!")
        }
        banks.add(bank)
        return bank
    }
}