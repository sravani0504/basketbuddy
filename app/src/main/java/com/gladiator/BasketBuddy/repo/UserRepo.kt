package com.gladiator.BasketBuddy.repo

import com.gladiator.BasketBuddy.model.Item
import com.google.firebase.database.FirebaseDatabase

class ItemRepo {

    fun addItem(item: Item){
        var dbRef = FirebaseDatabase.getInstance().getReference("Items")
        dbRef.child("Id").setValue(item)
    }




}