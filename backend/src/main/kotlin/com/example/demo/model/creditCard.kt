package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "credit_card")
class CreditCard(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val cardNumber: String,

    @Column(nullable = false, length = 100)
    private val bank: String,

    @Column(nullable = false)
    private val expirationDate: java.sql.Date,

    @Column(nullable = false, length = 100)
    private val cardholderName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val cardHolder: User = User(),

) {
    constructor() : this(null, "", "", java.sql.Date(0), "")
}