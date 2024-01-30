package com.example.demo.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.AccessLevel;
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

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

    @Column
    @DateTimeFormat(style = "dd/MM/yyyy")
    private val birthDay: LocalDate,

    @Column(nullable = false, length = 32)
    private val username: String,

    @Column(name = "email_address")
    private val emailAddress: String? = null,

    @Id
    @Column(name = "users_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
)