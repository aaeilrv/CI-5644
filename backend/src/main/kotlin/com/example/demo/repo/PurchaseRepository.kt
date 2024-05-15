package com.example.demo.repo
import com.example.demo.controller.dto.ExchangeRequestDTO
import com.example.demo.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Repository
interface PurchaseRepository: JpaRepository<Purchase, Long> {
    @Query("SELECT purchase FROM Purchase purchase WHERE purchase.purchasingUser.auth0Sub = :sub")
    fun findByUserSub(sub:String):List<Purchase>
}