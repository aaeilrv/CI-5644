package com.example.demo.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.AccessLevel;

@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name="users")
open class User(
    @Column(nullable = false, length = 100)
    private val firstName: String,

    @Column(nullable = false, length = 100)
    private val lastName: String,

    @Column(nullable = false, length = 32)
    private val username: String,

    @Column(name = "email_address")
    private val emailAddress: String? = null,

    @Id
    @GeneratedValue
    private val id: Long = -1
)