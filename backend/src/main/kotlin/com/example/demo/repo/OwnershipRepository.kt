package com.example.demo.repo

import org.springframework.data.jpa.repository.JpaRepository
import com.example.demo.model.Ownership
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.example.demo.model.User
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

@Repository
interface OwnershipRepository: JpaRepository<Ownership, Long> {
    @Query(nativeQuery = true,
        value = """
            SELECT o.*
            FROM card c LEFT JOIN (
            SELECT *
            FROM ownership
            WHERE ownership.user_id = :#{#user_object.id}
            ) o ON c.id = o.card_id;
        """)
    fun findByUserOrderByCardCountry(@Param("user_object") user: User, pageable: Pageable): Optional<Page<Ownership>>
}
