package id.my.anandalukman.backend.datasource

import id.my.anandalukman.backend.model.BankBean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface BankDataSource {

    fun retrieveBanks(): Collection<BankBean>
    fun retrieveBank(accountNumber: String): BankBean
    fun createBank(bankBean: BankBean): BankBean
    fun patchBank(bankBean: BankBean): BankBean
    fun deleteBank(accountNumber: String)

}