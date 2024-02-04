package com.example.demo.repo

import com.example.demo.model.Card
import com.example.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page

@Repository
interface CardRepository: JpaRepository<Card, Long> {
    fun findByOwners(user: User, pageable: Pageable): Page<Card>
}
