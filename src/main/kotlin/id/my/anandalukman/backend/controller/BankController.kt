package id.my.anandalukman.backend.controller

import id.my.anandalukman.backend.model.BankBean
import id.my.anandalukman.backend.model.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<BankBean> = service.getBanks()
}