package com.example.demo.service

import com.example.demo.controller.dto.*
import com.example.demo.model.*
import com.example.demo.repo.*
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp
import java.time.Instant
import org.springframework.http.ResponseEntity

import java.util.*
import kotlin.NoSuchElementException
import java.security.SecureRandom

@Service
class PurchaseService(@Autowired private val purchaseRepository: PurchaseRepository,
                      private val userService: UserService,
                      private val cardService: CardService,
                      private val creditCardService: CreditCardService) {

    public fun create(purchase: CreatePurchaseDTO, sub: String): Purchase {
        val foundUser = userService.getBySub(sub)
        val foundCreditCard = creditCardService.getById(1).orElseThrow{
            NoSuchElementException("Credit card not found.")
        }

        val newPurchase:CreatePurchaseRequest
        try{
            newPurchase = CreatePurchaseRequest(
                user = foundUser,
                amount = purchase.amount,
                creditCard = foundCreditCard,
                createdAt = Timestamp.from(Instant.now())
            )
        }catch (e:IllegalArgumentException){
            throw IllegalArgumentException("Error creating the purchase")
        }
        addRandomCards(purchase.amount, sub)
        return purchaseRepository.save(Purchase(newPurchase))
    }

     private fun addRandomCards(numberOfPackets: Int, sub: String): List<Card>? {
         val cardsId: MutableList<Long> = mutableListOf()
         val randomGen = SecureRandom()
         for (i in 1..numberOfPackets){
             val nextPacket = generatePacket(randomGen)
             cardsId.addAll(nextPacket)
         }
         val cards = cardService.getMultipleById(cardsId)
         try {
             userService.addMultipleCards(sub, cards)
         } catch (e: NoSuchElementException) {
             return null
         }
         return cards
     }

    private fun generatePacket(randomGen: SecureRandom): MutableList<Long> {
        val idList: MutableList<Long> = mutableListOf()
        while (idList.size < 5) {
            val newId = randomGen.nextLong(1, 36)
            if (!idList.contains(newId)) {
                idList.add(newId)
            }
        }
        return idList
    }

    public fun getAll(pageable: Pageable): List<Purchase> {
        val purchaseEntities = purchaseRepository.findAll(pageable)
        val purchases = purchaseEntities.map { it }
        return purchases.content
    }

    public fun getBySub(sub:String): List<PurchaseDTO>{
        val purchasesMadeByUser =  purchaseRepository.findByUserSub(sub)
        return purchasesMadeByUser.map { purchases -> PurchaseDTO(purchases) }
    }

}