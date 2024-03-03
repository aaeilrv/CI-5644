package com.example.demo.repo

import com.example.demo.model.ExchangeCounteroffer
import com.example.demo.model.ExchangeOffer
import com.example.demo.model.ExchangeOfferStatus
import com.example.demo.model.ExchangeRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ExchangeRequestRepository: JpaRepository<ExchangeRequest, Long> {
    @Query("SELECT er FROM ExchangeRequest er WHERE er.status = :status")
    fun findByStatus(status: ExchangeOfferStatus): Optional<ExchangeRequest>
}

@Repository
interface ExchangeOfferRepository: JpaRepository<ExchangeOffer, Long> {

}

@Repository
interface ExchangeCounterofferRepository: JpaRepository<ExchangeCounteroffer, Long> {

}
