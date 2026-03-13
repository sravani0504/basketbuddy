package com.gladiator.BasketBuddy.view.composable

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        .padding(vertical = 12.dp, horizontal = 16.dp)) {
        Text(text = "$index. ${item.itemName} - ${item.quantity}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A3E2B)
        )
        Text(
            text = item.itemDescription, fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun ItemSummaryList(items: List<Item>, modifier: Modifier = Modifier){

    LazyColumn(modifier = modifier.padding(top = 12.dp)) {
        itemsIndexed(items){
                index, item ->
            Card (modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                ItemSummaryRow(
                    index=index+1,
                    item=item
                )
            }
        }
    }
}


@Composable
fun ItemSummaryScreen(items: List<Item>, modifier: Modifier = Modifier, navController: NavController){

    Column(Modifier.fillMaxSize().background(Color(0xFFF5EBDD))
    ) {
        TopBar(title = "Items Summary", onBackClick = {navController.popBackStack()})
        ItemSummaryList(items, modifier = modifier.weight(1f))
        BasketBuddyBottomNav(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSummary(){
    val items = listOf(
        Item(itemName = "Milk", itemDescription = "1 litre packet", quantity = 2),
        Item(itemName = "Bread", itemDescription = "Whole wheat", quantity = 1)
    )
    Column(modifier = Modifier.fillMaxSize()) {
        var navController = rememberNavController()
        ItemSummaryScreen(items, modifier = Modifier.weight(1f), navController)
    }
}
