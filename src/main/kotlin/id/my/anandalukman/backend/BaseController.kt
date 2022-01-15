package id.my.anandalukman.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController                     // Inisialisasi supaya SpringBoot tau class ini sebagai Controller
@RequestMapping("api/lukmantest")   // Handling Rest Request
class BaseController {
    private var exampleString: String? = null

    @GetMapping("/test")            // set name of endpoint
    fun baseController(): String {
        exampleString = "Halo ini adalah endpoint"
        return exampleString as String
    }

}