package com.example.demo.repo

import com.example.demo.model.CreditCard
import com.example.demo.model.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

import java.util.Optional

@Repository
interface CreditCardRepository: JpaRepository<CreditCard,Long> {

    @Query("SELECT cc FROM CreditCard cc WHERE cc.cardHolder.id = :id")
    fun findByUserId(id:Long):List<CreditCard>

    @Query("SELECT cc FROM CreditCard cc WHERE cc.cardHolder.auth0Sub = :sub")
    fun findByUserSub(sub:String):List<CreditCard>

}