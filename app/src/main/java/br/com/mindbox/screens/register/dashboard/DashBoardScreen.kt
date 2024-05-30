package br.com.mindbox.screens.register.dashboard

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mindbox.R
import br.com.mindbox.model.Advice
import br.com.mindbox.model.Slip
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.screens.register.alert.Alert
import br.com.mindbox.service.RetrofitFactory
import br.com.mindbox.ui.theme.Montserrat
import br.com.mindbox.ui.theme.MontserratSemibold
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    navController: NavController,
    MenuText: String, userData: UserData?,
    onSignOut: () -> Unit
) {
    var adviceDescriptionState by remember {
        mutableStateOf(Advice(slip = Slip(1, "")).slip.adviceDescription)
    }
    var isApiCallMade by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Navigation Drawer",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                DrawerItem("Alert", navController, "alert")
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("Dashboard") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Menu",
                                tint = colorResource(
                                    id = R.color.white
                                )
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                item {
                    fun getAdvice() {
                        val call = RetrofitFactory().getAdviceService().getAdvice()
                        call.enqueue(object : Callback<Advice> {
                            override fun onResponse(
                                call: Call<Advice>,
                                response: Response<Advice>
                            ) {
                                adviceDescriptionState = response.body()?.slip?.adviceDescription!!
                            }

                            override fun onFailure(call: Call<Advice>, t: Throwable) {
                                Log.i("FIAP", "onResponse: ${t.message}")
                            }

                        })
                    }

                    LaunchedEffect(Unit) {
                        if (!isApiCallMade) {
                            getAdvice()
                            isApiCallMade = true
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (userData?.profilePictureUrl != null) {
                            AsyncImage(
                                model = userData.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        if (userData?.username != null) {
                            Text(
                                text = "Olá ${userData.username}",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Button(onClick = onSignOut) {
                            Text(text = "Sair")
                        }
                    }

                    Column(Modifier.padding(20.dp)) {
                        Text(
                            text = "Dica personalizada", fontFamily = MontserratSemibold,
                            color = Color(0xFF2B7DEB)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = adviceDescriptionState,
                            fontFamily = Montserrat,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Informe alguém sobre sua rota ao explorar novos caminhos.",
                            fontFamily = Montserrat,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "A rua José Xavier que você frequentou pode apresentar riscos para a sua segurança.",
                            fontFamily = Montserrat, color = MaterialTheme.colorScheme.onPrimary
                        )
                        Row(
                            Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                Modifier.padding(top = 12.dp, bottom = 42.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.app_logo),
                                    contentDescription = "App logo",
                                    modifier = Modifier.size(100.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Spacer(modifier = Modifier.height(45.dp))
                                Text(
                                    fontSize = 24.sp, text = "Seja bem-vindo!",
                                    fontFamily = MontserratSemibold,
                                    color = Color(0xFF2B7DEB)
                                )
                            }
                        }

                        Text(
                            text = "Por onde deseja começar?",
                            fontFamily = Montserrat, color = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(modifier = Modifier.height(48.dp))

                        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF272727)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp, top = 52.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.mapa_image),
                                        contentDescription = "App logo",
                                        modifier = Modifier.size(100.dp),
                                    )
                                    Spacer(modifier = Modifier.height(52.dp))
                                    Text(
                                        text = "Visualizar mapa",
                                        Modifier.width(100.dp),
                                        textAlign = TextAlign.Center,
                                        fontFamily = MontserratSemibold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            Card(
                                onClick = { navController.navigate("alert") },
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF272727)),
                                modifier = Modifier
                                    .fillMaxWidth(0.5f) // Define que a largura do Card é metade da largura do Row
                                    .padding(8.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp, top = 52.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.alert_image),
                                        contentDescription = "App logo",
                                        modifier = Modifier.size(100.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                    Spacer(modifier = Modifier.height(52.dp))
                                    Text(
                                        text = "Alertas próximos",
                                        Modifier.width(100.dp),
                                        textAlign = TextAlign.Center,
                                        fontFamily = MontserratSemibold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DrawerItem(text: String, navController: NavController, destination: String) {
    Text(
        text = text,
        color = colorResource(id = R.color.white),
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(destination)
            }
            .padding(16.dp)
    )
}
