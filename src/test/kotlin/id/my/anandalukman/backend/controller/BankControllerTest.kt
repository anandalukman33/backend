package id.my.anandalukman.backend.controller

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

/**
 * Testing ini berfungsi untuk mengetest RestApi
 * terutama pada requestnya, jadi seakan2 request tapi secara nyatanya tidak melakukan request
 * jadi hanya sebagai fake Request
 */

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

    // "@Autowired" => Menetapkan variabel mockMvc sebagai dependency injection
    @Autowired
    lateinit var mockMvc: MockMvc
    val url = "/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {

            // given
            mockMvc.get(url)
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
    @DisplayName("getBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {

            // given
            val accountNumber = "lukmanganteng"

            // when
            mockMvc.get("$url/lukmanganteng")

                    // then
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
            mockMvc.get("$url/$accountNumber")
            
            // then
                    .andDo { print() }
                    .andExpect { status { isNotFound() } }
        }
        
    }
}