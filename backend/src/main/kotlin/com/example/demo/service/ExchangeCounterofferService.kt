package com.example.demo.service

import com.example.demo.controller.dto.*
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import kotlin.NoSuchElementException

@Service
class ExchangeCounterofferService(
    @Autowired private val exchangeCounterofferRepository: ExchangeCounterofferRepository,
    @Autowired private val userService: UserService,
    @Autowired private val cardService: CardService,
    @Autowired private val exchangeOfferService: ExchangeOfferService,
    @Autowired private val exchangeRequestService: ExchangeRequestService
) {

    fun create(request: CreateExchangeCounterofferDTO, userSub: String): ExchangeCounteroffer {
        
        val offeredCard = cardService.getById(request.offeredCardId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found.")
        }
        val exchangeOffer = exchangeOfferService.getById(request.exchangeOfferId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange offer not found.")
        }
    
        if (exchangeOffer.bidder.auth0Sub != userSub) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed.")
        }
        val now = System.currentTimeMillis();
        val newExchangeCounteroffer = ExchangeCounteroffer(
            null,
            offeredCard,
            ExchangeRequestStatus.PENDING,
            exchangeOffer.exchangeRequest,
            exchangeOffer,
            Timestamp(now)
        )
    
        return exchangeCounterofferRepository.save(newExchangeCounteroffer)
    }

    fun updateExchangeCounteroffer(request: UpdateExchangeCounterofferRequest, userSub: String): ExchangeCounteroffer {
        val exchangeCounterofferToUpdate = exchangeCounterofferRepository.findById(request.id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange counteroffer not found.")
        }

        val creatorSub = exchangeCounterofferToUpdate.exchangeOffer.bidder.auth0Sub
        if (creatorSub != userSub) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized to update this exchange counteroffer.")
        }
    
        exchangeCounterofferToUpdate.apply {
            status = ExchangeRequestStatus.valueOf(request.status.name)
        }
    
        return exchangeCounterofferRepository.save(exchangeCounterofferToUpdate)
    }

    public fun getAll(pageable: Pageable): List<ExchangeCounteroffer> {
        val exchangeCounterofferEntities = exchangeCounterofferRepository.findAll(pageable)
        val exchangeCounteroffer = exchangeCounterofferEntities.map { it }
        return exchangeCounteroffer.content
    }

    public fun getById(id: Long): Optional<ExchangeCounteroffer> {
        return exchangeCounterofferRepository.findById(id)
    }

    fun getByCreatorSub(Usersub: String): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByCreatorSub(Usersub)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
    }

    fun getByReceiverSub(Usersub: String): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByReceiverSub(Usersub)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
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

    fun getByCreatorSubAndDateRange(Usersub: String, start: Date, end: Date): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByCreatorSubAndDateRange(Usersub, start, end)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
    }

    fun getByReceiverSubAndDateRange(Usersub: String, start: Date, end: Date): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByReceiverSubAndDateRange(Usersub, start, end)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
    }

    fun getByCreatorSubAndStatus(Usersub: String, status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByCreatorSubAndStatus(Usersub, status)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
    }

    fun getByReceiverSubAndStatus(Usersub: String, status: ExchangeRequestStatus): List<ExchangeCounterofferDTO> {
        val counterOffers = exchangeCounterofferRepository.findByReceiverSubAndStatus(Usersub, status)
        return counterOffers.map { ExchangeCounterofferDTO(it) }
    }
}