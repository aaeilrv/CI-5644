package com.example.demo.controller.dto

import java.io.Serializable
import com.example.demo.model.User

data class UserDTO (
    private val id: Long,
    private val firstName: String,
    private val lastName: String,
    private val birthDay: String,
    private val username: String,
    private val emailAddress: String
): Serializable {

    constructor(userObject: User): this(
        userObject.getId(),
        userObject.getFirstName(),
        userObject.getLastName(),
        userObject.getBirthDay().toString(),
        userObject.getUsername(),
        userObject.getEmailAddress()
    )
    fun getId(): Long {
        return this.id
    }

    fun getFirstName(): String {
        return this.firstName
    }

    fun getLastName(): String {
        return this.lastName
    }

    fun getBirthDay(): String {
        return this.birthDay
    }

    fun getUsername(): String {
        return this.username
    }

    fun getEmailAddress(): String {
        return this.emailAddress
    }
}