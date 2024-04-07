package com.example.demo.service

import com.example.demo.controller.dto.CreateUserRequest
import com.example.demo.controller.dto.LeaderboardResponse
import com.example.demo.controller.dto.UserDTO
import com.example.demo.model.User
import com.example.demo.model.Card
import com.example.demo.repo.UserRepository
import com.example.demo.model.Ownership
import com.example.demo.repo.CardRepository
import com.example.demo.repo.OwnershipRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import com.example.demo.service.CardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.stream.Collectors
import kotlin.NoSuchElementException

@Service
class UserService (@Autowired private val userRepository: UserRepository,
                   @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(user: User): User {
        return userRepository.save(user)
    }

    public fun getBySub(sub: String): User {
        return userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("no user with sub $sub found.")
        }
    }

    public fun getById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            throw NoSuchElementException("no user with id $id found.")
        }
    }


    public fun getCardsOwnedBySub(sub: String, pageable: Pageable): Page<Ownership> {
        val user = userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("no user with sub $sub found.")
        }
        return ownershipRepository.findByUserOrderByCardCountry(user, pageable).orElseThrow {
            throw UnknownError("error occurred while fetching results")
        }
    }

    public fun addSingleCard(sub: String, card : Card): User {
        val user = userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("no user with sub $sub found.")
        }
        //See if user already owns card
        val ownershipValue: Ownership? = user.cards.firstOrNull {it.card.id == card.id}
        if (ownershipValue == null) {
            val newOwnershipValue = Ownership(
                null,
                user,
                card,
                1
            )
            user.cards.add(newOwnershipValue)
            ownershipRepository.save(newOwnershipValue)
        } else {
            ownershipValue.numberOwned += 1
            ownershipRepository.save(ownershipValue)
        }
        return userRepository.save(user)
    }

    fun addMultipleCards(sub: String, cards: List<Card>): User {
        val user = userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("User not found.")
        }
        val ownershipValues = user.cards
        cards.forEach { card ->
           val ownershipValue: Ownership? = ownershipValues.firstOrNull { it.card.id == card.id }
            if (ownershipValue == null) {
                val newOwnership = Ownership(
                    null,
                    user,
                    card,
                    1
                )
                ownershipValues.add(newOwnership)
            } else {
                ownershipValue.numberOwned += 1
            }
        }
        ownershipRepository.saveAll(ownershipValues)
        return userRepository.save(user)
    }

    public fun getAll(pageable: Pageable): List<User> {
        return userRepository.findAll(pageable).content
    }

    private fun calculateProgress(user: User): BigDecimal {
        val numberOwned = user.cards.size
        val percentage = BigDecimal(numberOwned*100.00/670).setScale(2, RoundingMode.HALF_EVEN)
        return percentage
    }

    fun getProgress(sub: String): String {
        val user = userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("no user with sub $sub found")
        }
        return calculateProgress(user).toString()
    }

    fun getLeaders(allUser: List<User>): List<LeaderboardResponse> {
        val listLeaders: MutableList<Pair<User, BigDecimal>> = mutableListOf()
        var counter = 1
        for (user: User in allUser) {
            listLeaders.add(user to calculateProgress(user))
        }
        listLeaders.sortByDescending { it.second }
        val listLeadersForPrinting: MutableList<LeaderboardResponse> = mutableListOf()
        for (leaders: Pair<User, BigDecimal> in listLeaders) {
            listLeadersForPrinting.add(
                    LeaderboardResponse(
                            counter,
                            leaders.first.username,
                            leaders.first.cards.size
                    )
            )
            counter += 1
        }
        return listLeadersForPrinting
    }

    fun editUserData(sub: String, body : Map<String, String>) : User{
        val user = userRepository.findByAuth0Sub(sub).orElseThrow {
            throw NoSuchElementException("user with sub $sub not found.")
        }

        user.firstName = body["firstName"]!!
        user.lastName = body["lastName"]!!
        user.username = body["username"]!!
        user.email = body["email"]!!

        return userRepository.save(user)
    }
}
