package com.example.demo.model

import com.example.demo.controller.dto.CreateCreditCardRequest
import jakarta.persistence.*
import java.sql.Date

public enum class CreditCardTypes{
    VISA,MASTERCARD
}

@Entity
@Table(name = "credit_card")
class CreditCard(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "card_number", nullable = false, length = 100)
    val cardNumber: String,

    @Column(name = "bank", nullable = false, length = 100)
    val bank: String,

    @Column(name= "expiration_date",nullable = false)
    val expirationDate: java.sql.Date,

    @Column(name = "cardholder_name",nullable = false, length = 100)
    val cardholderName: String,

    @Column(name = "card_type", nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    var card_type: CreditCardTypes,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val cardHolder: User = User(),

) {
    constructor() : this(null, "", "", java.sql.Date(0), "",CreditCardTypes.VISA,User())

    constructor(request: CreateCreditCardRequest):this(
        null,
        request.cardNumber,
        request.bank,
        request.expirationDate,
        request.cardHolderName,
        CreditCardTypes.valueOf(request.cardType.uppercase()),
        request.user
    )
}