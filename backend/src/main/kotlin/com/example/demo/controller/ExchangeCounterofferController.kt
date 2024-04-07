package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.ExchangeCounteroffer
import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.service.ExchangeCounterofferService
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
@RequestMapping("v1/exchangeCounteroffer")
class ExchangeCounterofferController {

    @Autowired
    lateinit var exchangeCounterofferService: ExchangeCounterofferService

    @PostMapping
    fun createExchangeOffer(principal: JwtAuthenticationToken, @RequestBody request: CreateExchangeCounterofferDTO): ResponseEntity<ExchangeCounterofferDTO> {
        val userSub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffer = exchangeCounterofferService.create(request, userSub)
        return ResponseEntity.ok(ExchangeCounterofferDTO(exchangeCounteroffer))
}

    @PatchMapping("/{id}")
    fun updateExchangeCounteroffer(@RequestBody request: UpdateExchangeCounterofferRequest, principal: JwtAuthenticationToken): ResponseEntity<ExchangeCounterofferDTO> {
        val sub = principal.tokenAttributes["sub"] as String
        val updatedExchangeCounteroffer = exchangeCounterofferService.updateExchangeCounteroffer(request, sub)
        return ResponseEntity.ok(ExchangeCounterofferDTO(updatedExchangeCounteroffer))
    }

    @GetMapping
    fun getAllExchangeCounteroffers(pageable: Pageable): List<ExchangeCounterofferDTO> {
        return exchangeCounterofferService.getAll(pageable).map {ExchangeCounterofferDTO(it)}
    }

    @GetMapping("/{id}")
    fun getExchangeCounterofferById(@PathVariable id: Long): ResponseEntity<ExchangeCounterofferDTO> {
        val exchangeCounterOfferOpt = exchangeCounterofferService.getById(id)
        if (exchangeCounterOfferOpt.isPresent) {
            return ResponseEntity.ok(ExchangeCounterofferDTO(exchangeCounterOfferOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange counteroffer with $id not found.")
        }
    }

    // Todos los ECO creados por un usuario
    @GetMapping("/creator/me")
    fun getExchangeCounteroffersByCreator(principal: JwtAuthenticationToken): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByCreatorSub(sub)
        return ResponseEntity.ok(exchangeCounteroffers)
    }

    // Todos los ECO recibidos por el usuario autenticado
    @GetMapping("/receiver/me")
    fun getExchangeCounteroffersByReceiver(principal: JwtAuthenticationToken): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByReceiverSub(sub)
        return ResponseEntity.ok(exchangeCounteroffers)
    }

    // Todos los ECO de una barajita específica
    @GetMapping("/card/{id}")
    fun getExchangeCounterofferByCard(@PathVariable id: Long): List<ExchangeCounterofferDTO> {
        return exchangeCounterofferService.getByCard(id)
    }

    // Todos los ECO que ha recibido un Exchange Offer
    @GetMapping("/EO/{id}")
    fun getExchangeCounterofferByEO(@PathVariable id: Long): List<ExchangeCounterofferDTO> {
        return exchangeCounterofferService.getByEO(id)
    }

    // Todos los ECO de un Exchange Request
    @GetMapping("/ER/{id}")
    fun getExchangeCounterofferByER(@PathVariable id: Long): List<ExchangeCounterofferDTO> {
        return exchangeCounterofferService.getByER(id)
    }

    // Todos los ECO creados por el usuario autenticado
    @GetMapping("/creator/me/start/{start}/end/{end}")
    fun getExchangeCounterofferByCreatorAndDateRange(
        principal: JwtAuthenticationToken, 
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: Date,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: Date
    ): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByCreatorSubAndDateRange(sub, start, end)
        return ResponseEntity.ok(exchangeCounteroffers)
    }

    // Todos los ECO recibidos por el usuario autenticado
    @GetMapping("/receiver/me/start/{start}/end/{end}")
    fun getExchangeCounterofferByReceiverAndDateRange(
        principal: JwtAuthenticationToken, 
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) start: Date,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) end: Date
    ): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByReceiverSubAndDateRange(sub, start, end)
        return ResponseEntity.ok(exchangeCounteroffers)
    }

    // *- los que no funcionan -* //

    // Todos los ECO con un estatus
    @GetMapping("/status/{status}")
    fun getExchangeCounterofferByStatus(@PathVariable status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        return exchangeCounterofferService.getByStatus(status)
    }

    @GetMapping("/creator/me/status/{status}")
    fun getMyExchangeCounteroffersByStatus(principal: JwtAuthenticationToken, @PathVariable status: ExchangeRequestStatus): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByCreatorSubAndStatus(sub, status)
        return ResponseEntity.ok(exchangeCounteroffers)
    }


    // Todos los ECO con cierto estatus recibidos por el usuario autenticado
    @GetMapping("/receiver/me/status/{status}")
    fun getExchangeCounteroffersForMeByStatus(principal: JwtAuthenticationToken, @PathVariable status: ExchangeRequestStatus): ResponseEntity<List<ExchangeCounterofferDTO>> {
        val sub = principal.tokenAttributes["sub"] as String
        val exchangeCounteroffers = exchangeCounterofferService.getByReceiverSubAndStatus(sub, status)
        return ResponseEntity.ok(exchangeCounteroffers)
    }
}