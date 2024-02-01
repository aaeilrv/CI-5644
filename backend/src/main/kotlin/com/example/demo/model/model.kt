package com.example.demo.model

import com.example.demo.controller.dto.CreateUserRequest
import jakarta.persistence.*


@Entity
@Table(name = "users")
open class User (
    @Column(nullable = false, length = 100) private var firstName: String,

    @Column(nullable = false, length = 100) private var lastName: String,

    @Column(nullable = false, length = 100) private var birthDay: String,

    @Column(nullable = false, length = 32) private var username: String,

    @Column(nullable = false, length = 100) private var emailAddress: String,

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @ManyToMany
    @JoinTable(
        name = "card_owner",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "card_id")]
    )
    private val cards: List<Card> = mutableListOf(),

    @OneToMany(mappedBy = "cardHolder", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val user_cards: List<Payment> = mutableListOf(),

    @ManyToMany(mappedBy = "likedUsers")
    private val likes: List<Exchange> = mutableListOf(),

    @OneToMany(mappedBy = "user_purchase", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val user_purchases: List<Purchase> = mutableListOf()
) {
    fun getId(): Long {
        return this.id!!
    }

    fun getFirstName(): String {
        return this.firstName
    }

    fun getLastName(): String {
        return this.lastName
    }

    fun getBirthDay(): String {
        return this.birthDay
    }

    fun getUsername(): String {
        return this.username
    }

    fun getEmailAddress(): String {
        return this.emailAddress
    }

    constructor() : this("", "", "", "", "", null)
    constructor(request: CreateUserRequest) : this(
        request.firstName,
        request.lastName,
        request.birthDay,
        request.username,
        request.emailAddress,
        null
    )
}

@Entity
@Table(name = "cards")
class Card(
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val name: String,

    @Column(nullable = false)
    private val playerNumber: Int,

    @Column(nullable = false, length = 100)
    private val playerPosition: String,

    @ManyToMany(mappedBy = "cards")
    private val owners: List<User> = mutableListOf(),

    @ManyToMany(mappedBy = "likedCards")
    private val likes: List<Exchange> = mutableListOf()
) {
    constructor() : this(null, "", -1, "") {

    }
}

@Entity
@Table(name = "payments")
class Payment(
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val cardNumber: String,

    @Column(nullable = false, length = 100)
    private val bank: String,

    @Column(nullable = false, length = 100)
    private val expirationDate: String,

    @Column(nullable = false, length = 100)
    private val cardHolderName: String,

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
@Table(name = "exchanges")
class Exchange(
    @Id
    @Column(name = "exchange_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val date: String,

    @ManyToMany
    @JoinTable(
        name = "user_like",
        joinColumns = [JoinColumn(name = "exchange_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    private val likedUsers: List<User> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "card_like",
        joinColumns = [JoinColumn(name = "exchange_id")],
        inverseJoinColumns = [JoinColumn(name = "card_id")]
    )
    private val likedCards: List<Card> = mutableListOf()
) {
    constructor() : this(null, "") {

    }
}

@Entity
@Table(name = "purchases")
class Purchase(
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private val date: String,

    @Column(nullable = false)
    private val quantity: Int,

    @Column(nullable = false)
    private val price: Double,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private val payment: Payment? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_purchase_id")
    private val user_purchase: User = User()
) {
    constructor() : this(null, "", 0, 0.0) {

    }
}