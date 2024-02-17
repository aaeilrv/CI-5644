package com.example.demo.controller.dto

data class AddCardToOwnerRequest (
    val ownerId: Long,
    val cardId:Long
)