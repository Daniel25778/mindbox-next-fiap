package br.com.mindbox.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mindbox.R

@Composable
fun DrawerItem(text: String, navController: NavController, destination: String) {
    Row(
        modifier = Modifier.clickable {
            navController.navigate(destination)
        },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.flat_color_icons_google),
            contentDescription = "teste"
        )
        Text(
            text = text,
            color = colorResource(id = R.color.white),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

}