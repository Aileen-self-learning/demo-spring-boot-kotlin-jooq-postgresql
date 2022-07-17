package com.tw.cn.example.springboot.demospringbootkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){

    @Nested
    @DisplayName("GET /banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks{
        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get("/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){
                        value("123456")
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /banks/accountNumber")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBankById{
        @Test
        fun `should return bank with given account number`() {
            //given
            val accountNumber = 123456
            //when/then
            mockMvc.get("/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {value("123456")}
                    jsonPath("$.trust") {value(3.14)}
                    jsonPath("$.transactionFee") {value(17)}
                }
        }

        @Test
        fun `should return NOT FOUND when accountNumber does not exist`() {
            //given
            val accountNumber = "does not exist"
            //when
            mockMvc.get("/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should create a new bank`() {
            //given
            val newBank = Bank("123459", 5.65, 12)
            //when
            val performPost = mockMvc.post("/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") {value("123459")}
                    jsonPath("$.trust") {value(5.65)}
                    jsonPath("$.transactionFee") {value(12)}
                }

        }
        
        @Test
        fun `should return BAD REQUEST if given account number already exists`() {
            //given
            val invalidBank = Bank("123456", 5.65, 12)
            //when
            val performPost = mockMvc.post("/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }
}