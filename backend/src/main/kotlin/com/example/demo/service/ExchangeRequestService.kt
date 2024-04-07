package com.example.demo.service

import com.example.demo.controller.dto.CreateExchangeRequestDTO
import com.example.demo.controller.dto.CreateExchangeRequestRequest
import com.example.demo.controller.dto.ExchangeRequestDTO
import com.example.demo.controller.dto.UpdateExchangeRequestRequest
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.NoSuchElementException

@Service
class ExchangeRequestService(
    @Autowired private val exchangeRequestRepository: ExchangeRequestRepository,
    @Autowired private val userService: UserService,
    @Autowired private val cardService: CardService
) {

    fun create(request: CreateExchangeRequestDTO, userSub: String): ExchangeRequest {
        val foundUser = userService.getBySub(userSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        val foundCard = cardService.getById(request.requestedCardId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found.")
        }

        val newExchangeRequest = ExchangeRequest().also {
            it.updateRequester(foundUser)
            it.updateRequestedCard(foundCard)
            it.updateStatus(ExchangeRequestStatus.PENDING)
        }

        return exchangeRequestRepository.save(newExchangeRequest)
    }

    fun updateExchangeRequest(request: UpdateExchangeRequestRequest): ExchangeRequest {
        val exchangeRequestToUpdate = exchangeRequestRepository.findById(request.id)

        if (exchangeRequestToUpdate.isPresent) {
            val currentER = exchangeRequestToUpdate.get()
            currentER.status = request.status
            return exchangeRequestRepository.save(currentER)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange request not found.")
        }
    }

    fun getAll(pageable: Pageable): List<ExchangeRequest> {
        return exchangeRequestRepository.findAll(pageable).content
    }

    fun getById(id: Long): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findById(id)
    }

    fun getByUserSub(userSub: String): List<ExchangeRequestDTO> {
        val user = userService.getBySub(userSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        val userId = user.getId()
        val exchangeRequests = exchangeRequestRepository.findByUserId(userId)
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }

    fun getByCardId(cardId: Long): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByCardId(cardId)
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }

    //// NO FUNCIONAN AÚN POR EL ENUM
    public fun getByStatus(status: ExchangeRequestStatus): List<ExchangeRequestDTO> {
        val exchangeRequests =  exchangeRequestRepository.findByStatus(ExchangeRequestStatus.ACCEPTED)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }


    public fun getByUseridByStatus(userId: Long, status: ExchangeRequestStatus): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByUserIdAndStatus(userId, status)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }
    /////

    fun getByUserSubAndCardId(userSub: String, cardId: Long): List<ExchangeRequestDTO> {
        val user = userService.getBySub(userSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        val exchangeRequests = exchangeRequestRepository.findByUserIdAndCardId(user.getId(), cardId)
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }

    public fun getByDateRange(startDate: Date, endDate: Date): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByDateRange(startDate, endDate)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    fun getByUserSubWithinDateRange(userSub: String, startDate: Date, endDate: Date): List<ExchangeRequestDTO> {
        val user = userService.getBySub(userSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        val exchangeRequests = exchangeRequestRepository.findByUserIdWithinDateRange(user.getId(), startDate, endDate)
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }

    fun getAllPossibleERbyUserSub(userSub: String): List<ExchangeRequestDTO> {
        val user = userService.getBySub(userSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
        }
        val exchangeRequests = exchangeRequestRepository.findAllERbyUserOwner(user.getId())
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }

    fun getAllPossibleERbyCardsOwnerAndRequesterSub(ownerSub: String, requesterSub: String): List<ExchangeRequestDTO> {
        val owner = userService.getBySub(ownerSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found.")
        }
        val requester = userService.getBySub(requesterSub).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Requester not found.")
        }
        val exchangeRequests = exchangeRequestRepository.findAllPossibleERbyCardsOwnerAndRequester(owner.getId(), requester.getId())
        return exchangeRequests.map { ExchangeRequestDTO(it) }
    }
    
}