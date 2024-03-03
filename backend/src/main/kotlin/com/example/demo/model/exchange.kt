package com.example.demo.model

import com.example.demo.controller.dto.CreateExchangeRequestRequest
import jakarta.persistence.*
import java.awt.print.Pageable
import java.sql.Time
import java.sql.Timestamp

enum class ExchangeRequestStatus {
    PENDING, ACCEPTED, REJECTED
}

enum class ExchangeOfferStatus {
    PENDING, ACCEPTED, REJECT, COUNTEROFFER
}

@Entity
@Table(name = "exchange_request")
class ExchangeRequest(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private val requester: User,

    @ManyToOne
    @JoinColumn(name = "requested_card_id", nullable = false)
    private val requestedCard: Card,

    @Column(name = "status", nullable = false, columnDefinition = "exchange_request_status")
    @Enumerated(EnumType.STRING)
    private val status: ExchangeRequestStatus, // 0: pending, 1: accepted, 2: rejected, 3: canceled, 4: completed

    @Column(name = "created_at", nullable = false)
    private val createdAt: java.sql.Timestamp,

    ) {
    constructor() : this(-1, User(), Card(), ExchangeRequestStatus.PENDING, java.sql.Timestamp(0))
    constructor(request: CreateExchangeRequestRequest) : this()

    fun getId(): Long = this.id!!
    fun getUser(): User = this.requester
    fun getRequestedCard(): Card = this.requestedCard
    fun getStatus(): ExchangeRequestStatus = this.status
    fun getCreatedAt(): Timestamp = this.createdAt
}

@Entity
@Table(name = "exchange_offer")
class ExchangeOffer(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long?,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private val requester: User,

        @ManyToOne
        @JoinColumn(name = "exchange_request_id", nullable = false)
        private val exchangeRequest: ExchangeRequest,

        @ManyToOne
        @JoinColumn(name = "offered_card_id", nullable = false)
        private val card: Card,

        @Column(name = "status", nullable = false, columnDefinition = "exchange_offer_status")
        @Enumerated(EnumType.STRING)
        private val status: ExchangeOfferStatus, // 0: pending, 1: accepted, 2: rejected, 4: counteroffer

        @Column(name = "created_at", nullable = false)
        private val createdAt: java.sql.Timestamp,

        ) {
    constructor() : this(-1, User(), ExchangeRequest(), Card(), ExchangeOfferStatus.PENDING, java.sql.Timestamp(0))

    fun getId(): Long = this.id!!
    fun getUser(): User = this.requester
    fun getExchangeRequest(): ExchangeRequest = this.exchangeRequest
    fun getOfferedCard(): Card = this.card
    fun getStatus(): ExchangeOfferStatus = this.status
    fun getCreatedAt(): Timestamp = this.createdAt
}

@Entity
@Table(name = "exchange_counteroffer")
class ExchangeCounteroffer(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long?,

        @ManyToOne
        @JoinColumn(name = "offered_card_id", nullable = false)
        private val card: Card,

        @Column(name = "status", nullable = false, columnDefinition = "exchange_request_status")
        @Enumerated(EnumType.STRING)
        private val status: ExchangeRequestStatus, // 0: pending, 1: accepted, 2: rejected, 3: canceled, 4: completed

        @ManyToOne
        @JoinColumn(name = "exchange_request_id", nullable = false)
        private val exchangeRequest: ExchangeRequest,

        @ManyToOne
        @JoinColumn(name = "exchange_offer_id", nullable = false)
        private val exchangeOffer: ExchangeOffer,

        @Column(name = "created_at", nullable = false)
        private val createdAt: java.sql.Timestamp,

        ) {
    constructor() : this(-1, Card(), ExchangeRequestStatus.PENDING, ExchangeRequest(), ExchangeOffer(), java.sql.Timestamp(0))
    fun getId(): Long = this.id!!
    fun getOfferedCard(): Card = this.card
    fun getStatus(): ExchangeRequestStatus = this.status
    fun getExchangeRequest(): ExchangeRequest = this.exchangeRequest
    fun getExchangeOffer(): ExchangeOffer = this.exchangeOffer
    fun getCreatedAt(): Timestamp = this.createdAt
}