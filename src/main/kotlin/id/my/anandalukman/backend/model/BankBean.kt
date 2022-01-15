package id.my.anandalukman.backend.model


/**
 * Reference Api
 * https://developer.thenewboston.com/api/bank-api/banks
 */

data class BankBean(
        private var accountNumber: String,
        private var trust: Double,
        private var transactionFee: Int,
)