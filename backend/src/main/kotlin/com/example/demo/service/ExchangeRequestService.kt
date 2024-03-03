package com.example.demo.service

import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExchangeRequestService(@Autowired private val exchangeRequestRepository: ExchangeRequestRepository,
                      @Autowired private val ownershipRepository: OwnershipRepository) {


    public fun create(exchangeRequest: ExchangeRequest): ExchangeRequest {
        return exchangeRequestRepository.save(exchangeRequest)
    }

    public fun getById(id: Long): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findById(id)
    }

    public fun getByStatus(status: ExchangeRequestStatus): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findByStatus(status)
    }

    public fun getByUserid(userId: Long): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findByUserId(userId)
    }

    public fun getByUseridByStatus(userId: Long, status: ExchangeRequestStatus): Optional<ExchangeRequest> {
        return exchangeRequestRepository.findByUserIdAndStatus(userId, status)
    }

    public fun getAll(pageable: Pageable): List<ExchangeRequest> {
        val exchangeRequestEntities = exchangeRequestRepository.findAll(pageable)
        val exchangeRequests = exchangeRequestEntities.map { it }
        return exchangeRequests.content
    }
}