package com.tw.cn.example.springboot.demospringbootkotlin.datasource

import org.jooq.DSLContext
import org.jooq.generated.Tables
import org.jooq.generated.tables.Users
import org.springframework.stereotype.Repository

@Repository
class UserRepository(val dslContext: DSLContext) {

    private val users = Tables.USERS
//    private val password = Users.USERS.PASSWORD
    private val name = Users.USERS.NAME
//    private val auth = Users.USERS.AUTH

    fun findUserByName(username: String): org.jooq.generated.tables.pojos.Users {
        val foundRecord = dslContext
            .selectFrom(users)
            .where(name.eq(username))
            .fetchOne() ?: throw NoSuchElementException("Could not find user with user name $username")
        return foundRecord.into(org.jooq.generated.tables.pojos.Users::class.java)
    }
}