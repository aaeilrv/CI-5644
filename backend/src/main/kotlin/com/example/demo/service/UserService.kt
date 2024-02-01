package com.example.demo.service

import com.example.demo.controller.dto.CreateUserRequest
import com.example.demo.controller.dto.UserDTO
import com.example.demo.model.User
import com.example.demo.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserService (@Autowired private val userRepository: UserRepository) {
//    public fun findAll(): List<UserDTO> {
//        return userRepository.findAll()
//            .stream()
//            .map(this::fromEntityToDTO)
//            .collect(Collectors.toList())
//    }

    public fun create(user: User): User {
        return userRepository.save(user)
    }

    public fun getById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    public fun getAll(pageable: Pageable): List<User> {
        val userEntities = userRepository.findAll(pageable)
        val users = userEntities.map { userEntity ->
            User(
                id = userEntity.getId(),
                firstName = userEntity.getFirstName(),
                lastName = userEntity.getLastName(),
                birthDay = userEntity.getBirthDay(),
                username = userEntity.getUsername(),
                emailAddress = userEntity.getEmailAddress()
            )
        }
        return users.content
    }

//    private fun fromEntityToDTO(entity: User): UserDTO {
//        return UserDTO(
//            entity.getId(),
//            entity.getFirstName(),
//            entity.getLastName(),
//            entity.getBirthDay(),
//            entity.getUsername(),
//            entity.getEmailAddress()
//        )
//    }
}