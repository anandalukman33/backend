package id.my.anandalukman.backend.model.service

import id.my.anandalukman.backend.datasource.BankDataSource
import id.my.anandalukman.backend.model.BankBean
import org.springframework.stereotype.Service

@Service
class BankService (private val dataSource: BankDataSource) {
    fun getBanks(): Collection<BankBean> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): BankBean = dataSource.retrieveBank(accountNumber)
    fun addBank(bankBean: BankBean): BankBean = dataSource.createBank(bankBean)
    fun patchBank(bankBean: BankBean): BankBean = dataSource.patchBank(bankBean)
    fun deleteBank(accountNumber: String) : Unit = dataSource.deleteBank(accountNumber)
}