package id.my.anandalukman.backend.controller

import id.my.anandalukman.backend.model.BankBean
import id.my.anandalukman.backend.model.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<BankBean> = service.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(CREATED)
    fun addBank(@RequestBody bankBean: BankBean): BankBean = service.addBank(bankBean)

    @PatchMapping
    @ResponseStatus(OK)
    fun patchBank(@RequestBody bankBean: BankBean): BankBean = service.patchBank(bankBean)

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String): Unit = service.deleteBank(accountNumber)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(e.message, NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
            ResponseEntity(e.message, BAD_REQUEST)
}