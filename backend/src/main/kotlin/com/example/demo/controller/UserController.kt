package com.example.demo.controller

import com.example.demo.controller.dto.CreateUserRequest
import com.example.demo.controller.dto.UserDTO
import com.example.demo.model.User
import com.example.demo.service.UserService
import jakarta.persistence.Entity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<User> {
        return ResponseEntity.ok(userService.create(User(request)))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val userOpt = userService.getById(id)
        if (userOpt.isPresent) {
            return ResponseEntity.ok(userOpt.get())
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with $id not found")
        }
    }

    @GetMapping()
    fun getAllUsers(pageable: Pageable): List<User> {
        return userService.getAll(pageable)
    }
}