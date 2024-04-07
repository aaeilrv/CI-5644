package com.example.demo.model

import jakarta.persistence.*
import com.example.demo.controller.dto.CreatePurchaseDTO
import com.example.demo.controller.dto.CreatePurchaseRequest
import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import java.math.BigDecimal
import java.sql.Timestamp

@Entity
@Table(name = "purchase")
class Purchase(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(name = "purchase_timestamp", nullable = false)
    private val timestamp: java.sql.Timestamp,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val purchasingUser: User,

    @Column(name = "packets_purchased", nullable = false)
    private val packetsPurchased: Int,

    @Column(name = "base_amount", nullable = false)
    private val baseAmount: Int,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private val creditCard: CreditCard,

) {
    constructor() : this(null,
        java.sql.Timestamp(0),
        User(),
        0,
        0,
        CreditCard())

    fun getId(): Long = this.id!!

    fun getCreatedAt(): Timestamp = this.timestamp

    fun getUser(): User = this.purchasingUser

    fun getPacketsPurchased(): Int = this.packetsPurchased

    fun getBaseAmount(): Int = this.baseAmount

    fun getCreditCard(): CreditCard = this.creditCard

    constructor(request: CreatePurchaseRequest) :this(
        null,
        request.createdAt,
        request.user,
        request.amount,
        request.amount*5,
        request.creditCard
    )

}