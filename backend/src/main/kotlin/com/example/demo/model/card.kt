package com.example.demo.model

import jakarta.persistence.*

enum class FieldPosition {
    GOALKEEPER, MIDFIELD, DEFENSE, FORWARD
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
    private val playerNumber: Short,

    @Column(name = "photo_url", nullable = false)
    private val photoURL: String,

    @Column(name= "player_position", nullable = false, columnDefinition = "field_position")
    @Enumerated(EnumType.STRING)
    private val playerPosition: FieldPosition,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private val users: Set<Ownership> = mutableSetOf(),

    ) {
    constructor() : this(null, "", -1, "", FieldPosition.MIDFIELD)
}