package com.gladiator.BasketBuddy.view.composable



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gladiator.BasketBuddy.ui.theme.BasketBuddyTheme
import kotlinx.coroutines.delay
import com.gladiator.BasketBuddy.R



@Composable
fun SplashScreen(onNavigate: () -> Unit){
    LaunchedEffect(Unit) {
        delay(2500)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFD2B48C),
                Color(0xFFB08968)))),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ){
              Image(
                  painter = painterResource(R.drawable.baseline_shopping_basket),
                  contentDescription = "Basket Image",
                  modifier = Modifier.size(60.dp)
              )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Basket Buddy",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                
                )

        }
    }



}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
        BasketBuddyTheme {
            SplashScreen(onNavigate = { })
        }
}
