package com.example.demo.controller.dto

import java.io.Serializable
import com.example.demo.model.User

data class UserDTO (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val birthDay: String,
    val username: String,
    val emailAddress: String
): Serializable {

    constructor(userObject: User): this(
        userObject.id!!,
        userObject.firstName,
        userObject.lastName,
        userObject.birthday.toString(),
        userObject.username,
        userObject.email
    )
}