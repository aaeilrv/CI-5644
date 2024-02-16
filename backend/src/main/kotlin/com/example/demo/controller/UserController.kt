package com.example.demo.controller

import com.example.demo.controller.dto.AddCardToOwnerRequest
import com.example.demo.controller.dto.CreateUserRequest
import com.example.demo.model.Card
import com.example.demo.model.User
import com.example.demo.service.CardService
import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal


@RestController
@RequestMapping("v1/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var cardService: CardService

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<User> {
        return ResponseEntity.ok(userService.create(User(request)))
    }
    @PostMapping("/createMultipleUsers")
    fun createUsers(@RequestBody request: List<CreateUserRequest>): String {
        for(r: CreateUserRequest in request) {
            userService.create(User(r))
        }
        return "done" + request.size
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

    @GetMapping("/leaders")
    fun getLeaders(pageable: Pageable):List<String>{
        val allUser = userService.getAll(pageable)
        return userService.getLeaders(allUser)
    }

    @GetMapping()
    fun getAllUsers(pageable: Pageable): List<User> {
        return userService.getAll(pageable)
    }


    @GetMapping("cardsOwned/{id}")
    fun getCardsOwned(@PathVariable id: Long):List<Card>?{
        return userService.getCardsOwnedById(id)
    }

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
    fun addCard(@RequestBody request: AddCardToOwnerRequest):ResponseEntity<User>{
        val cardOpt = cardService.getById(request.cardId)
        if (cardOpt.isPresent) {
            val newCard:User? = userService.updateCardsOwnedList(request.ownerId,cardOpt.get())
            return ResponseEntity.ok(newCard)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ${request.cardId} not found")
        }
    }

    @GetMapping("/hello-oauth")
    fun hello(@RequestHeader("x-token") token: String): String {
        return "Hello, " + token
    }
}