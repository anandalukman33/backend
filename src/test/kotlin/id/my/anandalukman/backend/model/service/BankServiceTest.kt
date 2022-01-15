package id.my.anandalukman.backend.model.service

import id.my.anandalukman.backend.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    // "relaxed = true" => Setiap kali function dipanggil akan selalu memberikan default value
    private var dataSource: BankDataSource = mockk(relaxed = true)
    private var bankService = BankService(dataSource)

    @Test
    fun `should should call it's data source to retrieve banks`() {
    
        // given
        //every { dataSource.retrieveBanks() } returns emptyList()
        
        // when
        bankService.getBanks()
        
        // then
        verify(exactly = 1) { dataSource.retrieveBanks() } // Data test hanya boleh call sekali saja.
    }
    
    
}