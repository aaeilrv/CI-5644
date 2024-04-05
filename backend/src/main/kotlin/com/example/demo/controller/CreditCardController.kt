package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.CreditCard
import com.example.demo.service.CreditCardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@CrossOrigin(value = ["http://localhost:3000", "http://207.246.119.200:3000"])
@RestController
@RequestMapping("v1/creditCard")
class CreditCardController {
    @Autowired
    lateinit var creditCardService: CreditCardService

    @PostMapping
    fun addCreditCard(principal: JwtAuthenticationToken, @RequestBody request: CreateCreditCardDTO): ResponseEntity<CreditCardDTO>{
        val sub = principal.tokenAttributes["sub"]?.toString() ?: return ResponseEntity.internalServerError().build()
        return ResponseEntity.ok(CreditCardDTO(creditCardService.create(request,sub)))
    }

    @GetMapping("/user/{id}")
    fun getCreditCardById(@PathVariable id:Long): List<CreditCardDTO>{
        return creditCardService.getByUserId(id)
    }

    @GetMapping
    fun getCardsBySub(principal: JwtAuthenticationToken):List<CreditCardDTO>{
        val sub = principal.tokenAttributes["sub"].toString()
        return creditCardService.getBySub(sub)
    }

}