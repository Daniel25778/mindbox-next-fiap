package br.com.mindbox.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.DrawerItem
import br.com.mindbox.components.EmailListItem
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.database.repository.UserRepository
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService
import coil.compose.AsyncImage
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val userRepository = UserRepository(context)
    val emailRepository = EmailRepository(context)
    val listRecipients: List<Long> = listOf<Long>(user.id)
    val usersWithRecentEmails = remember { userRepository.findUsersWithRecentEmailsSent(user.id) }
    val findEmailsSentToUsers = remember { emailRepository.findEmailsSentToUsers(listRecipients) }
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = LinearEasing
        ), label = ""
    )

    data class BottomNavigationItem(
        val title: String,
        val selectedIcon: ImageVector ,
        val unselectedIcon: ImageVector,
        val url: String,
        val hasNews: Boolean,
        val badgeCount: Int? = null
    )

    val items = listOf(
        BottomNavigationItem(
            title = "Calendário",
            url = "dashboard",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Novo",
            url = "alert",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Chat",
            url = "chatOnboarding",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    ModalNavigationDrawer(
        modifier = Modifier.background(colorResource(id = R.color.layer_mid)),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .background(colorResource(id = R.color.layer_mid))
            ) {
                Column(
                    modifier = Modifier
                        .background(colorResource(id = R.color.layer_mid))
                        .padding(start = 15.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(100.dp)
                            .background(colorResource(id = R.color.layer_mid))
                    )
                    Box(
                        contentAlignment = Alignment.TopStart,
                        modifier = Modifier
                            .height(40.dp)
                            .width(150.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo_horizontal),
                            contentDescription = "App Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    DrawerItem("Alerta", navController, "alert")
                }
            }
        }

    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.layer_mid)),
            bottomBar = {
                NavigationBar(
                    containerColor = colorResource(id = R.color.layer_mid),
                    modifier = Modifier.background(colorResource(id = R.color.layer_mid))
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.url)
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            },
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .width(500.dp)
                                .background(colorResource(id = R.color.layer_mid))
                        ) {
                            IconButton(onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.app_logo_horizontal),
                                contentDescription = "App Logo",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(30.dp)
                                    .padding(start = 8.dp)
                            )
                        }
                    },
                    actions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            if (userData?.profilePictureUrl != null) {
                                AsyncImage(
                                    model = userData.profilePictureUrl,
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            } else
                                Avatar(data = user, size = 40.dp, withText = false)
                        }
                    }

                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        Text(
                            text = "Recentes",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(usersWithRecentEmails) { item ->
                                Avatar(data = item, withText = true, size = 60.dp)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Todos da caixa de entrada",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(findEmailsSentToUsers) {
                                EmailListItem(data = user)
                            }
                        }
                    }

                }
            }
        }
    }
}





