package com.gladiator.BasketBuddy.view.composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.model.Item
import com.gladiator.BasketBuddy.viewmodel.ItemDisplayViewModel

@Composable
fun ItemCard(
    index: Int,
    item: Item,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$index. ${item.itemName}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onDecrement,
                    enabled = item.quantity > 1
                ) {
                    Text("-", fontSize = 18.sp)
                }

                Text(
                    text = item.quantity.toString(),
                    fontSize = 16.sp
                )

                IconButton(onClick = onIncrement) {
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


//@Composable
//fun ItemList(items: List<Item>) {
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth().padding(16.dp)) {
//            itemsIndexed(items) { index, item ->
//                Column {  }
//                ItemCard(
//                    index = index + 1,
//                    item = item
//                )
//            }
//       }
////        Button(onClick = {onSaveClick()},
////            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
////            Text("Save")
////        }
//
//}
@Composable
fun ItemList(
    items: List<Item>,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            ItemCard(
                index = index + 1,
                item = item,
                onIncrement = { onIncrement(index) },
                onDecrement = { onDecrement(index) }
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ItemDisplayScreen(navController: NavController){
//    val sampleItems=listOf(Item("Milk", "1 litre packet"),
//        Item("Bread","Whole Wheat bread"))
//    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top){
//        TopBar(title = "Groceries", onBackClick = {})
//        Box(modifier = Modifier.weight(1f)){
//            ItemList(sampleItems)
//        }
////        ItemList(sampleItems)
//        Button(onClick = {}, modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp)) {
//            Text("Save")
//        }
//        BasketBuddyBottomNav(navController)
//    }
//}


@Preview(showBackground = true)
@Composable
fun itemdisplayPreview(){
    var navController = rememberNavController()
    ItemDisplayScreen(navController)
}

@Composable
fun ItemDisplayScreen(
    navController: NavController,
    viewModel: ItemDisplayViewModel = viewModel()
) {
    val items by viewModel.items.collectAsState()

    // TEMP: simulate receiving from another screen
    LaunchedEffect(Unit) {
        viewModel.setItems(
            listOf(
                Item("Milk", "1 litre packet"),
                Item("Bread", "Whole Wheat bread")
            )
        )
    }


    val bottomBarHeight = 64.dp
    val fabBottomPadding = bottomBarHeight + 16.dp

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = "Groceries", onBackClick = { navController.popBackStack() })

        Box(modifier = Modifier.weight(1f)) {
            Column {
                ItemList(
                    items = items,
                    onIncrement = viewModel::incrementQuantity,
                    onDecrement = viewModel::decrementQuantity
                )
                Button(
                    onClick = {
                        // items here contain updated quantities
                        navController.navigate("summaryscreen")
                    },
                    modifier = Modifier
                        .padding(8.dp).align(Alignment.CenterHorizontally)
                ) {
                    Text("Save")
                }
            }
            // === Add (+) Floating Button, above the bottom nav bar ===
            FloatingActionButton(
                onClick = {
                    navController.navigate("addItem")
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item"
                )
            }
        }



        BasketBuddyBottomNav(navController)
    }
}




