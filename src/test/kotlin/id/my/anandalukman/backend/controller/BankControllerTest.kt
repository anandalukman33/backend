package id.my.anandalukman.backend.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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
    
    @Test
    fun `should return all banks`() {
    
        // given
        mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value("lukman ganteng")
                    }
                }
        
        // when
        
        
        // then
        
    }
    
}