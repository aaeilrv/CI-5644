package com.example.demo.repo

import org.springframework.data.jpa.repository.JpaRepository
import com.example.demo.model.Ownership
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.example.demo.model.User

@Repository
interface OwnershipRepository: JpaRepository<Ownership, Long> {
    public fun findByUser(user: User, pageable: Pageable): Page<Ownership>
}