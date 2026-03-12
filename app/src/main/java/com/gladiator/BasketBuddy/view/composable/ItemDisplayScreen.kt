package com.gladiator.BasketBuddy.view.composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gladiator.BasketBuddy.model.Item

@Composable
fun ItemCard(
    index: Int,
    item: Item
) {
    var quantity by remember { mutableStateOf(item.quantity) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$index. ${item.itemName}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        if (quantity > 1) quantity--
                    }
                ) {
                    Text("-", fontSize = 18.sp)
                }
                Text(
                    text = quantity.toString(),
                    fontSize = 16.sp
                )
                IconButton(
                    onClick = {
                        quantity++
                    }
                ) {
                    Text("+", fontSize = 18.sp)
                }
            }
            Text(
                text = item.itemDescription,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun ItemList(items: List<Item>) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            itemsIndexed(items) { index, item ->
                Column {  }
                ItemCard(
                    index = index + 1,
                    item = item
                )
            }
       }
//        Button(onClick = {onSaveClick()},
//            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
//            Text("Save")
//        }

}

@Preview(showBackground = true)
@Composable
fun ItemDisplayScreen(){
    val sampleItems=listOf(Item("Milk", "1 litre packet"),
        Item("Bread","Whole Wheat bread"))
    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween){
        TopBar(title = "Groceries", onBackClick = {})
        ItemList(sampleItems)
        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Save")
        }
        BasketBuddyBottomNav()
    }
}
