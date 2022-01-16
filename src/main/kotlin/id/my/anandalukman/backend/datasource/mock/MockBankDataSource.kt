package id.my.anandalukman.backend.datasource.mock

import id.my.anandalukman.backend.datasource.BankDataSource
import id.my.anandalukman.backend.model.BankBean
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {

    val banks = mutableListOf(
            BankBean("lukmanganteng", 3.14, 17),
            BankBean("1002", 4.15, 28),
            BankBean("1003", 5.16, 39)
    )

    override fun retrieveBanks(): Collection<BankBean> = banks
    override fun retrieveBank(accountNumber: String): BankBean  = banks.firstOrNull {
        it.accountNumber == accountNumber
    } ?: throw NoSuchElementException("Tidak dapat menemukan data dari $accountNumber")

    override fun createBank(bankBean: BankBean): BankBean {

        if (banks.any { it.accountNumber == bankBean.accountNumber }) {
            throw IllegalArgumentException("Data ${bankBean.accountNumber} sudah ada di database")
        }

        banks.add(bankBean)

        return bankBean
    }

//    Awalnya seperti ini, lalu biar simpel di convert menjadi expression body, seperti diatas
//    override fun getBanks(): Collection<BankBean> {
//        return banks
//    }

}

/**
 * Create Test
 * CTRL + SHIFT + T
 * Lalu arahkan class atau function yg ingin di test
 */