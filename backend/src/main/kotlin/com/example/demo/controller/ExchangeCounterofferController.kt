package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.ExchangeCounteroffer
import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.service.ExchangeCounterofferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@CrossOrigin(value = ["http://localhost:3000"])
@RestController
@RequestMapping("v1/exchangeCounteroffer")
class ExchangeCounterofferController {

    @Autowired
    lateinit var exchangeCounterofferService: ExchangeCounterofferService

    @PostMapping
    fun createExchangeCounteroffer(@RequestBody request: CreateExchangeCounterofferRequest): ResponseEntity<ExchangeCounterofferDTO> {
        return ResponseEntity.ok(ExchangeCounterofferDTO(exchangeCounterofferService.create(ExchangeCounteroffer(request))))
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

    @GetMapping("/{status}")
    fun getExchangeCounterofferByStatus(@PathVariable status: ExchangeRequestStatus): ResponseEntity<ExchangeCounterofferDTO> {
        val exchangeCounterOfferOpt = exchangeCounterofferService.getByStatus(status)
        if (exchangeCounterOfferOpt.isPresent) {
            return ResponseEntity.ok(ExchangeCounterofferDTO(exchangeCounterOfferOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange offer with $status not found.")
        }
    }
}