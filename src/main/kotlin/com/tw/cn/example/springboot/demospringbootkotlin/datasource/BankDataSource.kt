package com.tw.cn.example.springboot.demospringbootkotlin.datasource

import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBankById(id: String): Bank
    fun createBank(bank: Bank): Bank
}