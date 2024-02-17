package com.example.demo.controller.dto

data class CreateCardRequest (
    val name: String,
    val playerNumber: Int,
    val playerPosition: String,
    val country:String
)