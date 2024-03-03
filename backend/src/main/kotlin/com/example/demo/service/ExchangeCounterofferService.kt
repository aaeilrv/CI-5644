package com.example.demo.service

import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExchangeCounterofferService(@Autowired private val exchangeCounterofferRepository: ExchangeCounterofferRepository,
                             @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(exchangeCounteroffer: ExchangeCounteroffer): ExchangeCounteroffer {
        return exchangeCounterofferRepository.save(exchangeCounteroffer)
    }

    public fun getById(id: Long): Optional<ExchangeCounteroffer> {
        return exchangeCounterofferRepository.findById(id)
    }

    public fun getAll(pageable: Pageable): List<ExchangeCounteroffer> {
        val exchangeCounterofferEntities = exchangeCounterofferRepository.findAll(pageable)
        val exchangeCounteroffer = exchangeCounterofferEntities.map { it }
        return exchangeCounteroffer.content
    }
}