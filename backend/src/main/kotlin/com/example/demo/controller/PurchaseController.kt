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
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@CrossOrigin(value = ["http://localhost:3000", "http://207.246.119.200:3000"])
@RestController
@RequestMapping("v1/purchase")
class PurchaseController {

}