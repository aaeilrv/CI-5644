package com.example.demo.service

import com.example.demo.controller.dto.ExchangeCounterofferDTO
import com.example.demo.controller.dto.ExchangeOfferDTO
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

    public fun getAll(pageable: Pageable): List<ExchangeCounteroffer> {
        val exchangeCounterofferEntities = exchangeCounterofferRepository.findAll(pageable)
        val exchangeCounteroffer = exchangeCounterofferEntities.map { it }
        return exchangeCounteroffer.content
    }

    public fun getById(id: Long): Optional<ExchangeCounteroffer> {
        return exchangeCounterofferRepository.findById(id)
    }

    public fun getByCreator(id: Long): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByCreatorId(id)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByReceiver(id: Long): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByReceiverId(id)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByCard(id: Long): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByCard(id)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByEO(id: Long): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByExchangeOffer(id)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByER(id: Long): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByExchangeRequest(id)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByDateRange(start: Date, end: Date): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByDateRange(start, end)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByStatus(status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByStatus(status)
        return counterOffers.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByCreatorAndDateRange(id: Long, start: Date, end: Date): List<ExchangeCounterofferDTO> {
        val counteroffer = exchangeCounterofferRepository.findByDateRangeAndCreatorId(id, start, end)
        return counteroffer.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByReceiverAndDateRange(id: Long, start: Date, end: Date): List<ExchangeCounterofferDTO> {
        val counteroffer = exchangeCounterofferRepository.findByDateRangeAndReceiverId(id, start, end)
        return counteroffer.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByCreatorAndStatus(id: Long, status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        val counteroffer = exchangeCounterofferRepository.findByCreatorAndStatus(id, status)
        return counteroffer.map { co -> ExchangeCounterofferDTO(co) }
    }

    public fun getByReceiverAndStatus(id: Long, status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        val counteroffer = exchangeCounterofferRepository.findByReceiverAndStatus(id, status)
        return counteroffer.map { co -> ExchangeCounterofferDTO(co) }
    }
}