package com.tw.cn.example.springboot.demospringbootkotlin.service

import com.tw.cn.example.springboot.demospringbootkotlin.datasource.BankRepository
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val repository: BankRepository) {
    fun getBanks(): Collection<Bank> = repository.findAll()
    fun getBankById(id: String): Bank {
        return repository.retrieveBankById(id)
    }
    fun addBank(bank: Bank): Bank = repository.createBank(bank)
    fun updateBank(bank: Bank): Bank = repository.updateBank(bank)
    fun deleteBankById(id: String) : Unit = repository.deleteBankById(id)
}