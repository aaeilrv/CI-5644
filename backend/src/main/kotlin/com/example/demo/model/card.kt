package com.example.demo.model

import jakarta.persistence.*
import com.example.demo.controller.dto.CreateCardRequest
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Type
import org.hibernate.dialect.PostgreSQLEnumJdbcType

public enum class FieldPosition {
    GOALKEEPER, MIDFIELDER, DEFENDER, FORWARD
}

@Entity
@Table(name = "card")
class Card(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false, length = 100)
    var name: String,

    @Column(nullable = false)
    var playerNumber: Short,

    @Column(name = "photo_url", nullable = true)
    var photoURL: String?,

    @Column(name= "player_position", nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    var playerPosition: FieldPosition,

    @Column(name = "country", nullable = false)
    var country: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    var users: MutableList<Ownership> = mutableListOf(),

    ) {
    constructor() : this(null, "", -1, "", FieldPosition.MIDFIELDER, "")

    constructor(request: CreateCardRequest): this(
        null,
        request.name,
        request.playerNumber,
        request.photoURL,
        FieldPosition.valueOf(request.playerPosition.uppercase()),
        request.country
    )
}