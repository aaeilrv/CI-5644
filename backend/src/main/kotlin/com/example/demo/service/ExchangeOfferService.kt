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

@Service
class ExchangeOfferService(
    @Autowired private val exchangeOfferRepository: ExchangeOfferRepository,
    @Autowired private val userService: UserService,
    @Autowired private val cardService: CardService,
    @Autowired private val exchangeRequestService: ExchangeRequestService
) {

    fun create(exchangeOfferDTO: CreateExchangeOfferDTO, userSub: String): ExchangeOffer {
        val foundBidder = userService.getBySub(userSub)
        val foundCard = cardService.getById(exchangeOfferDTO.offeredCardId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found.")
        }
        val foundExchangeRequest = exchangeRequestService.getById(exchangeOfferDTO.exchangeRequestId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange request not found.")
        }
        val now = System.currentTimeMillis();

        val newExchangeOffer = ExchangeOffer(
            null,
            foundBidder,
            foundExchangeRequest,
            foundCard,
            ExchangeOfferStatus.PENDING,
            Timestamp(now)
        )

        return exchangeOfferRepository.save(newExchangeOffer)
    }

    fun updateExchangeOffer(request: UpdateExchangeOfferRequest, userSub: String): ExchangeOffer {
        val exchangeOfferToUpdate = exchangeOfferRepository.findById(request.id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange offer not found.")
        }
        val bidderSub = exchangeOfferToUpdate.bidder.auth0Sub
        if (bidderSub != userSub) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized to update this exchange offer.")
        }

        exchangeOfferToUpdate.apply {
            status = ExchangeOfferStatus.valueOf(request.status.name)
        }

        return exchangeOfferRepository.save(exchangeOfferToUpdate)
    }

    fun getByUserSub(userSub: String): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByBidderSub(userSub)
        return exchangeOffers.map { ExchangeOfferDTO(it) }
    }

    public fun getAll(pageable: Pageable): List<ExchangeOffer> {
        val exchangeOfferEntities = exchangeOfferRepository.findAll(pageable)
        val exchangeOffer = exchangeOfferEntities.map { it }
        return exchangeOffer.content
    }

    fun getById(id: Long): Optional<ExchangeOffer> {
        return exchangeOfferRepository.findById(id)
    }
    // Todos los EO creados por un usuario
    fun getByBidderSub(userSub: String): List<ExchangeOfferDTO> {
        // Comprobar si el usuario existe podría ser útil para validar, pero no es necesario obtener su ID.
        userService.getBySub(userSub)
        // Utiliza directamente el 'sub' para buscar las ofertas.
        return exchangeOfferRepository.findByBidderSub(userSub).map { ExchangeOfferDTO(it) }
    }
    

    // Todos los EO recibidos por un usuario
    fun getByReceiverSub(userSub: String): List<ExchangeOfferDTO> {
        return exchangeOfferRepository.findByReceiverSub(userSub).map { ExchangeOfferDTO(it) }
    }

    // Todos los EO recibidos por un exchange request
    public fun getByExchangeRequest(id: Long): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByExchangeRequest(id)
        return exchangeOffers.map { offers -> ExchangeOfferDTO(offers) }
    }

    // Todos los EO ofreciendo una barajita
    public fun getByCardId(cardId: Long): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByCard(cardId)
        return exchangeOffers.map { offers -> ExchangeOfferDTO(offers) }
    }

    // Todos los EO con un status
    public fun getByStatus(status: ExchangeOfferStatus): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByStatus(status)
        return exchangeOffers.map { offers -> ExchangeOfferDTO(offers) }
    }

    // Todos los EO en cierto rango de tiempo
    public fun getByDateRange(start: Date, end: Date): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByDateRange(start, end)
        return exchangeOffers.map { offers -> ExchangeOfferDTO(offers) }
    }

    // Todos los EO creados por un usuario ofreciendo una barajita
    fun getByBidderSubAndCardId(bidderSub: String, cardId: Long): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByBidderSubAndCardId(bidderSub, cardId)
        return exchangeOffers.map { ExchangeOfferDTO(it) }
    }

    // Todos los EO creados por un usuario y con un status
    fun getByBidderSubAndStatus(bidderSub: String, status: ExchangeOfferStatus): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByBidderSubAndStatus(bidderSub, status)
        return exchangeOffers.map { ExchangeOfferDTO(it) }
    }
    // Todos los EO creados por un usuario en cierto rango de tiempo
    fun getByBidderSubAndDateRange(bidderSub: String, start: Date, end: Date): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByBidderSubAndDateRange(bidderSub, start, end)
    return exchangeOffers.map { ExchangeOfferDTO(it) }
    }

    // Todos los EO recibidos por un usuario en cierto rango de tiempo
    fun getByReceiverSubAndDateRange(receiverSub: String, start: Date, end: Date): List<ExchangeOfferDTO> {
        val exchangeOffers = exchangeOfferRepository.findByReceiverSubAndDateRange(receiverSub, start, end)
    return exchangeOffers.map { ExchangeOfferDTO(it) }
    }

}