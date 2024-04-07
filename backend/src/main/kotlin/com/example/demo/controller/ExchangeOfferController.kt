package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.ExchangeOffer
import com.example.demo.model.ExchangeOfferStatus
import com.example.demo.service.ExchangeOfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@CrossOrigin(value = ["http://localhost:3000", "http://207.246.119.200:3000"])
@RestController
@RequestMapping("v1/exchangeOffer")
class ExchangeOfferController {

    @Autowired
    lateinit var exchangeOfferService: ExchangeOfferService

    @PostMapping
    fun createExchangeOffer(principal: JwtAuthenticationToken, @RequestBody request: CreateExchangeOfferDTO): ResponseEntity<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return ResponseEntity.ok(ExchangeOfferDTO(exchangeOfferService.create(request, sub)))
    }

    @PatchMapping("/{id}")
    fun updateExchangeOffer( @RequestBody request: UpdateExchangeOfferRequest,principal: JwtAuthenticationToken): ResponseEntity<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        val updatedExchangeOffer = exchangeOfferService.updateExchangeOffer(request, sub)
        return ResponseEntity.ok(ExchangeOfferDTO(updatedExchangeOffer))
    }

    @GetMapping
    fun getAllExchangeOffers(pageable: Pageable): List<ExchangeOfferDTO> {
        return exchangeOfferService.getAll(pageable).map { exchangeOffer -> ExchangeOfferDTO(exchangeOffer) }
    }

  
    @GetMapping("/bidder/me")
    fun getMyExchangeOffers(principal: JwtAuthenticationToken): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByBidderSub(sub)
    }

    // Todos los EO recibidos por un usuario
    @GetMapping("/receiver/me")
    fun getExchangeOffersForMe(principal: JwtAuthenticationToken): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByReceiverSub(sub)
    }

    // Todos los EO ofreciendo una barajita
    @GetMapping("/offered/{id}")
    fun getExchangeOfferByCard(@PathVariable id: Long): List<ExchangeOfferDTO> {
        return exchangeOfferService.getByCardId(id)
    }

    // Todos los EO que ha recibido un Exchange Request
    @GetMapping("/ER/{id}")
    fun getExchangeOfferByER(@PathVariable id: Long): List<ExchangeOfferDTO> {
        return exchangeOfferService.getByExchangeRequest(id)
    }

    // Todos los EO de un usuario ofreciendo una barajita particular
    @GetMapping("/bidder/me/card/{cardId}")
    fun getMyExchangeOffersByCard(principal: JwtAuthenticationToken, @PathVariable cardId: Long): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByBidderSubAndCardId(sub, cardId)
    }

    // Todos los EO creados en cierto rango de tiempo
    @GetMapping("/start/{start}/end/{end}")
    fun getExchangeOfferByDateRange(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: Date,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: Date
    ): List<ExchangeOfferDTO> {
        return exchangeOfferService.getByDateRange(start, end)
    }

    // Ofertas por sub en un rango de tiempo
    @GetMapping("/bidder/me/start/{start}/end/{end}")
    fun getMyExchangeOffersByDateRange(principal: JwtAuthenticationToken, 
                                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: Date, 
                                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: Date): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByBidderSubAndDateRange(sub, start, end)
    }

    // Ofertas recibidas en un rango de tiempo identificado por sub
    @GetMapping("/receiver/me/start/{start}/end/{end}")
    fun getExchangeOffersForMeByDateRange(principal: JwtAuthenticationToken, 
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: Date, 
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: Date): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByReceiverSubAndDateRange(sub, start, end)
    }

        // *- los que no funcionan -* //
        
    // Todos los EO con un status particular
    @GetMapping("/status/{status}")
    fun getExchangeOfferByStatus(@PathVariable status: ExchangeOfferStatus): List<ExchangeOfferDTO> {
        return exchangeOfferService.getByStatus(status)
    }

    // Todos los EO creados por un usuario y con un estatus
    @GetMapping("/bidder/me/status/{status}")
    fun getMyExchangeOffersByStatus(principal: JwtAuthenticationToken, @PathVariable status: ExchangeOfferStatus): List<ExchangeOfferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        return exchangeOfferService.getByBidderSubAndStatus(sub, status)
    }
}