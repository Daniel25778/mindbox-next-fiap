package br.com.mindbox.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.mindbox.R
import br.com.mindbox.model.user.User
import coil.compose.AsyncImage

@Composable
fun Avatar(
    user: User, size: Dp, withText: Boolean, onClick: () -> Unit = {}
) {
    if (withText) Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = user.profilePictureUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable {
                    onClick()
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = user.fullName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp),
            color = colorResource(id = R.color.white)
        )
    }
    else AsyncImage(
        model = user.profilePictureUrl,
        contentDescription = "Profile picture",
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable {
                if (onClick !== null) {
                    onClick()
                }
            },
        contentScale = ContentScale.Crop
    )
}