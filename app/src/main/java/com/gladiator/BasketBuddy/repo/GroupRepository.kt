package com.gladiator.BasketBuddy.repo


import com.gladiator.BasketBuddy.model.Group
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class GroupRepository {

    private val database = FirebaseDatabase.getInstance()
    private val groupsRef = database.getReference("groups")

    suspend fun createGroup(group: Group): Result<Boolean> {
        return try {

            groupsRef.child(group.groupCode).setValue(group).await()

            Result.success(true)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    suspend fun getGroups(): Result<List<Group>> {

        return try {

            val snapshot = groupsRef.get().await()

            val groupList = mutableListOf<Group>()

            for (child in snapshot.children) {

                val group = child.getValue(Group::class.java)

                if (group != null) {
                    groupList.add(group)
                }
            }

            Result.success(groupList)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}