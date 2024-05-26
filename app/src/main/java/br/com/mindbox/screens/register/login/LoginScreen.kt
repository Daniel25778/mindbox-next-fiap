package br.com.mindbox.screens.register.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiapstatupone.mindbox.R
import br.com.fiapstatupone.mindbox.components.Input
import br.com.fiapstatupone.mindbox.presentation.sign_in.SignInState
import br.com.fiapstatupone.mindbox.ui.theme.Montserrat
import br.com.fiapstatupone.mindbox.ui.theme.MontserratSemibold

@Composable
    fun Login(navController: NavController, state: SignInState,
              onSignInClick: () -> Unit) {
        Box {
            var email by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }

            val context = LocalContext.current
            LaunchedEffect(key1 = state.signInError) {
                state.signInError?.let { error ->
                    Toast.makeText(
                        context,
                        error,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF1E1E1E))

        ) {

            Image(
                ImageBitmap.imageResource(R.drawable.shield),
                contentDescription = "",
                modifier = Modifier
                    .aspectRatio(2.5f)
            )
            Text(
                "Sua ferramenta de segurança colaborativa!",
                color = Color(0xFF2b7deb),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))

                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Autentique através da sua conta do Google", fontFamily =  Montserrat,  color = MaterialTheme.colorScheme.onPrimary)
                        IconButton(onClick = onSignInClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.flat_color_icons_google),
                                contentDescription = "Google Icon",
                                modifier = Modifier.size(50.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }

                }

            Text(
                "Entre para proteger sua comunidade!",
                color = Color(0xFFFFFFFF),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))


            Text(
                fontSize = 15.sp, text = "Criar nova conta",
                fontFamily = MontserratSemibold, textAlign = TextAlign.Left,
                color = Color(0xFF2b7deb)
            )




            Spacer(modifier = Modifier.height(50.dp))

            Input(
                value = email,
                placeholder = "Digite o seu e-mail",
                label = "E-mail",
                modifier = Modifier,
                keyboardType = KeyboardType.Email
            ) { email = it }

            Spacer(modifier = Modifier.height(40.dp))
            Input(
                value = password,
                placeholder = "Digite sua senha",
                label = "Senha",
                modifier = Modifier,
                keyboardType = KeyboardType.Password
            ) { password = it }

            Spacer(modifier = Modifier.height(80.dp))

                Button(

                    onClick = { navController.navigate("dashboard") },
                    modifier = Modifier

                        .background(Color(0xFFEFB8C8))
                        .fillMaxWidth() // Preenche a largura máxima disponível
                        .height(50.dp), // Define uma altura fixa para o botão
                ) {
                    Text(text = "LOGAR", color = Color(0xFF2b7deb))

                }


            }
        }
    }

