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
    val id: Long?,

    @Column(name = "purchase_timestamp", nullable = false)
    val timestamp: java.sql.Timestamp,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val purchasingUser: User,

    @Column(name = "packets_purchased", nullable = false)
    val packetsPurchased: Int,

    @Column(name = "base_amount", nullable = false)
    val baseAmount: Int,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    val creditCard: CreditCard,

) {
    constructor() : this(null,
        java.sql.Timestamp(0),
        User(),
        0,
        0,
        CreditCard())

    constructor(request: CreatePurchaseRequest) :this(
        null,
        request.createdAt,
        request.user,
        request.amount,
        request.amount*5,
        request.creditCard
    )

}