package br.com.mindbox.screens.email_detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.database.repository.EmailTaskRepository
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.util.date.DateUtils
import coil.compose.AsyncImage

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailScreen(
    navController: NavHostController,
    emailId: Long,
    userData: UserData?,
    rawNavBottomItems: List<NavBottomItem>
) {
    data class GomaAction(
        val displayText: String, val action: () -> Unit
    )

    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )

    val emailRepository = EmailRepository(context)
    val emailTaskRepository = EmailTaskRepository(context)
    val emailWithTasks = emailRepository.findEmailByIdWithSender(emailId)

    val (replyValue, setReplyValue) = remember { mutableStateOf("") }

    val gomaActions = listOf<GomaAction>(GomaAction(displayText = "Traduzir", action = {
        Toast.makeText(context, "Em breve disponível!", Toast.LENGTH_SHORT).show()
    }), GomaAction(displayText = "Sugestão de resposta", action = {
        Toast.makeText(context, "Em breve disponível!", Toast.LENGTH_SHORT).show()
    }), GomaAction(displayText = "Resumir", action = {
        Toast.makeText(context, "Em breve disponível!", Toast.LENGTH_SHORT).show()
    }), GomaAction(displayText = "Adicionar a minhas tarefas", action = {
        Toast.makeText(context, "Em breve disponível!", Toast.LENGTH_SHORT).show()
    }), GomaAction(displayText = "Criar tarefa", action = {
        Toast.makeText(context, "Em breve disponível!", Toast.LENGTH_SHORT).show()
    }))



    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.layer_mid)),
        bottomBar = {
            Surface(
                color = colorResource(id = R.color.layer_mid), modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            color = colorResource(id = R.color.white),
                            text = "Quer uma ajudinha?",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.chat_unselected_icon),
                            contentDescription = "chat",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(gomaActions) { gomaAction ->
                            OutlinedButton(onClick = { gomaAction.action() }) {
                                Text(
                                    gomaAction.displayText,
                                    color = colorResource(id = R.color.white)
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(value = replyValue,
                            onValueChange = setReplyValue,
                            enabled = true,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp)),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = colorResource(id = R.color.white),
                            ),
                            textStyle = TextStyle(color = colorResource(id = R.color.black)),
                            placeholder = { Text(text = "Responda de forma objetiva!") })
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
                                Toast.makeText(
                                    context,
                                    "E-mail enviado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            }, modifier = Modifier.size(48.dp), content = {
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
        },
        topBar = {
            TopAppBar(title = {}, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colorResource(
                    id = R.color.layer_mid
                )
            ), navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.pngegg),
                        modifier = Modifier.size(16.dp),
                        contentDescription = "Voltar"
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
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {


                    // Subject
                    Text(
                        text = emailWithTasks!!.email.subject,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    // Sender Info
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Avatar(user = emailWithTasks!!.sender, size = 40.dp, withText = false)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = emailWithTasks!!.sender.email,
                                style = MaterialTheme.typography.bodyMedium,
                                color = colorResource(id = R.color.white)
                            )
                            Text(
                                text = DateUtils.getBrazilianDateTimeFormat()
                                    .format(emailWithTasks.email.sendDate),
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.white)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = emailWithTasks!!.email.text,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.white),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Checklist
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = colorResource(id = R.color.white), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    if (emailWithTasks!!.tasks.isNotEmpty()) {
                        Text(
                            text = "Tarefas criadas",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.white),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        emailWithTasks!!.tasks.forEach { task ->
                            var isChecked by remember { mutableStateOf(task.isCompleted) }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Checkbox(checked = isChecked, onCheckedChange = {
                                    isChecked = !isChecked
                                    emailTaskRepository.changeCompletedStatus(
                                        task.id, isChecked
                                    )
                                    Toast.makeText(
                                        context, "Status alterado com sucesso!", Toast.LENGTH_SHORT
                                    ).show()
                                })
                                Text(
                                    text = task.description,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontSize = 18.sp,
                                    color = colorResource(id = R.color.white)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





