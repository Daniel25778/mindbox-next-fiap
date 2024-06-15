package br.com.mindbox.screens.chat

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.DrawerItem
import br.com.mindbox.database.repository.CalendarEventParticipantRepository
import br.com.mindbox.database.repository.CalendarEventRepository
import br.com.mindbox.model.user.User
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.service.ChatBotService
import br.com.mindbox.service.EmailService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class ChatMessage(val text: String, val isUserMessage: Boolean)

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val emailService = EmailService(context)
    val authorizationService = AuthorizationService(context)
    val loggedUser = authorizationService.getLoggedUsers()[0]
    val calendarEventRepository = CalendarEventRepository(context)
    val calendarEventParticipantRepository = CalendarEventParticipantRepository(context)
    val chatBotService = ChatBotService(emailService, loggedUser, calendarEventRepository, calendarEventParticipantRepository)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )

    var responseText by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }
    var expectedUserResponse by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf<ChatMessage>()) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var isSendingMessage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val initialResponse = chatBotService.processMessage("")
        responseText = initialResponse.message
        expectedUserResponse = initialResponse.expectedUserResponse
        messageText = initialResponse.expectedUserResponse
        chatMessages = chatMessages + ChatMessage(responseText, isUserMessage = false)
    }

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
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(
                        id = R.color.layer_mid
                    )
                ), title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(colorResource(id = R.color.layer)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chat_unselected_icon),
                                contentDescription = "IA Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Goma", style = MaterialTheme.typography.titleMedium
                        )
                    }
                }, navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("dashboard") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pngegg),
                            modifier = Modifier.size(16.dp),
                            contentDescription = "Voltar"
                        )
                    }
                })
            },
            bottomBar = {
                Surface(
                    color = colorResource(id = R.color.layer_mid),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        TextField(value = messageText,
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = colorResource(id = R.color.white),
                            ),
                            textStyle = TextStyle(color = colorResource(id = R.color.black), ),
                            placeholder = { Text(text = "Digite uma mensagem") })
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = colorResource(id = R.color.layer), shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            IconButton(onClick = {
                                if (isSendingMessage) return@IconButton
                                isSendingMessage = true
                                chatMessages = chatMessages + ChatMessage(
                                    messageText,
                                    isUserMessage = true
                                )
                                val chatBotResponse = chatBotService.processMessage(messageText)
                                coroutineScope.launch {
                                    delay(1000)
                                    responseText = chatBotResponse.message
                                    expectedUserResponse = chatBotResponse.expectedUserResponse
                                    messageText = chatBotResponse.expectedUserResponse
                                    chatMessages = chatMessages + ChatMessage(
                                        responseText,
                                        isUserMessage = false
                                    )
                                    isSendingMessage = false
                                }
                            },
                                enabled = expectedUserResponse.isNotEmpty(),
                                modifier = Modifier.size(48.dp),
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.send_chat_message_icon),
                                        contentDescription = "Enviar mensagem",
                                        modifier = Modifier.size(24.dp),
                                        tint = colorResource(R.color.white)
                                    )
                                })
                        }
                    }
                }
            }


        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(chatMessages.size) { index ->
                            val chatMessage = chatMessages[index]
                            if (chatMessage.isUserMessage) {
                                UserMessage(chatMessage.text, loggedUser)
                            } else {
                                ChatBotMessage(chatMessage.text)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBotMessage(message: String) {
    Row(
        verticalAlignment = Alignment.Top, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.layer)), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.chat_unselected_icon),
                contentDescription = "IA Icon",
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.grey_150), RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp),
                color = colorResource(id = R.color.primary)
            )
        }
    }
}

@Composable
fun UserMessage(message: String, user: User) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    colorResource(id = R.color.layer), RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.grey_150),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.layer)), contentAlignment = Alignment.Center
        ) {
            Avatar(user = user, size = 40.dp, withText = false)
        }
    }
}
