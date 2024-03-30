package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.service.PurchaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@CrossOrigin(value = ["http://localhost:3000", "http://207.246.119.200:3000"])
@RestController
@RequestMapping("v1/purchase")
class PurchaseController {
    @Autowired
    lateinit var purchaseService: PurchaseService

    @PostMapping
    fun createPurchase(@RequestBody request: CreatePurchaseDTO): ResponseEntity<PurchaseDTO>{
        return ResponseEntity.ok(PurchaseDTO(purchaseService.create(request)))
    }

    @GetMapping("/all")
    fun getAllPurchasesMade(pageable: Pageable): List<PurchaseDTO>{
        return purchaseService.getAll(pageable).map{purchase ->  PurchaseDTO(purchase)}
    }

    @GetMapping
    fun getPurchaseBySub(principal:JwtAuthenticationToken):List<PurchaseDTO>{
        val sub = principal.tokenAttributes["sub"].toString()
        return purchaseService.getBySub(sub)
    }
}