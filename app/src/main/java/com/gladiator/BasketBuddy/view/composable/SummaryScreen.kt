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
import com.gladiator.BasketBuddy.model.Item


@Composable
fun ItemSummaryRow(
    index: Int,
    item: Item
){
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 16.dp)) {
        Text(text = "$index. ${item.itemName} - ${item.quantity}", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Text(
            text = item.itemDescription,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}


@Composable
fun ItemSummaryList(items: List<Item>){
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
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
fun ItemSummaryScreen(items:List<Item>){
    Column (modifier = Modifier.fillMaxSize()){
        ItemSummaryList(items)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSummary(){
    val items=listOf(Item("Milk","1 litre packet",2),
        Item("Bread","Whole wheat",1))
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween){
        TopBar(title = "Items Summary", onBackClick = {})
        ItemSummaryScreen(items)
        //BasketBuddyBottomNav()
    }
}