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
    private val id: Long?,

    @Column(name = "card_number", nullable = false, length = 100)
    private val cardNumber: String,

    @Column(name = "bank", nullable = false, length = 100)
    private val bank: String,

    @Column(name= "expiration_date",nullable = false)
    private val expirationDate: java.sql.Date,

    @Column(name = "cardholder_name",nullable = false, length = 100)
    private val cardholderName: String,

    @Column(name = "card_type", nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    var card_type: CreditCardTypes,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val cardHolder: User = User(),

) {
    constructor() : this(null, "", "", java.sql.Date(0), "",CreditCardTypes.VISA,User())

    fun getId(): Long = this.id!!
    fun getUser():User = this.cardHolder

    fun getBank():String = this.bank

    fun getCardType():CreditCardTypes = this.card_type

    fun getNumber():String = this.cardNumber

    fun getCardName():String = this.cardholderName

    fun getExpirationDate(): Date = this.expirationDate


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