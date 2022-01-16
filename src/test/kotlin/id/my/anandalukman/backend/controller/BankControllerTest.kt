package id.my.anandalukman.backend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import id.my.anandalukman.backend.model.BankBean
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

/**
 * Testing ini berfungsi untuk mengetest RestApi
 * terutama pada requestnya, jadi seakan2 request tapi secara nyatanya tidak melakukan request
 * jadi hanya sebagai fake Request
 */

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
        var mockMvc: MockMvc,
        var objectMapper: ObjectMapper,
) {

//     "@Autowired" => Menetapkan variabel mockMvc sebagai dependency injection
//    @Autowired
//    lateinit var mockMvc: MockMvc

//    @Autowired
//    lateinit var objectMapper: ObjectMapper

    val url = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {

            // given
            val performGetRequest = mockMvc.get(url)

            // then
            performGetRequest
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$[0].accountNumber") {
                            value("lukmanganteng")
                        }
                    }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {

            // given
            val accountNumber = "lukmanganteng"

            // when
            val performGetRequest = mockMvc.get("$url/$accountNumber")

            // then
            performGetRequest
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.trust") { value("3.14") }
                        jsonPath("$.transactionFee") { value("17") }
                    }
        }

        @Test
        fun `should return Not Found if the account number doesn't exist`() {

            // given
            val accountNumber = "doesn't_exist"

            // when
            val performGetRequest = mockMvc.get("$url/$accountNumber")

            // then
            performGetRequest
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add the new bank`() {

            // given
            val newBank = BankBean("Ananda Muhamad Lukman", 22.07, 1998)

            // when
            val performPostRequest = mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            val performGetRequest = mockMvc.get("$url/${newBank.accountNumber}")

            // then
            performPostRequest
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content {
                            contentType(MediaType.APPLICATION_JSON)
                            json(objectMapper.writeValueAsString(newBank))
                        }
//                        jsonPath("$.accountNumber") { value("Ananda Muhamad Lukman") }
//                        jsonPath("$.trust") { value("22.07") }
//                        jsonPath("$.transactionFee") { value("1998") }
                    }
            performGetRequest
                    .andExpect { content { json(objectMapper.writeValueAsString(newBank)) } }
        }

        @Test
        fun `should retun BAD REQUEST if bank with given account number already exists`() {

            // given
            val invalidBank = BankBean("1002", 4.15, 28)

            // when
            val performPostRequest = mockMvc.post(url) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPostRequest
                    .andDo { print() }
                    .andExpect { status { isBadRequest() } }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {

            // given
            val updateBanks = BankBean("lukmanganteng", 8.15, 88)

            // when
            val performPatchRequest = mockMvc.patch(url) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBanks)
            }

            val performGetRequest = mockMvc.get("$url/${updateBanks.accountNumber}")

            // then
            performPatchRequest
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content {
                            contentType(MediaType.APPLICATION_JSON)
                            json(objectMapper.writeValueAsString(updateBanks))
                        }
//                        jsonPath("$.accountNumber") { value("lukmanganteng") }
//                        jsonPath("$.trust") { value("8.15") }
//                        jsonPath("$.transactionFee") { value("88") }
                    }

            performGetRequest
                    .andExpect { content { json(objectMapper.writeValueAsString(updateBanks)) } }
        }


        @Test
        fun `should return BAD REQUEST if no bank with given account number exists`() {

            // given
            val invalidBank = BankBean("?????", 9.9, 9999)

            // when
            val performPatchRequest = mockMvc.patch(url) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            performPatchRequest
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
        }


    }
}