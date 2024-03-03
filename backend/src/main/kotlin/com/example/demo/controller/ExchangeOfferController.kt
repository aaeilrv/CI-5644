import com.example.demo.controller.dto.CreateExchangeOfferRequest
import com.example.demo.controller.dto.CreateExchangeRequestRequest
import com.example.demo.controller.dto.ExchangeOfferDTO
import com.example.demo.controller.dto.ExchangeRequestDTO
import com.example.demo.model.ExchangeOffer
import com.example.demo.model.ExchangeOfferStatus
import com.example.demo.model.ExchangeRequest
import com.example.demo.service.ExchangeOfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(value = ["http://localhost:3000"])
@RestController
@RequestMapping("v1/exchangeOffer")
class ExchangeOfferController {

    @Autowired
    lateinit var exchangeOfferService: ExchangeOfferService

    @PostMapping
    fun createExchangeOffer(@RequestBody request: CreateExchangeOfferRequest): ResponseEntity<ExchangeOfferDTO> {
        return ResponseEntity.ok(ExchangeOfferDTO(exchangeOfferService.create(ExchangeOffer(request))))
    }

    @GetMapping
    fun getAllExchangeOffers(pageable: Pageable): List<ExchangeOfferDTO> {
        return exchangeOfferService.getAll(pageable).map { ExchangeOfferDTO(it) }
    }

    @GetMapping("/{id}")
    fun getExchangeOfferById(@PathVariable id: Long): ResponseEntity<ExchangeOfferDTO> {
        val exchangeOfferOpt = exchangeOfferService.getById(id)
        if (exchangeOfferOpt.isPresent) {
            return ResponseEntity.ok(ExchangeOfferDTO(exchangeOfferOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange offer with $id not found.")
        }
    }

    // Todos los EO de un estatus
    @GetMapping("/{status}")
    fun getExchangeOfferByStatus(@PathVariable status: ExchangeOfferStatus): ResponseEntity<ExchangeOfferDTO> {
        val exchangeOfferOpt = exchangeOfferService.getByStatus(status)
        if (exchangeOfferOpt.isPresent) {
            return ResponseEntity.ok(ExchangeOfferDTO(exchangeOfferOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange offers with $status not found.")
        }
    }
}