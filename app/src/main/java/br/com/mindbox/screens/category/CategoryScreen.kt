package br.com.mindbox.screens.category



import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    rawNavBottomItems: List<NavBottomItem>,
) {
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )
    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

    NavigationLayout(navController = navController, selectedItemIndex =  3, rawNavBottomItems = navBottomItems) {
        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = "Categorias",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )

            }

        }
    }

}





