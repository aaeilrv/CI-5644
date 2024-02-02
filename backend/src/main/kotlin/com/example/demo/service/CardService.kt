package com.example.demo.service

import com.example.demo.controller.dto.CreateCardRequest
import com.example.demo.controller.dto.CardDTO
import com.example.demo.model.Card
import com.example.demo.model.User
import com.example.demo.repo.CardRepository
import com.example.demo.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class CardService(@Autowired private val cardRepository: CardRepository) {

    public fun create(card: Card): Card {
        return cardRepository.save(card)
    }

    public fun getById(id: Long): Optional<Card> {
        return cardRepository.findById(id)
    }

    public fun getAll(pageable: Pageable): List<Card> {
        val cardEntities = cardRepository.findAll(pageable)
        val cards = cardEntities.map { cardEntity ->
            Card(
                id = cardEntity.getId(),
                name = cardEntity.getName(),
                playerPosition = cardEntity.getPlayerPosition(),
                playerNumber = cardEntity.getPlayerNumber(),
                country = cardEntity.getCountry()
            )
        }
        return cards.content
    }
    public fun changeName(id:Long, name:String): Card?{
        val exists: Optional<Card> = getById(id)
        if(exists.isPresent){
            val oldCard:Card = exists.get()
            oldCard.setName(name)
            val newCard:Card = cardRepository.save(oldCard)
            return create(newCard)
        }
        return null
    }

}