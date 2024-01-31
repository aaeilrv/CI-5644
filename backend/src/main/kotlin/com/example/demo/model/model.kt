package com.example.demo.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.AccessLevel;

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "users")
open class User(
    @Column(nullable = false, length = 100)
    private val firstName: String,

    @Column(nullable = false, length = 100)
    private val lastName: String,

    @Column
    private val birthDay: String,

    @Column(nullable = false, length = 32)
    private val username: String,

    @Column(name = "email_address")
    private val emailAddress: String? = null,

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

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

//    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
//    private val user_senders: List<Exchange> = mutableListOf(),
//
//    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
//    private val user_receivers: List<Exchange> = mutableListOf(),

    @OneToMany(mappedBy = "user_purchase", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val user_purchases: List<Purchase> = mutableListOf()
)

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "cards")
class Card(
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false, length = 100)
    private val name: String,

    @Column(nullable = false)
    private val playerNumber: Int,

    @Column(nullable = false, length = 100)
    private val playerPosition: String,

    @ManyToMany(mappedBy = "cards")
    private val owners: List<User> = mutableListOf(),

    @ManyToMany(mappedBy = "likedCards")
    private val likes: List<Exchange> = mutableListOf(),

//    @OneToMany(mappedBy = "senderCard", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
//    private val card_senders: List<Exchange> = mutableListOf(),
//
//    @OneToMany(mappedBy = "receiverCard", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
//    private val card_receivers: List<Exchange> = mutableListOf()
)

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "payments")
class Payment(
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false, length = 100)
    private val cardNumber: String,

    @Column(nullable = false, length = 100)
    private val bank: String,

    @Column(nullable = false, length = 100)
    private val expirationDate: String,

    @Column(nullable = false, length = 100)
    private val cardHolderName: String,

    @ManyToMany(mappedBy = "cards")
    private val owners: List<User> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private val cardHolder: User,

    @OneToOne(mappedBy = "payment")
    private val purchase: Purchase
)

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "exchanges")
class Exchange(
    @Id
    @Column(name = "exchange_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

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
    private val likedCards: List<Card> = mutableListOf(),

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sender_id")
//    private val sender: User,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "receiver_id")
//    private val receiver: User,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sender_card_id")
//    private val senderCard: Card,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "receiver_card_id")
//    private val receiverCard: Card
)

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "purchases")
class Purchase(
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(nullable = false, length = 100)
    private val date: String,

    @Column(nullable = false)
    private val quantity: Int,

    @Column(nullable = false)
    private val price: Double,

    @OneToOne(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    private val payment: Payment,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_purchase_id")
    private val user_purchase: User
)