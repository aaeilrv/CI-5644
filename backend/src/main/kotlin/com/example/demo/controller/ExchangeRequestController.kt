package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.ExchangeRequest
import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.service.ExchangeRequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@CrossOrigin(value = ["http://localhost:3000"])
@RestController
@RequestMapping("v1/exchangeRequest")
class ExchangeRequestController {

    @Autowired
    lateinit var exchangeRequestService: ExchangeRequestService

    @PostMapping
    fun createExchangeRequest(@RequestBody request: CreateExchangeRequestRequest): ResponseEntity<ExchangeRequestDTO> {
        return ResponseEntity.ok(ExchangeRequestDTO(exchangeRequestService.create(ExchangeRequest(request))))
    }

    @GetMapping
    fun getAllExchangeRequests(pageable: Pageable): List<ExchangeRequestDTO> {
        return exchangeRequestService.getAll(pageable).map {ExchangeRequestDTO(it)}
    }

    @GetMapping("/{id}")
    fun getExchangeRequestById(@PathVariable id: Long): ResponseEntity<ExchangeRequestDTO> {
        val exchangeRequestOpt = exchangeRequestService.getById(id)
        if (exchangeRequestOpt.isPresent) {
            return ResponseEntity.ok(ExchangeRequestDTO(exchangeRequestOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange request with $id not found.")
        }
    }

    // Todos los Exchange Requests con cierto estatus
    @GetMapping("/{status}")
    fun getExchangeRequestByStatus(@PathVariable status: ExchangeRequestStatus): ResponseEntity<ExchangeRequestDTO> {
        val exchangeRequestOpt = exchangeRequestService.getByStatus(status)
        if (exchangeRequestOpt.isPresent) {
            return ResponseEntity.ok(ExchangeRequestDTO(exchangeRequestOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange requests with $status not found.")
        }
    }

    // Todos los Exchange Requests creados por un usuario
    @GetMapping("/user/{id}")
    fun getExchangeRequestByUserId(@PathVariable id: Long): ResponseEntity<ExchangeRequestDTO> {
        val exchangeRequestOpt = exchangeRequestService.getByUserid(id)
        if (exchangeRequestOpt.isPresent) {
            return ResponseEntity.ok(ExchangeRequestDTO(exchangeRequestOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange requests of user $id not found.")
        }
    }

    // Todos los ER de cierto estatus creados por un usuario
    @GetMapping("/user/{id}/{status}")
    fun getExchangeRequestByUserIdAndStatus(@PathVariable id: Long, status: ExchangeRequestStatus): ResponseEntity<ExchangeRequestDTO> {
        val exchangeRequestOpt = exchangeRequestService.getByUseridByStatus(id, status)
        if (exchangeRequestOpt.isPresent) {
            return ResponseEntity.ok(ExchangeRequestDTO(exchangeRequestOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "$status exchange requests of user $id not found.")
        }
    }

}