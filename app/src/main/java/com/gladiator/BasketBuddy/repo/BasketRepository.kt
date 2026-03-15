package com.gladiator.BasketBuddy.repo

import com.gladiator.BasketBuddy.model.Item
import com.gladiator.BasketBuddy.model.ItemList
import com.gladiator.BasketBuddy.network.FirebaseClient
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class BasketRepository {

    private val listsRef = FirebaseClient.listsRef
//    private val itemsRef = FirebaseClient.itemsRef
    private val itemsRef= FirebaseDatabase.getInstance().getReference("items")

    suspend fun createList(itemList: ItemList): Result<Unit> {
        return try {
            listsRef
                .child(itemList.groupCode)
                .child(itemList.listId.toString())
                .setValue(itemList)
                .await()

            Result.success(Unit)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend fun getListsForGroup(groupCode: String): Result<List<ItemList>> {
        return try {
            val snapshot = listsRef.child(groupCode).get().await()
            val lists = mutableListOf<ItemList>()

            for (child in snapshot.children) {
                val itemList = child.getValue(ItemList::class.java) ?: continue
                lists.add(itemList)
            }
            Result.success(lists.sortedBy { it.name.lowercase() })
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend fun getItemsForList(listId: Int): Result<List<Item>> {
        return try {
            val snapshot = itemsRef.child(listId.toString()).get().await()
            val items = mutableListOf<Item>()

            for (child in snapshot.children) {
                val item = child.getValue(Item::class.java) ?: continue
                items.add(item)
            }

            Result.success(items.sortedBy { it.itemName.lowercase() })
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend fun addItem(item: Item): Result<Unit> {
        return try {
            val itemRef = if (item.itemId.isBlank()) {
                itemsRef.child(item.listId.toString()).push()
            } else {
                itemsRef.child(item.listId.toString()).child(item.itemId)
            }

            val itemToSave = item.copy(itemId = itemRef.key.orEmpty())
            itemRef.setValue(itemToSave).await()

            Result.success(Unit)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend fun updateItems(items: List<Item>): Result<Unit> {
        return try {
            for (item in items) {
                if (item.itemId.isBlank()) continue

                itemsRef
                    .child(item.listId.toString())
                    .child(item.itemId)
                    .setValue(item)
                    .await()
            }

            Result.success(Unit)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    suspend fun itemExists(listId: Int, itemName: String): Boolean {

        return try {

            val snapshot = itemsRef
                .child(listId.toString())
                .get()
                .await()

            for (child in snapshot.children) {

                val item = child.getValue(Item::class.java)

                if (
                    item != null &&
                    item.itemName.equals(itemName.trim(), ignoreCase = true)
                ) {
                    return true
                }
            }

            false

        } catch (e: Exception) {
            false
        }
    }
}