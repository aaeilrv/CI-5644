package com.example.demo.controller.dto

import com.example.demo.model.Ownership

data class CardOwnedByUserDTO(
    val cardId: Long,
    val playerName: String,
    val playerPosition: String,
    val playerNumber: Short,
    val numberOwned: Int,
    val country: String,
    val photoUrl: String
) {
    constructor(ownershipObject: Ownership): this(
        ownershipObject.card.id!!,
        ownershipObject.card.name,
        ownershipObject.card.playerPosition.toString(),
        ownershipObject.card.playerNumber,
        ownershipObject.numberOwned,
        ownershipObject.card.country,
        ownershipObject.card.photoURL!!
    )


}

data class UserWhoOwnsCardDTO(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val username: String,
    val email: String,
    val numberOwned: Int
) {
    constructor(ownershipObject: Ownership): this(
        ownershipObject.user.id!!,
        ownershipObject.user.firstName,
        ownershipObject.user.lastName,
        ownershipObject.user.birthday.toString(),
        ownershipObject.user.username,
        ownershipObject.user.email,
        ownershipObject.numberOwned
    )
}
