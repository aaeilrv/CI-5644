package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "ownership")
class Ownership(

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "card_id")
    val card: Card,

    @Column(name = "number_of_cards_owned", nullable = false)
    var numberOwned: Int, // El número de cartas que tiene el usuario de tipo (card id)
) {
    constructor() : this(null, User(), Card(), 0)
}

