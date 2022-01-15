package id.my.anandalukman.backend.model


/**
 * Reference Api
 * https://developer.thenewboston.com/api/bank-api/banks
 */

data class BankBean(
        var accountNumber: String,
        var trust: Double,
        var transactionFee: Int,
)