package com.example.demo.model

import jakarta.persistence.*
import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import java.math.BigDecimal

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

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "base_amount", nullable = false)
    private val baseAmount: BigDecimal,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private val creditCard: CreditCard,

) {
    constructor() : this(null,
        java.sql.Timestamp(0),
        User(),
        0,
        java.math.BigDecimal(0),
        CreditCard())
}