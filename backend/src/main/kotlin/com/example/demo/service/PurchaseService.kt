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

    public fun create(purchase: CreatePurchaseDTO): Purchase{
        val foundUser = userService.getById(purchase.userId).orElseThrow{
            NoSuchElementException("User not found.")
        }
        val foundCreditCard = creditCardService.getById(1).orElseThrow{
            java.util.NoSuchElementException("Credit card not found.")
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
        addRandomCards(purchase.amount,foundUser.getAuth0Sub())
        return purchaseRepository.save(Purchase(newPurchase))
    }

     public fun addRandomCards(numberOfPackets:Int,sub:String){
        val cardsId: MutableList<Long> = mutableListOf()
        for (i in 1..numberOfPackets){
            val random = SecureRandom();
            cardsId.add(random.nextLong(1,36))
            cardsId.add(random.nextLong(1,36))
            cardsId.add(random.nextLong(1,36))
            cardsId.add(random.nextLong(1,36))
            cardsId.add(random.nextLong(1,36))
        }
        for(cardId: Long in cardsId){
            val cardOpt = cardService.getById(cardId)
            if(cardOpt.isPresent) {
                userService.updateCardsOwnedList(sub, cardOpt.get())

            }else{
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
        }
    }

    public fun getAll(pageable: Pageable):List<Purchase> {
        val purchaseEntities = purchaseRepository.findAll(pageable)
        val purchases = purchaseEntities.map { it }
        return purchases.content
    }

    public fun getBySub(sub:String):List<PurchaseDTO>{
        val purchasesMadeByUser =  purchaseRepository.findByUserSub(sub)
        return purchasesMadeByUser.map { purchases -> PurchaseDTO(purchases) }
    }

}