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
    private val id: Long?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    private val user: User,

    @ManyToOne
    @JoinColumn(name = "card_id")
    private val card: Card,

    @Column(name = "number_of_cards_owned", nullable = false)
    private var numberOwned: Int, // El número de cartas que tiene el usuario de tipo (card id)
) {
    constructor() : this(null, User(), Card(), 0)

    fun getId(): Long? = this.id
    fun getCard(): Card = this.card
    fun getUser(): User = this.user
    fun getNumberOwned(): Int = this.numberOwned
    fun setNumberOwned(newValue: Int) {
        this.numberOwned = newValue
    }
}

