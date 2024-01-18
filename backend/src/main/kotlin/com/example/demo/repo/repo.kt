package com.example.demo.repo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.example.demo.model.User

@Repository
interface Repository: CrudRepository<User, Long> {
    fun findByLastName(lastName: String): Iterable<User>
}