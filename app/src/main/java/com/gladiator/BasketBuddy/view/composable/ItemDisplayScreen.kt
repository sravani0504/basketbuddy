package com.gladiator.BasketBuddy.view.composable

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp


//@Composable
//fun ItemDisplay(navController:NavC){
//    Scaffold(topBar = {
//        TopBar(
//            title = "Groceries",
//            onBackClick = {}
//        ) { }
//    }) {  }
//}



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar(title: String,onBackClick: ()-> Unit){
//    TopAppBar(
//        title = {
//            Text(title, modifier = Modifier.padding(70.dp,0.dp))
//        },
//        navigationIcon = {
//            IconButton(onClick = onBackClick) {
//                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//
//            }
//        }
//    )
//}


@Preview(showBackground = true)
@Composable
fun GroceryTopBarPreview(){
    TopBar(title = "Groceries", onBackClick = {})
}