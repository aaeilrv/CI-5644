package com.example.demo.service

import com.example.demo.controller.dto.CreateCardRequest
import com.example.demo.controller.dto.CardDTO
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ExchangeService(@Autowired private val exchangeRequestRepository: ExchangeRequestRepository,
                      @Autowired private val exchangeOfferRepository: ExchangeOfferRepository,
                      @Autowired private val exchangeCounterofferRepository: ExchangeCounterofferRepository,
                      @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(exchangeRequest: ExchangeRequest): ExchangeRequest {
        return exchangeRequestRepository.save(exchangeRequest)
    }

    public fun create(exchangeOffer: ExchangeOffer): ExchangeOffer {
        return exchangeOfferRepository.save(exchangeOffer)
    }

    public fun create(exchangeCounterOffer: ExchangeCounteroffer): ExchangeCounteroffer {
        return exchangeCounterofferRepository.save(exchangeCounterOffer)
    }
}