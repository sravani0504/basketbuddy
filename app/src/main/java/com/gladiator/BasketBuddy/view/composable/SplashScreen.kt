package com.gladiator.BasketBuddy.view.composable



import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gladiator.BasketBuddy.ui.theme.BasketBuddyTheme
import kotlinx.coroutines.delay
import com.gladiator.BasketBuddy.R

@Composable
fun SplashScreen(onNavigate: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2500)
        onNavigate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD2B48C),
                        Color(0xFFB08968)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center
            ) {

                // Motion lines behind basket
                Canvas(
                    modifier = Modifier.size(180.dp)
                ) {

                    drawLine(
                        color = Color(0xFFF3E6D2),
                        start = Offset(0.5f, size.height * 0.40f),
                        end = Offset(size.width * 0.35f, size.height * 0.35f),
                        strokeWidth = 14f,
                        cap = StrokeCap.Round
                    )

                    drawLine(
                        color = Color(0xFFF3E6D2),
                        start = Offset(10f, size.height * 0.55f),
                        end = Offset(size.width * 0.35f, size.height * 0.50f),
                        strokeWidth = 14f,
                        cap = StrokeCap.Round
                    )

                    drawLine(
                        color = Color(0xFFF3E6D2),
                        start = Offset(10f, size.height * 0.70f),
                        end = Offset(size.width * 0.35f, size.height * 0.65f),
                        strokeWidth = 14f,
                        cap = StrokeCap.Round
                    )
                }

                // Basket image
                Image(
                    painter = painterResource(id = R.drawable.basket2),
                    contentDescription = "Basket",
                    modifier = Modifier.size(180.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "BasketBuddy",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF3E6D2)
            )

            // Curved underline
            Canvas(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .width(200.dp)
                    .height(40.dp)
            ) {

                drawArc(
                    color = Color(0xFFF3E6D2),
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    style = Stroke(
                        width = 10f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            CircularProgressIndicator(
                color = Color(0xFFF3E6D2),
                strokeWidth = 4.dp
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
        BasketBuddyTheme {
            SplashScreen(onNavigate = { })
            //TopBar("Hello", onBackClick = { })
        }
}
