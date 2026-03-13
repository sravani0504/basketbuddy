package com.gladiator.BasketBuddy.view.composable


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.model.Item
import com.gladiator.BasketBuddy.model.Screen
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
            .padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$index. ${item.itemName}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF5A3E2B)
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onDecrement,
                    enabled = item.quantity > 1
                ) {
                    Text("-", fontSize = 20.sp,
                        color = Color(0xFFB08968)
                    )
                }
                Text(
                    text = item.quantity.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = onIncrement) {
                    Text("+", fontSize = 20.sp,
                        color = Color(0xFFB08968)
                    )
                }
            }

            Text(
                text = item.itemDescription,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 6.dp)
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
            .padding(8.dp)
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
    val navController = rememberNavController()
    ItemDisplayScreen(navController)
}

@Composable
fun ItemDisplayScreen(
    navController: NavController,
    viewModel: ItemDisplayViewModel = viewModel()
) {
    val items by viewModel.items.collectAsStateWithLifecycle()
    val selectedListName by viewModel.selectedListName.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.loadItemsForSelectedList()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.loadItemsForSelectedList()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFF5EBDD))
    ) {
        TopBar(title = selectedListName.ifBlank { "Items" }, onBackClick = { navController.popBackStack() })

        Box(modifier = Modifier.weight(1f)) {
            Column {
                message?.let {
                    Text(
                        text = it,
                        color = if (it == "Changes saved") Color(0xFF2E7D32) else MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                ItemList(
                    items = items,
                    onIncrement = viewModel::incrementQuantity,
                    onDecrement = viewModel::decrementQuantity
                )

                if (items.isEmpty()) {
                    Text(
                        text = "No items added yet",
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )
                }

                Button(
                    onClick = {
                        viewModel.saveChanges { }
                    },
                    modifier = Modifier
                        .padding(16.dp).align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB08968)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Save Changes",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddItem.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = Color(0xFFB08968)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item",
                    tint = Color.White
                )
            }
        }
        BasketBuddyBottomNav(navController)
    }
}




