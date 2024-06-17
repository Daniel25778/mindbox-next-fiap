package br.com.mindbox.screens.category



import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.database.repository.UserRepository
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService


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
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val emailRepository = EmailRepository(context)
    val categoriesList: List<Long> = listOf(1L)
    val categories = listOf("Categoria 1", "Categoria 2", "Categoria 3")
    var selectedCategory by remember { mutableStateOf(categories[0]) }
    val emails = remember(selectedCategory) {
        emailRepository.findEmailsByCategoryAndRecipientIds(categoriesList, user.id)
    }
    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

    NavigationLayout(navController = navController, selectedItemIndex =  3, rawNavBottomItems = navBottomItems) {
        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column(modifier = Modifier.padding(start = 20.dp)) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        val isSelected = category == selectedCategory

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(10.dp)
                                .background(
                                    color = if (isSelected) colorResource(R.color.white) else Color.Transparent,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .clickable {
                                    selectedCategory = category
                                }) {
                            Text(
                                text = category,
                                Modifier.padding(10.dp),
                                color = if (isSelected) colorResource(R.color.layer_mid) else colorResource(R.color.white),
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    items(emails) { email ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(Color.Gray, shape = MaterialTheme.shapes.small)
                        ) {
                            Text(text = email.email.text, fontSize = 16.sp, color = Color.White)
                            Text(text = email.sender.fullName, fontSize = 12.sp, color = Color.LightGray)
                        }
                    }
                }


            }

        }
    }

}





