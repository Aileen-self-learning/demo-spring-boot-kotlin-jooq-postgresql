package com.tw.cn.example.springboot.demospringbootkotlin.datasource

import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.Banks
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository

@Repository
class BankRepository(
    val dslContext: DSLContext,
    val bankList: MutableList<Bank>
){
    private val fee = Banks.BANKS.TRANSACTION_FEE
    private val trust = Banks.BANKS.TRUST
    private val number = Banks.BANKS.ACCOUNT_NUMBER

    fun findAll(): Collection<Bank> {
        dslContext
            .select()
            .from(Tables.BANKS)
            .forEach{
                bankList.add(it.into(Bank::class.java))
            }
        return this.bankList
    }

    fun retrieveBankById(id: String): Bank {
        val foundRecord = dslContext
            .selectFrom(Tables.BANKS)
            .where(number.eq(id))
            .fetchOne() ?: throw NoSuchElementException("Could not find bank with accountNumber $id")
        return foundRecord.into(Bank::class.java)
    }

    fun createBank(bank: Bank): Bank {
        try {
            val insertQuery = dslContext.insertQuery(Tables.BANKS)
            insertQuery.addValue(number, bank.accountNumber)
            insertQuery.addValue(trust, bank.trust)
            insertQuery.addValue(fee, bank.transactionFee)
            insertQuery.execute()
            return bank
        }catch (e: DuplicateKeyException) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists!")
        }
    }

    fun updateBank(bank: Bank): Bank {
        val foundRecord = dslContext
            .selectFrom(Tables.BANKS)
            .where(number.eq(bank.accountNumber))
            .fetchOne()
        foundRecord?: throw NoSuchElementException("Could not find bank with accountNumber ${bank.accountNumber}")
        dslContext
            .update(Tables.BANKS)
            .set(trust, bank.trust)
            .set(fee, bank.transactionFee)
            .where(number.eq(bank.accountNumber))
            .execute()
        return bank
    }

    fun deleteBankById(id: String) {
        dslContext.delete(Tables.BANKS)
            .where(number.eq(id))
            .execute()
    }
}