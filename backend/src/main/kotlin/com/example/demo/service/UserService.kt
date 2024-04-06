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

@Service
class UserService (@Autowired private val userRepository: UserRepository,
                   @Autowired private val ownershipRepository: OwnershipRepository) {

    public fun create(user: User): User {
        return userRepository.save(user)
    }

    public fun getBySub(sub: String): Optional<User> {
        return userRepository.findByAuth0Sub(sub)
    }

    public fun getById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }


    public fun getCardsOwnedBySub(sub: String, pageable: Pageable): Page<Ownership>?{
        val exists: Optional<User> = getBySub(sub)
        if(exists.isPresent) {
            val user: User = exists.get()
            return ownershipRepository.findByUserOrderByCardCountry(user, pageable)
        }
        else{
            return null
        }
    }

    public fun addSingleCard(sub: String, card : Card): User?{
        val exists: Optional<User> = getBySub(sub)
        if(exists.isPresent){
            val user: User = exists.get()
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
        return null
    }

    fun addMultipleCards(sub: String, cards: List<Card>): User {
        val user = getBySub(sub).orElseThrow { NoSuchElementException("User not found.") }
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
        val userEntities = userRepository.findAll(pageable)
        val users = userEntities.map { it }
        return users.content
    }

    private fun calculateProgress(user: User): BigDecimal {
        val numberOwned = user.cards.size
        val percentage = BigDecimal(numberOwned*100.00/670).setScale(2, RoundingMode.HALF_EVEN)
        return percentage
    }

    fun getProgress(sub: String): String {
        val user = getBySub(sub).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,
            "user with $sub not found.")
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

    fun editUserData(user: User, body : Map<String, String>) : User{

        user.firstName = body["firstName"]!!
        user.lastName = body["lastName"]!!
        user.username = body["username"]!!
        user.email = body["email"]!!

        return userRepository.save(user)
    }
}
