package com.example.demo.service

import com.example.demo.controller.dto.CreateCreditCardDTO
import com.example.demo.controller.dto.*
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import com.example.demo.service.CardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.stream.Collectors

@Service
class CreditCardService(@Autowired private val creditCardRepository: CreditCardRepository,
                        private val userService: UserService) {

    public fun getById(id:Long):Optional<CreditCard>{
        return creditCardRepository.findById(id)
    }

    fun getByUserId(id:Long):List<CreditCardDTO>{
        val creditCards = creditCardRepository.findByUserId(id)
        return creditCards.map { cards -> CreditCardDTO(cards) }
    }

    fun getBySub(sub:String):List<CreditCardDTO>{
        val creditCardByUser =  creditCardRepository.findByUserSub(sub)
        return creditCardByUser.map { cards -> CreditCardDTO(cards) }
    }

    public fun create(creditCard:CreateCreditCardDTO, sub:String):CreditCard{
        val foundUser = userService.getBySub(sub).orElseThrow{
            NoSuchElementException("User not found.")
        }
        val newCreditCard:CreateCreditCardRequest
        try {
            newCreditCard = CreateCreditCardRequest(
                user = foundUser,
                expirationDate = creditCard.expirationDate,
                cardNumber = creditCard.cardNumber,
                cardHolderName = creditCard.cardHolder,
                bank = creditCard.bank,
                cardType = creditCard.cardType
            )
        }catch (e:IllegalArgumentException){
            throw IllegalArgumentException("Error adding the credit card to user")
        }
        return creditCardRepository.save(CreditCard(newCreditCard))
        }

    }
