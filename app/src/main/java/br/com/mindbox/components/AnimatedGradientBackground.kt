package br.com.mindbox.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import br.com.mindbox.R

@Composable
fun AnimatedGradientBackground(alphaAnimate: Float, content: @Composable () -> Unit) {
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
            colorResource(id = R.color.layer_mid),
            colorResource(id = R.color.black),
            colorResource(id = R.color.layer_mid)
        ),
        start = Offset(0.0f, 0.0f),
        end = Offset(animatedOffsetX, 1900.0f)
    )

    Box(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize(),
    ) {
        content()
    }
}