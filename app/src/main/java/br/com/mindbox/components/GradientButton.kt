package br.com.mindbox.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.mindbox.R

@Composable
fun GradientButton(
    text: String
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            colorResource(id = R.color.purple_mid),
            colorResource(id = R.color.layer)
        )
    )

    Button(onClick = { /*TODO*/ }, modifier = Modifier.background(gradient).fillMaxWidth(), shape = RoundedCornerShape(0.dp), colors = ButtonDefaults.buttonColors(
        disabledContentColor = Color.Transparent,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    )) {
        Text(text = text, color = colorResource(id = R.color.white))
    }
}