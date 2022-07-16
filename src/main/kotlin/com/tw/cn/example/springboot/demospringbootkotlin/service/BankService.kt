package com.tw.cn.example.springboot.demospringbootkotlin.service

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.BankDataSource
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = bankDataSource.retrieveBanks()
}