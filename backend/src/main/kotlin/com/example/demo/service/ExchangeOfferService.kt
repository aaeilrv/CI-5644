package com.example.demo.service

import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExchangeOfferService(@Autowired private val exchangeOfferRepository: ExchangeOfferRepository,
                      @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(exchangeOffer: ExchangeOffer): ExchangeOffer {
        return exchangeOfferRepository.save(exchangeOffer)
    }

    public fun getById(id: Long): Optional<ExchangeOffer> {
        return exchangeOfferRepository.findById(id)
    }

    public fun getAll(pageable: Pageable): List<ExchangeOffer> {
        val exchangeOfferEntities = exchangeOfferRepository.findAll(pageable)
        val exchangeOffer = exchangeOfferEntities.map { it }
        return exchangeOffer.content
    }
}