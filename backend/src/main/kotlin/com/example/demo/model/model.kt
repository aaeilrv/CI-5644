package com.example.demo.model

import com.example.demo.controller.dto.CreateUserRequest
import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal


@Entity
@Table(name = "users")
open class User (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private var firstName: String,

    @Column(nullable = false, length = 100)
    private var lastName: String,

    @Column(nullable = false, length = 100)
    private var birthday: java.sql.Date,

    @Column(nullable = false, length = 32)
    private var username: String,

    @Column(nullable = false, length = 100)
    private var email: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val cards: Set<UserCard> = mutableSetOf(), // El conjunto de cartas que tiene el usuario

    @OneToMany(mappedBy = "cardHolder", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val userCards: List<Payment> = mutableListOf(),

    @OneToMany(mappedBy = "user_purchase", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val userPurchases: List<Purchase> = mutableListOf()
) {
    open fun getId(): Long {
        return this.id!!
    }

    open fun getFirstName(): String {
        return this.firstName
    }

    open fun getLastName(): String {
        return this.lastName
    }

    open fun getFullName(): String {
        return (this.firstName + " " + this.lastName)
    }

    open fun getBirthDay(): java.sql.Date {
        return this.birthday
    }

    open fun getUsername(): String {
        return this.username
    }

    open fun getEmailAddress(): String {
        return this.email
    }

    constructor() : this(null, "", "", java.sql.Date(-1), "", "")
    constructor(request: CreateUserRequest) : this(
        null,
        request.firstName,
        request.lastName,
        java.sql.Date.valueOf(request.birthDay),
        request.username,
        request.emailAddress
    )
}

@Entity
@Table(name = "card")
class Card(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val name: String,

    @Column(nullable = false)
    private val playerNumber: String,

    @Column(nullable = false, length = 100)
    private val playerPosition: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val users: Set<UserCard> = mutableSetOf(),

) {
    constructor() : this(null, "", "", "") {

    }
}

@Entity
@Table(name = "user_card")
@IdClass(UserCardPk::class)
class UserCard(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "card_id")
    private val card: Card,

    @Column(name = "quantity", nullable = false)
    private val quantity: Int, // El número de cartas que tiene el usuario de tipo (card id)
) {
    constructor() : this(User(), Card(), 0) {

    }
}

class UserCardPk : Serializable {
    private val user: Long? = null
    private val card: Long? = null
}

@Entity
@Table(name = "payment_information")
class Payment(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val cardNumber: String,

    @Column(nullable = false, length = 100)
    private val bank: String,

    @Column(nullable = false, length = 8)
    private val expirationDate: String,

    @Column(nullable = false, length = 100)
    private val cardholderName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val cardHolder: User = User(),

    @OneToOne(mappedBy = "payment")
    private val purchase: Purchase? = null
) {
    constructor() : this(null, "", "", "", "") {

    }
}

@Entity
@Table(name = "exchange")
class Exchange(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(name = "exchange_status", nullable = false)
    private val status: Int, // 0: pending, 1: accepted, 2: rejected, 3: canceled, 4: completed

    @Column(name = "created_at", nullable = false)
    private val createdAt: java.sql.Date,

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private val sender: User,

    @ManyToOne
    @JoinColumn(name = "offered_card_id", nullable = false)
    private val offeredCard: Card,

    @ManyToOne
    @JoinColumn(name = "requested_card_id")
    private val requestedCard: Card?,

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private val receiver: User?,

) {
    constructor() : this(null, -1, java.sql.Date(-1), User(), Card(), null, null) {

    }
}

@Entity
@Table(name = "purchase")
class Purchase(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false)
    private val price: BigDecimal,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private val payment: Payment? = null,

    @Column(name = "purchase_date", nullable = false)
    private val date: java.sql.Date = java.sql.Date(-1),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val user_purchase: User = User()
) {
    constructor() : this(null, BigDecimal(0.0)) {

    }
}