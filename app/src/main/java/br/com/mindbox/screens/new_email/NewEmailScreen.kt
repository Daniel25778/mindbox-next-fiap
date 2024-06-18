package br.com.mindbox.screens.new_email


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Checkbox
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import br.com.mindbox.components.GradientButton
import br.com.mindbox.components.Input
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.util.date.DateUtils
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEmailScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    rawNavBottomItems: List<NavBottomItem>,
    emailTo: String?,
) {
    data class Suggestion(
        val displayText: String, val content: String
    )

    data class Task(
        val title: String, val isCompleted: Boolean
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(3)
    }

    var recipient by remember { mutableStateOf(emailTo ?: "") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var scheduledDateTime by rememberSaveable {
        mutableStateOf<Date?>(null)
    }
    val suggestions = listOf(
        Suggestion(
            "Saudação",
            "Olá, espero que você esteja bem. Como estão as coisas por aí? Espero que esteja aproveitando seu dia."
        ), Suggestion(
            "Informativo",
            "Estou escrevendo para informá-lo sobre a nova política da empresa que entrará em vigor na próxima semana."
        ), Suggestion(
            "Reunião",
            "Gostaria de agendar uma reunião para discutir os resultados do último trimestre e definir metas para o próximo."
        ), Suggestion(
            "Agradecimento",
            "Gostaria de agradecer pelo seu tempo e atenção na última reunião. Suas contribuições foram muito valiosas."
        ), Suggestion(
            "Urgente",
            "Este é um assunto urgente e precisa de sua atenção imediata. Por favor, revise o documento anexo o mais rápido possível."
        ), Suggestion(
            "Sugestão",
            "Gostaria de sugerir uma nova abordagem para nosso projeto. Acredito que podemos melhorar os resultados implementando essa ideia."
        ), Suggestion(
            "Confirmação",
            "Gostaria de confirmar nossa reunião agendada para amanhã às 10h. Está confirmado o seu comparecimento?"
        ), Suggestion(
            "Solicitação",
            "Estou escrevendo para solicitar sua ajuda com o processo de integração do novo funcionário. Podemos agendar uma conversa para alinhar os próximos passos?"
        ), Suggestion(
            "Feedback",
            "Gostaria de compartilhar algumas observações sobre o último projeto. Estou impressionado com o trabalho realizado, mas acredito que podemos melhorar em alguns aspectos."
        ), Suggestion(
            "Convite",
            "Estou organizando um evento para a equipe no próximo mês e gostaria de convidá-lo para participar. Seria ótimo contar com sua presença."
        ), Suggestion(
            "Anúncio",
            "Tenho o prazer de informar que fomos selecionados para apresentar nosso projeto na conferência internacional. Parabéns a todos pelo excelente trabalho!"
        )
    )

    fun addTask(newTask: Task) {
        tasks = tasks + newTask
    }

    fun updateTaskTitle(index: Int, title: String) {
        if (index >= 0 && index < tasks.size) {
            tasks = tasks.toMutableList().apply {
                this[index] = this[index].copy(title = title)
            }
        }
    }

    fun toggleTaskCompletion(index: Int) {
        if (index >= 0 && index < tasks.size) {
            tasks = tasks.toMutableList().apply {
                this[index] = this[index].copy(isCompleted = !this[index].isCompleted)
            }
        }
    }

    fun removeTask(index: Int) {
        if (index >= 0 && index < tasks.size) {
            tasks = tasks.toMutableList().apply {
                removeAt(index)
            }
        }
    }

    fun showTimePicker(context: android.content.Context) {
        val calendar = Calendar.getInstance()
        calendar.time = scheduledDateTime!!
        val initialHour = calendar.get(Calendar.HOUR_OF_DAY)
        val initialMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context, { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                scheduledDateTime = calendar.time
            }, initialHour, initialMinute, true
        )
        timePickerDialog.show()
    }

    fun showDatePicker(context: android.content.Context) {
        val calendar = Calendar.getInstance()
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context, { _, year, month, day ->
                calendar.set(year, month, day)
                scheduledDateTime = calendar.time
                showTimePicker(context)
            }, initialYear, initialMonth, initialDay
        )
        datePickerDialog.show()
    }

    val onSendEmailResult: () -> Unit = {
        navController.navigate("dashboard")
        Toast.makeText(context, "E-mail enviado com sucesso", Toast.LENGTH_SHORT).show()
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
                    Text(text = "E-mails")
                    Spacer(modifier = Modifier.height(10.dp))
                    DrawerItem(
                        "Todos da caixa de entrada",
                        { navController.navigate("dashboard") },
                        R.drawable.all_emails,
                        emailListType = "inbox"
                    )
                    DrawerItem(
                        "Enviados",
                        { navController.navigate("dashboard") },
                        R.drawable.all_emails,
                        emailListType = "sent"
                    )
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Menu")
                    Spacer(modifier = Modifier.height(10.dp))
                    DrawerItem(
                        "Início",
                        { navController.navigate("dashboard") },
                        R.drawable.home_selected_icon,
                        emailListType = "inbox"
                    )
                    DrawerItem(
                        "Calendário",
                        { navController.navigate("calendar") },
                        R.drawable.calendar_selected_icon,
                        emailListType = "inbox"
                    )
                    DrawerItem(
                        "Novo",
                        { navController.navigate("new-email") },
                        R.drawable.add_selected_icon,
                        emailListType = "inbox"
                    )
                    DrawerItem(
                        "Categoria",
                        { Toast.makeText(context, "Em breve gerenciamento de categorias estará disponível!", Toast.LENGTH_SHORT).show() },
                        R.drawable.category_selected,
                        emailListType = "inbox"
                    )
                    Spacer(modifier = Modifier.fillMaxSize())
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
                            if (item.url !== "category") {
                                navController.navigate(item.url)
                                index.also { selectedItemIndex = it }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Em breve gerenciamento de categorias estará disponível!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
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
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        Input(
                            value = recipient,
                            onValueChange = { recipient = it },
                            placeholder = "Digite o destinatário",
                            label = "Destinatário",
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Input(
                            value = subject,
                            onValueChange = { subject = it },
                            placeholder = "Digite o assunto",
                            label = "Assunto",
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Input(
                            value = body,
                            onValueChange = { body = it },
                            placeholder = "Digite a descrição",
                            label = "Descrição",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = scheduledDateTime?.let {
                                DateUtils.getBrazilianDateTimeFormat().format(it)
                            } ?: "",
                            enabled = false,
                            onValueChange = {},
                            label = { Text("Agendar envio") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showDatePicker(context)
                                },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledPlaceholderColor = colorResource(id = R.color.white),
                                disabledTextColor = colorResource(id = R.color.white),
                                disabledLabelColor = colorResource(id = R.color.white),
                                disabledBorderColor = colorResource(R.color.grey_550)
                            ),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Quer uma ajudinha?",
                                color = colorResource(id = R.color.white),
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
                        ) {
                            items(suggestions) { suggestion ->
                                OutlinedButton(onClick = { body = suggestion.content }) {
                                    Text(
                                        suggestion.displayText,
                                        color = colorResource(id = R.color.white)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(color = colorResource(id = R.color.white), thickness = 1.dp)
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Associe tarefas a este e-mail",
                            color = colorResource(id = R.color.white),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        tasks.forEachIndexed { index, task ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Checkbox(
                                    checked = task.isCompleted, onCheckedChange = { isChecked ->
                                        toggleTaskCompletion(index)
                                    }, modifier = Modifier.padding(end = 8.dp, top = 20.dp)
                                )
                                Input(
                                    value = task.title,
                                    onValueChange = { newTitle ->
                                        updateTaskTitle(index, newTitle)
                                    },
                                    placeholder = "Tarefa ${index + 1}",
                                    label = "Tarefa ${index + 1}",
                                    modifier = Modifier.weight(1f) // Takes remaining space in the Row
                                )
                                IconButton(
                                    onClick = {
                                        removeTask(index)
                                    }, modifier = Modifier.padding(start = 8.dp, top = 20.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Delete, contentDescription = "Delete Task"
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        OutlinedButton(onClick = {
                            val newTask = Task("", isCompleted = false)
                            addTask(newTask)
                        }) {
                            Text(
                                color = colorResource(id = R.color.white), text = "Adicionar tarefa"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        GradientButton(
                            onClick = { onSendEmailResult() }, text = "Enviar"
                        )

                    }

                }
            }
        }
    }
}