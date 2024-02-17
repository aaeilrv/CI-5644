package com.example.demo.model

import jakarta.persistence.*
import java.io.Serializable

class OwnershipPk : Serializable {
    private val user: Long? = null
    private val card: Long? = null
}

@Entity
@Table(name = "ownership")
@IdClass(OwnershipPk::class)
class Ownership(
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private val user: User,

    @Id
    @ManyToOne
    @JoinColumn(name = "card_id")
    private val card: Card,

    @Column(name = "number_of_cards_owned", nullable = false)
    private val numberOwned: Int, // El número de cartas que tiene el usuario de tipo (card id)
) {
    constructor() : this(User(), Card(), 0)
}

