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
    private val id: Long?,

    @Column(nullable = false, length = 100)
    private var name: String,

    @Column(nullable = false)
    private var playerNumber: Short,

    @Column(name = "photo_url", nullable = true)
    private var photoURL: String?,

    @Column(name= "player_position", nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private var playerPosition: FieldPosition,

    @Column(name = "country", nullable = false)
    private var country: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    private var users: Set<Ownership> = mutableSetOf(),

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

    fun getId(): Long = this.id!!
    fun getName(): String = this.name
    fun getPlayerPosition(): FieldPosition = this.playerPosition
    fun getPlayerNumber(): Short = this.playerNumber
    fun getCountry(): String = this.country
    fun getPhotoURL(): String? = this.photoURL
    fun getUsers(): MutableList<Ownership> = this.users.toMutableList()
    fun setName(newName: String) {
        this.name = newName
    }
}