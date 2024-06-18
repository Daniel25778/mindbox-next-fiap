package br.com.mindbox.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.mindbox.R

@Composable
fun GomaFloatingButton(onClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = colorResource(id = R.color.layer_mid),
        contentColor = colorResource(id = R.color.white)
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            contentDescription = "GOMA",
            painter = painterResource(id = R.drawable.chat_selected_icon)
        )
    }
}