package com.example.demo.model

import com.example.demo.controller.dto.CreateUserRequest
import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "users")
open class User (
    @Column(nullable = false, length = 100) private var firstName: String,

    @Column(nullable = false, length = 100) private var lastName: String,

    @Column(nullable = false, length = 100) private var birthday: java.sql.Date,

    @Column(nullable = false, length = 32) private var username: String,

    @Column(nullable = false, length = 100) private var email: String,

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,

    @ManyToMany
    @JoinTable(
        name = "ownership",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "card_id")]
    )
    private val ownedCards: List<Card> = mutableListOf(),

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

    constructor() : this("", "", java.sql.Date(-1), "", "",null)
    constructor(request: CreateUserRequest) : this(
        request.firstName,
        request.lastName,
        java.sql.Date.valueOf(request.birthDay),
        request.username,
        request.emailAddress,
        null
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

    @ManyToMany(mappedBy = "ownedCards")
    private val owners: List<User> = mutableListOf(),

) {
    constructor() : this(null, "", "", "") {

    }
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

    @Column(nullable = false, length = 100)
    private val date: java.sql.Date,

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private val owner: User,

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private val exchangedCard: Card,

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private val receiver: User,

    @Column(name = "number_of_cards_traded", nullable = false)
    private val exchangedAmount: Int

) {
    constructor() : this(null, java.sql.Date(-1), User(), Card(), User(), -1) {

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