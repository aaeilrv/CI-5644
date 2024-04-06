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
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.*


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

    @PostMapping("/createMultipleUsers")
    fun createUsers(@RequestBody request: List<CreateUserRequest>): String {
        for(r: CreateUserRequest in request) {
            userService.create(User(r, ""))
        }
        return "done " + request.size
    }

    @GetMapping()
    fun getUserBySub(@RequestHeader("Authorization") token: String,
                    principal: JwtAuthenticationToken): ResponseEntity<UserDTO> {
        val sub: String = principal.tokenAttributes["sub"].toString()
        val user = userService.getBySub(sub).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with $sub not found")
        }

        return ResponseEntity.ok(UserDTO(user))
    }

    @GetMapping("/leaders")
    fun getLeaders(pageable: Pageable):List<LeaderboardResponse> {
        val allUser = userService.getAll(pageable)
        return userService.getLeaders(allUser)
    }

    @GetMapping("/all")
    fun getAllUsers(pageable: Pageable): List<UserDTO> {
        return userService.getAll(pageable).map {user -> UserDTO(user)}
    }

    @GetMapping("/cardsOwned")
    fun getCardsOwned(principal: JwtAuthenticationToken,
                      pageable: Pageable): Page<CardOwnedByUserDTO?>?{
        val sub = principal.tokenAttributes["sub"].toString()
        return userService.getCardsOwnedBySub(sub, pageable)?.map {ownership ->
            if (ownership == null) {
                null
            } else {
                CardOwnedByUserDTO(ownership)
            }
        }
    }

    @GetMapping("/progress")
    fun progress(principal: JwtAuthenticationToken): ResponseEntity<String>{
        val sub = principal.tokenAttributes["sub"] ?: return ResponseEntity.internalServerError().build()
        return ResponseEntity.ok(userService.getProgress(sub.toString()))
    }

    @GetMapping("progressAll")
    fun getProgressForAll(pageable: Pageable): List<String>{
        val listProgress:MutableList<String> = mutableListOf()
        val allUser = userService.getAll(pageable)
        for(user:User in allUser){
            listProgress.add("${user.username}: " + userService.getProgress(user.auth0Sub))
        }
        return listProgress
    }

    @PatchMapping()
    fun addCard(@RequestBody request: AddCardToOwnerRequest):ResponseEntity<UserDTO>{
        val card = cardService.getById(request.cardId).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ${request.cardId} not found")
        }
        val userWithNewCard: User? = userService.addSingleCard(request.ownerSub, card)
        return ResponseEntity.ok(if (userWithNewCard == null) null else UserDTO(userWithNewCard))
    }

    @PutMapping("/edit")
    fun profileEdit (principal: JwtAuthenticationToken,
                     @RequestBody request : Map<String, String>): ResponseEntity<UserDTO>{

        val sub: String = principal.tokenAttributes["sub"].toString()
        val current = userService.getBySub(sub).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with $sub not found")
        }
        return ResponseEntity.ok(UserDTO(userService.editUserData(current, request)))
    }

    @GetMapping("/hello-oauth")
    fun hello(@RequestHeader("Authorization") token:  String, principal: JwtAuthenticationToken): String {
        println("Llega al controlador")
        println(principal.tokenAttributes)
        return "Hello, $token"
    }
}
