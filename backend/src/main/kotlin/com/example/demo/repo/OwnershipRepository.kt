package com.example.demo.repo

import org.springframework.data.jpa.repository.JpaRepository
import com.example.demo.model.Ownership
import org.springframework.stereotype.Repository

@Repository
interface OwnershipRepository: JpaRepository<Ownership, Long> {
}