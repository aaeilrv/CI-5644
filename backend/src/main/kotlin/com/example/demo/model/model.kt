package com.example.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table

@Entity
@Table(name="users")
open class User(
    private val firstName: String,
    private val lastName: String,
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private val id: Long = -1
) {
    constructor() : this("", "")

    open fun getId(): Long {
        return this.id
    }

    open fun getFirstName(): String {
        return this.firstName
    }

    open fun getLastName(): String {
        return this.lastName
    }

}