package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.ExchangeRequest
import com.example.demo.service.ExchangeService
import org.apache.coyote.Request
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(value = ["http://localhost:3000"])
@RestController
@RequestMapping("v1/exchange")
class ExchangeController(private val exchangeService: ExchangeService) {


}