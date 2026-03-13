package com.gladiator.BasketBuddy.network

import com.gladiator.BasketBuddy.db.DatabaseNodes
import com.google.firebase.database.FirebaseDatabase

object FirebaseClient {
    private val database: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    val usersRef = database.getReference(DatabaseNodes.USERS)
    val groupsRef = database.getReference(DatabaseNodes.GROUPS)
    val userGroupsRef = database.getReference(DatabaseNodes.USER_GROUPS)
    val listsRef = database.getReference(DatabaseNodes.LISTS)
    val itemsRef = database.getReference(DatabaseNodes.ITEMS)
}