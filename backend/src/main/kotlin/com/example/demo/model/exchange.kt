package com.example.demo.model

import jakarta.persistence.*

enum class ExchangeRequestStatus {
    PENDING, ACCEPTED, REJECTED
}

@Entity
@Table(name = "exchange_request")
class Exchange(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private val requester: User,

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private val recipient: User,

    @ManyToOne
    @JoinColumn(name = "offered_card_id", nullable = false)
    private val offeredCard: Card,

    @Column(name = "offered_card_amount", nullable = false)
    private val offeredCardAmount: Int,

    @ManyToOne
    @JoinColumn(name = "requested_card_id", nullable = false)
    private val requestedCard: Card,

    @Column(name = "requested_card_amount", nullable = false)
    private val requestedCardAmount: Int,

    @Column(name = "request_status", nullable = false, columnDefinition = "exchange_request_status")
    @Enumerated(EnumType.STRING)
    private val status: ExchangeRequestStatus, // 0: pending, 1: accepted, 2: rejected, 3: canceled, 4: completed

    @Column(name = "created_at", nullable = false)
    private val createdAt: java.sql.Timestamp,

    ) {
    constructor() : this(-1, User(), User(), Card(), 0, Card(), 0, ExchangeRequestStatus.PENDING, java.sql.Timestamp(0))
}