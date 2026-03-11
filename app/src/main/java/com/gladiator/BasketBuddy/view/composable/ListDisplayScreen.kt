package com.gladiator.BasketBuddy.view.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gladiator.BasketBuddy.R

@Preview(showBackground = true)
@Composable
fun ListScreen() {
    val hint = "Search for groups..."

    Column {
        TopBar("Lists", onBackClick = {})
        ListDisplayScreen(
            hint = hint,
            onSearch = { message ->
                println("Searching for: $message")
            }
        )
        Spacer(modifier = Modifier.height(500.dp))
        AddListIcon {  }
        BasketBuddyBottomNav()
    }
}

@Composable
fun ListDisplayScreen(
    hint: String,
    onSearch: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }

    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
    )

    LaunchedEffect(message) {
        onSearch(message)
    }
}

fun HomeClick(){}
fun GroupClick(){}
fun ListClick(){}

@Composable
fun AddListIcon(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.outline_playlist_add_24),
                contentDescription = "Add List",
                modifier = Modifier.size(42.dp)
            )
        }
    }
}
