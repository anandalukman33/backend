package id.my.anandalukman.backend.datasource

import id.my.anandalukman.backend.model.BankBean

interface BankDataSource {

    fun retrieveBanks(): Collection<BankBean>
    fun retrieveBank(accountNumber: String): BankBean
    fun createBank(bankBean: BankBean): BankBean
    fun patchBank(bankBean: BankBean): BankBean

}