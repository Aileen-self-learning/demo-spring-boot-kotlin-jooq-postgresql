package com.tw.cn.example.springboot.demospringbootkotlin.datasource

import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
}