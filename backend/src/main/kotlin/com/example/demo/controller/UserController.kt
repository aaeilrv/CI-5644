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


@CrossOrigin(value = ["http://localhost:3000"])
@RestController
@RequestMapping("v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var cardService: CardService

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(UserDTO(userService.create(User(request))))
    }

    @PostMapping("/createMultipleUsers")
    fun createUsers(@RequestBody request: List<CreateUserRequest>): String {
        for(r: CreateUserRequest in request) {
            userService.create(User(r))
        }
        return "done " + request.size
    }
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val userOpt = userService.getById(id)
        if (userOpt.isPresent) {
            return ResponseEntity.ok(UserDTO(userOpt.get()))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with $id not found")
        }
    }

    @GetMapping("/leaders")
    fun getLeaders(pageable: Pageable):List<LeaderboardResponse> {
        val allUser = userService.getAll(pageable)
        return userService.getLeaders(allUser)
    }

    @GetMapping()
    fun getAllUsers(pageable: Pageable): List<UserDTO> {
        return userService.getAll(pageable).map {user -> UserDTO(user)}
    }


    @GetMapping("cardsOwned/{id}")
    fun getCardsOwned(@PathVariable id: Long, pageable: Pageable): Page<CardOwnedByUserDTO>?{
        return userService.getCardsOwnedById(id, pageable)?.map {ownership -> CardOwnedByUserDTO(ownership)}
    }

    /*
    @GetMapping("cardsOwned/{id}")
    fun getCardsOwned(@PathVariable id: Long, pageable: Pageable): Page<Card>? {
        return userService.getCardsOwnedById(id, pageable)
    }
*/
    @GetMapping("progress/{id}")
    fun progress(@PathVariable id: Long):String{
        return userService.getProgress(id)
    }
    @GetMapping("progress")
    fun getProgressForAll(pageable: Pageable): List<String>{
        val listProgress:MutableList<String> = mutableListOf()
        val allUser = userService.getAll(pageable)
        for(user:User in allUser){
            listProgress.add("${user.getUsername()}: " + userService.getProgress(user.getId()))
        }
        return listProgress
    }

    @PatchMapping()
    fun addCard(@RequestBody request: AddCardToOwnerRequest):ResponseEntity<UserDTO>{
        val cardOpt = cardService.getById(request.cardId)
        if (cardOpt.isPresent) {
            val userWithNewCard: User? = userService.updateCardsOwnedList(request.ownerId,cardOpt.get())
            return ResponseEntity.ok(if (userWithNewCard == null) null else UserDTO(userWithNewCard))
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ${request.cardId} not found")
        }
    }

    @GetMapping("/hello-oauth")
    fun hello(@RequestHeader("Authorization") token:  String, principal: JwtAuthenticationToken): String {
        println("Llega al controlador")
        println(principal.tokenAttributes)
        return "Hello, $token"
    }
}