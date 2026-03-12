package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gladiator.BasketBuddy.ui.theme.BasketBuddyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String,onBackClick: ()-> Unit) {

    Surface(shadowElevation = 4.dp) {
        TopAppBar(
            title = {
                Text(
                    title,
                    modifier = Modifier.fillMaxWidth()
                        .padding(end = 48.dp),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF5A3E2B),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFFB08968)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFF5EBDD),
                titleContentColor = Color(0xFF5A3E2B)
            ),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopAppBarPreview(){
    BasketBuddyTheme {
        TopBar("Groups",
            onBackClick = { })
    }
}