package com.gladiator.BasketBuddy.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Item(
    var itemId: String = "",
    var itemName: String = "",
    var itemDescription: String = "",
    var quantity: Int = 0,
    var listId: Int = 0
) {
    constructor() : this("", "", "", 0, 0)
}