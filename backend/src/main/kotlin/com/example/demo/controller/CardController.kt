package com.example.demo.controller

import com.example.demo.controller.dto.CreateCardRequest
import com.example.demo.controller.dto.CardDTO
import com.example.demo.controller.dto.UserWhoOwnsCardDTO
import com.example.demo.model.Card
import com.example.demo.model.User
import com.example.demo.service.CardService
import jakarta.persistence.Entity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("v1/card")
class CardController {

    @Autowired
    lateinit var cardService: CardService

    @PostMapping
    fun createCard(@RequestBody request: CreateCardRequest): ResponseEntity<CardDTO> {
        return ResponseEntity.ok(CardDTO(cardService.create(Card(request))))
    }

    @PostMapping("createMultipleCards")
    fun createCards(@RequestBody request: List<CreateCardRequest>): String {
        for(r:CreateCardRequest in request) {
            cardService.create(Card(r))
        }
        return "done"+request.size
    }
    @GetMapping()
    fun getAllCard(pageable: Pageable): List<CardDTO> {
        return cardService.getAll(pageable).map {CardDTO(it)}
    }

    @GetMapping("ownersOfCard/{cardId}")
    fun getCardOwners(@PathVariable cardId: Long): List<UserWhoOwnsCardDTO>? {
        return cardService.getOwnersById(cardId)?.map {UserWhoOwnsCardDTO(it)}
    }

}