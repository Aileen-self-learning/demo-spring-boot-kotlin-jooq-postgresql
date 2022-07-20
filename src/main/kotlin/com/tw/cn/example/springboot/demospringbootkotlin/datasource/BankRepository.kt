package com.tw.cn.example.springboot.demospringbootkotlin.datasource

import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository

@Repository
class BankRepository(
    val dslContext: DSLContext,
    val bankList: MutableList<Bank>
){
    private val BANK_TABLE = DSL.table("banks")
    private val TRANSACTION_FEE = DSL.field("transaction_fee")
    private val TRUST = DSL.field("trust")
    private val ACCOUNT_NUMBER = DSL.field("account_number")

    fun findAll(): Collection<Bank> {
        dslContext
            .select()
            .from(BANK_TABLE)
            .forEach{
                bankList.add(it.into(Bank::class.java))
            }
        return this.bankList
    }

    fun retrieveBankById(id: String): Bank {
        val foundRecord = dslContext
            .selectFrom(BANK_TABLE)
            .where(ACCOUNT_NUMBER.eq(id))
            .fetchOne() ?: throw NoSuchElementException("Could not find bank with accountNumber $id")
        return foundRecord.into(Bank::class.java)
    }

    fun createBank(bank: Bank): Bank {
        try {
            val insertQuery = dslContext.insertQuery(BANK_TABLE)
            insertQuery.addValue(ACCOUNT_NUMBER, bank.accountNumber)
            insertQuery.addValue(TRUST, bank.trust)
            insertQuery.addValue(TRANSACTION_FEE, bank.transactionFee)
            insertQuery.execute()
            return bank
        }catch (e: DuplicateKeyException) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists!")
        }
    }

    fun updateBank(bank: Bank): Bank {
        val foundRecord = dslContext
            .selectFrom(BANK_TABLE)
            .where(ACCOUNT_NUMBER.eq(bank.accountNumber))
            .fetchOne()
        foundRecord?: throw NoSuchElementException("Could not find bank with accountNumber ${bank.accountNumber}")
        dslContext
            .update(BANK_TABLE)
            .set(TRUST, bank.trust)
            .set(TRANSACTION_FEE, bank.transactionFee)
            .where(ACCOUNT_NUMBER.eq(bank.accountNumber))
            .execute()
        return bank
    }

    fun deleteBankById(id: String) {
        dslContext.delete(BANK_TABLE)
            .where(ACCOUNT_NUMBER.eq(id))
            .execute()
    }
}