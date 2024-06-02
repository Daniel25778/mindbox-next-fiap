package br.com.mindbox.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.DrawerItem
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.database.repository.UserRepository
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.service.ChatBot

data class ChatMessage(val text: String, val isUserMessage: Boolean)

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    chatBot: ChatBot
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

    var responseText by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }
    var expectedUserResponse by remember { mutableStateOf("") }
    var chatMessages by remember { mutableStateOf(listOf<ChatMessage>()) }

    LaunchedEffect(Unit) {
        val initialResponse = chatBot.processMessage("")
        responseText = initialResponse.message
        expectedUserResponse = initialResponse.expectedUserResponse
        messageText = initialResponse.expectedUserResponse
        chatMessages = chatMessages + ChatMessage(responseText, isUserMessage = false)
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
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onBackground)
                            ) {
                                // Placeholder for image
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Goma",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    },
                    navigationIcon = {}
                )
            },
            bottomBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(colorResource(id = R.color.layer_mid))
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = messageText,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.weight(1f),
                        placeholder = { Text(text = "Digite uma mensagem") }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            chatMessages = chatMessages + ChatMessage(messageText, isUserMessage = true)
                            val chatBotResponse = chatBot.processMessage(messageText)
                            responseText = chatBotResponse.message
                            expectedUserResponse = chatBotResponse.expectedUserResponse
                            messageText = chatBotResponse.expectedUserResponse
                            chatMessages = chatMessages + ChatMessage(responseText, isUserMessage = false)
                        }
                    ) {
                        Text(text = "Enviar")
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
                                UserMessage(chatMessage.text)
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
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground)
        ) {
            // Placeholder for image
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun UserMessage(message: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF6200EE), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground)
        ) {
            // Placeholder for image
        }
    }
}
