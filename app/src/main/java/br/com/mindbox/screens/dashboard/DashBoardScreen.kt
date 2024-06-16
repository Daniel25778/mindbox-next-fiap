package br.com.mindbox.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.EmailListItem
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.database.repository.UserRepository
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.service.AuthorizationService


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navController: NavController,
    onSignOut: () -> Unit,
    rawNavBottomItems: List<NavBottomItem>,
) {
    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val userRepository = UserRepository(context)
    val emailRepository = EmailRepository(context)
    val listRecipients: List<Long> = listOf<Long>(user.id)
    val usersWithRecentEmails = remember { userRepository.findReceiversBySenderIdOrderByRecentEmails(user.id) }
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )
    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

        NavigationLayout(navController = navController, selectedItemIndex =  0, rawNavBottomItems = navBottomItems) {
            emailListType ->
            val emails = when (emailListType) {
                "inbox" -> remember { emailRepository.findEmailsByRecipientIds(listRecipients) }
                "sent" -> emptyList()
                else -> emptyList()
            }
            AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Text(
                        text = "Recentes",
                        color = colorResource(id = R.color.white),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(usersWithRecentEmails) {
                            Avatar(user = it, withText = true, size = 60.dp)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = when (emailListType) {
                            "inbox" -> "Todos da caixa de entrada"
                            "sent" -> "Enviados"
                            else -> "E-mails"
                        },
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        items(emails) {
                            EmailListItem(emailWithTasks = it, navController = navController)
                        }
                    }
                }

            }
        }
    }






