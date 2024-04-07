package com.example.demo.controller

import com.example.demo.controller.dto.*
import com.example.demo.model.Card
import com.example.demo.model.User
import com.example.demo.service.CardService
import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal
import com.example.demo.security.SecurityConfig
import org.apache.coyote.Response
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.*
import kotlin.NoSuchElementException


@CrossOrigin(value = ["http://localhost:3000", "http://207.246.119.200:3000"])
@RestController
@RequestMapping("v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var cardService: CardService

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest,
                   principal: JwtAuthenticationToken): ResponseEntity<UserDTO> {
        val sub = principal.tokenAttributes["sub"]?.toString() ?: return ResponseEntity.internalServerError().build()
        return ResponseEntity.ok(UserDTO(userService.create(User(request, sub))))
    }

    @GetMapping()
    fun getUserBySub(principal: JwtAuthenticationToken): ResponseEntity<UserDTO> {
        val sub = principal.tokenAttributes["sub"]?.toString() ?: return ResponseEntity.internalServerError().build()
        return try {
            ResponseEntity.ok(UserDTO(userService.getBySub(sub)))
        } catch(e: Exception) {
            when (e) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }

    @GetMapping("/leaders")
    fun getLeaders(pageable: Pageable): ResponseEntity<List<LeaderboardResponse>> {
        val allUser = userService.getAll(pageable)
        return try {
            ResponseEntity.ok(userService.getLeaders(allUser))
        } catch (e: Exception) {
            ResponseEntity.internalServerError().build()
        }
    }

    @GetMapping("/all")
    fun getAllUsers(pageable: Pageable): List<UserDTO> {
        return userService.getAll(pageable).map {user -> UserDTO(user)}
    }

    @GetMapping("/cardsOwned")
    fun getCardsOwned(principal: JwtAuthenticationToken, pageable: Pageable): ResponseEntity<Page<CardOwnedByUserDTO?>> {
        val sub = principal.tokenAttributes["sub"]?.toString() ?: return ResponseEntity.internalServerError().build()
        return try {
            ResponseEntity.ok(userService.getCardsOwnedBySub(sub, pageable).map { ownership ->
                if (ownership == null) {
                    null
                } else {
                    CardOwnedByUserDTO(ownership)
                }
            })
        } catch(e: Exception) {
            when (e) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }

    @GetMapping("/progress")
    fun progress(principal: JwtAuthenticationToken): ResponseEntity<String>{
        val sub = principal.tokenAttributes["sub"] ?: return ResponseEntity.internalServerError().build()
        return try {
            ResponseEntity.ok(userService.getProgress(sub.toString()))
        } catch(e: Exception) {
            when (e) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }

    @GetMapping("progressAll")
    fun getProgressForAll(pageable: Pageable): ResponseEntity<List<String>> {
        val listProgress:MutableList<String> = mutableListOf()
        val allUser = userService.getAll(pageable)
        for(user:User in allUser){
            listProgress.add("${user.username}: " + userService.getProgress(user.auth0Sub))
        }
        return ResponseEntity.ok(listProgress)
    }

    @PatchMapping()
    fun addCard(@RequestBody request: AddCardToOwnerRequest): ResponseEntity<UserDTO>{
        val card = cardService.getById(request.cardId).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ${request.cardId} not found")
        }
        return try {
            ResponseEntity.ok(UserDTO(userService.addSingleCard(request.ownerSub, card)))
        } catch(e: Exception) {
            when (e) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }

    @PutMapping("/edit")
    fun profileEdit (principal: JwtAuthenticationToken,
                     @RequestBody request : Map<String, String>): ResponseEntity<UserDTO>{
        val sub = principal.tokenAttributes["sub"]?.toString() ?: return ResponseEntity.internalServerError().build()
        return try {
            ResponseEntity.ok(UserDTO(userService.editUserData(sub, request)))
        } catch(e: Exception) {
            when (e) {
                is NoSuchElementException -> ResponseEntity.notFound().build()
                else -> ResponseEntity.internalServerError().build()
            }
        }
    }
}
