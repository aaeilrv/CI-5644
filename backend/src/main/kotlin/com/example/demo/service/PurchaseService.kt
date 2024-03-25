package com.example.demo.service

import com.example.demo.controller.dto.*
import com.example.demo.model.*
import com.example.demo.repo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import kotlin.NoSuchElementException

@Service
class PurchaseService(@Autowired private val purchaseRepository: PurchaseRepository) {


}