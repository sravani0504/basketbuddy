package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.model.Item

@Composable
fun ItemSummaryRow(
    index: Int,
    item: Item
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 16.dp)) {
        Text(text = "$index. ${item.itemName} - ${item.quantity}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(
            text = item.itemDescription, fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}


@Composable
fun ItemSummaryList(items: List<Item>, modifier: Modifier = Modifier){

    LazyColumn(modifier = modifier.padding(top = 10.dp)) {
        itemsIndexed(items){
                index, item ->
            ItemSummaryRow(
                index=index+1,
                item=item
            )
        }
    }
}


@Composable
fun ItemSummaryScreen(items: List<Item>, modifier: Modifier = Modifier, navController: NavController){

    Column(Modifier.fillMaxSize()) {
        TopBar(title = "Items Summary", onBackClick = {})
        ItemSummaryList(items, modifier = modifier)
        BasketBuddyBottomNav(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSummary(){
    val items = listOf(
        Item("Milk", "1 litre packet", 2),
        Item("Bread", "Whole wheat", 1)
    )
    Column(modifier = Modifier.fillMaxSize()) {
        var navController = rememberNavController()
        ItemSummaryScreen(items, modifier = Modifier.weight(1f), navController)
    }
}
