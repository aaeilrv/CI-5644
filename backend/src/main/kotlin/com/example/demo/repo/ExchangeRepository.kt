package com.example.demo.repo

import com.example.demo.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ExchangeRequestRepository: JpaRepository<ExchangeRequest, Long> {
    @Query("SELECT er FROM ExchangeRequest er WHERE er.status = :status")
    fun findByStatus(status: ExchangeRequestStatus): Optional<ExchangeRequest>

    @Query("SELECT er FROM ExchangeRequest er WHERE er.requester.id = :id")
    fun findByUserId(userId: Long): Optional<ExchangeRequest>

    @Query("SELECT er FROM ExchangeRequest er WHERE er.requester.id = :userId AND er.status = :status")
    fun findByUserIdAndStatus(userId: Long, status: ExchangeRequestStatus): Optional<ExchangeRequest>
}

@Repository
interface ExchangeOfferRepository: JpaRepository<ExchangeOffer, Long> {

    @Query("SELECT eo FROM ExchangeOffer eo WHERE eo.status = :status")
    fun findByStatus(status: ExchangeOfferStatus): Optional<ExchangeOffer>

    @Query("SELECT eo FROM ExchangeOffer eo WHERE eo.requester.id = :id")
    fun findByUserId(userId: Long): Optional<ExchangeOffer>

    @Query("SELECT eo FROM ExchangeOffer eo WHERE eo.requester.id = :userId AND eo.status = :status")
    fun findByUserIdAndStatus(userId: Long, status: ExchangeOfferStatus): Optional<ExchangeOffer>
}

@Repository
interface ExchangeCounterofferRepository: JpaRepository<ExchangeCounteroffer, Long> {
    @Query("SELECT eco FROM ExchangeCounteroffer eco WHERE eco.status = :status")
    fun findByStatus(status: ExchangeRequestStatus): Optional<ExchangeCounteroffer>

    @Query("SELECT eco FROM ExchangeCounteroffer eco WHERE eco.requester.id = :id")
    fun findByUserId(userId: Long): Optional<ExchangeCounteroffer>

    @Query("SELECT eco FROM ExchangeCounteroffer eco WHERE eco.exchangeRequest.id = :userId AND eco.status = :status")
    fun findByCreatedUserIdAndStatus(userId: Long, status: ExchangeRequestStatus): Optional<ExchangeCounteroffer>

    @Query("SELECT eco FROM ExchangeCounteroffer eco WHERE eco.exchangeOffer.id = :userId AND eco.status = :status")
    fun findByCounterofferedUserIdAndStatus(userId: Long, status: ExchangeRequestStatus): Optional<ExchangeCounteroffer>
}
