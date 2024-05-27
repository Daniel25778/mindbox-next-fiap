package br.com.mindbox.screens.register.alert

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(8000)
        navController.popBackStack()
        navController.navigate("login")
    }
    AnimatedGradientBackground(alpha = alphaAnim.value)
}

@Composable
fun AnimatedGradientBackground(alpha: Float) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedOffsetX by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 3000.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val gradient = Brush.linearGradient(
        colors = listOf(
            colorResource(id = R.color.layer),
            colorResource(id = R.color.primary),
            colorResource(id = R.color.layer)
        ),
        start = Offset(0.0f, 0.0f),
        end = Offset(animatedOffsetX, 1900.0f)
    )
    Box(modifier = Modifier.background(gradient).fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.app_logo) , modifier = Modifier.size(120.dp).alpha(alpha = alpha), contentDescription = "")
    }
}

