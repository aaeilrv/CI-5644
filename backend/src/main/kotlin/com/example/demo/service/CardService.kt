package com.example.demo.service

import com.example.demo.controller.dto.CreateCardRequest
import com.example.demo.controller.dto.CardDTO
import com.example.demo.model.Card
import com.example.demo.model.User
import com.example.demo.model.Ownership
import com.example.demo.repo.CardRepository
import com.example.demo.repo.OwnershipRepository
import com.example.demo.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.NoSuchElementException

@Service
class CardService(@Autowired private val cardRepository: CardRepository,
                  @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(card: Card): Card {
        return cardRepository.save(card)
    }

    public fun getById(id: Long): Optional<Card> {
        return cardRepository.findById(id)
    }

    fun getMultipleById(ids: List<Long>): List<Card> {
        return cardRepository.findAllById(ids.asIterable())
    }

    public fun getAll(pageable: Pageable): List<Card> {
        val cardEntities = cardRepository.findAll(pageable)
        return cardEntities.content
    }
    public fun changeName(id:Long, name:String): Card {
        val card = getById(id).orElseThrow {
            throw NoSuchElementException("no card with id $id found")
        }
        card.name = name
        return cardRepository.save(card)

    }

    public fun getOwnersById(cardId: Long): List<Ownership> {
        val card = getById(cardId).orElseThrow {
            throw NoSuchElementException("no card with id $cardId found")
        }
        return card.users
    }

}