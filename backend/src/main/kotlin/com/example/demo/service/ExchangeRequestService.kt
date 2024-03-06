package com.example.demo.service

import com.example.demo.controller.dto.ExchangeRequestDTO
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ExchangeRequestService(@Autowired private val exchangeRequestRepository: ExchangeRequestRepository,
                      @Autowired private val ownershipRepository: OwnershipRepository) {


    public fun create(exchangeRequest: ExchangeRequest): ExchangeRequest {
        return exchangeRequestRepository.save(exchangeRequest)
    }

    public fun getAll(pageable: Pageable): List<ExchangeRequest> {
        val exchangeRequestEntities = exchangeRequestRepository.findAll(pageable)
        val exchangeRequests = exchangeRequestEntities.map { it }
        return exchangeRequests.content
    }

    public fun getById(id: Long): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findById(id)
    }

    fun getByUserid(userId: Long): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByUserId(userId)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    public fun getByCardId(cardId: Long): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByCardId(cardId)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
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

    public fun getByCardIdAndUserId(cardId: Long, userId: Long): List<ExchangeRequestDTO>  {
        val exchangeRequests = exchangeRequestRepository.findByCardidAndUserId(cardId, userId)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    public fun getByDateRange(startDate: Date, endDate: Date): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByDateRange(startDate, endDate)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    public fun getByUserIdWithinDateRange(userId: Long, startDate: Date, endDate: Date): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findByUserIdWithinDateRange(userId, startDate, endDate)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    public fun getAllPossibleERbyUser(id: Long): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findAllERbyUserOwner(id)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }

    public fun getAllPossibleERbyRequesterAndOwner(ownerId: Long, requesterId: Long): List<ExchangeRequestDTO> {
        val exchangeRequests = exchangeRequestRepository.findAllPossibleERbyRequesterAndOwner(ownerId, requesterId)
        return exchangeRequests.map { requests -> ExchangeRequestDTO(requests) }
    }
}