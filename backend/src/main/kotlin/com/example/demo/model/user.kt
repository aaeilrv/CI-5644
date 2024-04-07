package com.example.demo.model

import com.example.demo.controller.dto.CreateUserRequest
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*


@Entity
@Table(name = "users")
class User (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "auth0_sub")
    val auth0Sub: String,

    @Column(nullable = false, length = 100)
    var firstName: String,

    @Column(nullable = false, length = 100)
    var lastName: String,

    @Column(nullable = false, length = 100)
    var birthday: java.sql.Date,

    @Column(nullable = false, length = 32)
    var username: String,

    @Column(nullable = false, length = 100)
    var email: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    var cards: MutableList<Ownership> = mutableListOf(), // El conjunto de cartas que tiene el usuario

    @OneToMany(mappedBy = "cardHolder", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    var userCreditCards: List<CreditCard> = mutableListOf(),

    @OneToMany(mappedBy = "purchasingUser", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    var userPurchases: List<Purchase> = mutableListOf()
) {
    constructor() : this(null, "", "", "", java.sql.Date(-1), "", "")
    constructor(request: CreateUserRequest, auth0Sub: String) : this(
        null,
        auth0Sub,
        request.firstName,
        request.lastName,
        java.sql.Date.valueOf(request.birthDay),
        request.username,
        request.emailAddress
    )
}