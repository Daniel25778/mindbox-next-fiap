package br.com.mindbox.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.DrawerItem
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService
import coil.compose.AsyncImage
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    rawNavBottomItems: List<NavBottomItem>,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val startAnimation by remember { mutableStateOf(false) }
    val dates = listOf("12 Seg", "12 Seg", "12 Seg", "12 Seg", "12 Seg", "12 Seg")
    val events = listOf(
        Event("Atualização de protótipo", "Breve descrição", "9:30 - 10:30", R.drawable.contact_unselected_icon),
        Event("Atualização de protótipo", "Breve descrição", "9:30 - 10:30", R.drawable.contact_selected_icon)
    )
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(1)
    }
    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

    ModalNavigationDrawer(modifier = Modifier.background(colorResource(id = R.color.layer_mid)),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.background(colorResource(id = R.color.layer_mid))
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
                            modifier = Modifier.fillMaxSize()
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
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.layer_mid)),
            bottomBar = {
                NavigationBar(
                    containerColor = colorResource(id = R.color.layer_mid),
                    modifier = Modifier.background(colorResource(id = R.color.layer_mid))
                ) {
                    navBottomItems.forEachIndexed { index, item ->
                        NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                            index.also { selectedItemIndex = it }
                            navController.navigate(item.url)
                        }, label = {
                            Text(text = item.title)
                        }, alwaysShowLabel = false, icon = {
                            BadgedBox(badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }) {
                                Image(
                                    painter = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                            ?: painterResource(id = R.drawable.chat_unselected_icon)
                                    } else {
                                        item.unselectedIcon
                                            ?: painterResource(id = R.drawable.chat_unselected_icon)
                                    },
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        })
                    }
                }
            },
            topBar = {
                TopAppBar(title = {}, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(
                        id = R.color.layer_mid
                    )
                ), navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.width(500.dp)
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
                }, actions = {
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
                        } else Avatar(user = user, size = 40.dp, withText = false)
                    }
                }

                )
            }) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        Text(
                            text = "Calendário",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        LazyRow(
                            modifier = Modifier.padding(vertical = 16.dp)
                        ) {
                            items(dates.size) { index ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                ) {
                                    Text(text = dates[index], color = Color.White, fontSize = 14.sp)
                                }
                            }
                        }

                        Text(
                            text = "Segunda 12 de Fevereiro - Hoje",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        LazyColumn {
                            items(events.size) { index ->
                                EventItem(event = events[index])
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }


                    }

                }
            }
        }
    }
}


data class Event(val title: String, val description: String, val time: String, val avatarRes: Int)

@Composable
fun EventItem(event: Event) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(80.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = event.title, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = event.description, fontSize = 12.sp, color = Color.White)
            Row {
                AvatarImage(avatarRes = event.avatarRes)
                AvatarImage(avatarRes = event.avatarRes)
            }
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = event.time, fontSize = 12.sp, color = Color.White)
        }
    }
}

@Composable
fun AvatarImage(avatarRes: Int) {
    Image(
        painter = painterResource(id = avatarRes),
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
    )
}





