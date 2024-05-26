package br.com.mindbox.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Large Top App Bar",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary // Define a cor do texto para corresponder ao esquema de cores
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* ação de retorno */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onPrimary // Define a cor do ícone para corresponder ao esquema de cores
                )
            }
        },
        actions = {
            IconButton(onClick = { /* ação do menu */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.onPrimary // Define a cor do ícone para corresponder ao esquema de cores
                )
            }
        },

    )

}
