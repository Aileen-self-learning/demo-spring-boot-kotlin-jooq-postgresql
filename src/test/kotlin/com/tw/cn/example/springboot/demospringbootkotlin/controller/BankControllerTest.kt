package com.tw.cn.example.springboot.demospringbootkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tw.cn.example.springboot.demospringbootkotlin.model.Bank
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.*

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
        @WithMockUser
        fun `should return all banks`() {
            // when/then
            mockMvc.get("/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){
                        value("qwerty")
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /banks/accountNumber")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser
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
                    jsonPath("$.trust") {value(43.99)}
                    jsonPath("$.transactionFee") {value(20)}
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
    @WithMockUser
    inner class PostNewBank {
        @Test
        fun `should create a new bank`() {
            //given
            val newBank = Bank("123459", 58.65, 12)
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
                    jsonPath("$.trust") {value(58.65)}
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
    
    @Nested
    @DisplayName("PATCH /banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser
    inner class UpdateExistingBank {
        @Test
        fun `should update existing bank`() {
            //given
            val updatedBank = Bank("123459", 85.0, 12)
            //when
            val performPatch = mockMvc.patch("/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
            //then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }

                }

            mockMvc.get("/banks/${updatedBank.accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
        }

        @Test
        fun `should return bad request when trying to update non-existing bank`() {
            //given
            val invalidBank = Bank("does not exist", 65.98, 34)
            //when
            val performPatch = mockMvc.patch("/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("DELETE /banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser
    inner class deleteBankByAccountNumber {
        @Test
        fun `should delete an existing bank`() {
            //given
            val accountNumber = "123459"
            //when
            mockMvc.delete("/banks/${accountNumber}")
            //then
                .andDo { print() }
                .andExpect { status { isNoContent() } }

            mockMvc.get("/bank/${accountNumber}")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}