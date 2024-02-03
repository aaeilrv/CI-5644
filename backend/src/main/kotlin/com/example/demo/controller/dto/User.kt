package com.example.demo.controller.dto

import java.io.Serializable

class UserDTO (
    private val id: Long,
    private val firstName: String,
    private val lastName: String,
    private val birthDay: String,
    private val username: String,
    private val emailAddress: String
): Serializable {
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