package com.example.demo.repo

import com.example.demo.model.ExchangeCounteroffer
import com.example.demo.model.ExchangeOffer
import com.example.demo.model.ExchangeRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeRequestRepository: JpaRepository<ExchangeRequest, Long> {
}

@Repository
interface ExchangeOfferRepository: JpaRepository<ExchangeOffer, Long> {

}

@Repository
interface ExchangeCounterofferRepository: JpaRepository<ExchangeCounteroffer, Long> {

}