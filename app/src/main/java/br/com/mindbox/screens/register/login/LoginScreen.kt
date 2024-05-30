package br.com.mindbox.screens.register.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.GradientButton
import br.com.mindbox.components.Input
import br.com.mindbox.dto.auth.LoginDTO
import br.com.mindbox.model.user.User
import br.com.mindbox.presentation.sign_in.SignInState
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.util.data.listData

@SuppressLint("SuspiciousIndentation")
@Composable
fun Login(
    navController: NavController, state: SignInState,
    onSignInClick: () -> Unit
) {
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = LinearEasing
        ), label = ""
    )
    Box {
        var email by remember {
            mutableStateOf("gilberto@locaweb.com.br")
        }
        var password by remember {
            mutableStateOf("Senha@123")
        }

        val context = LocalContext.current
        val authorizationService = AuthorizationService(context = context)

        val onAuthenticationResult = { user: User? ->
            if (user != null) {
                Toast.makeText(context, "Autenticação bem sucedida", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard")
            } else {
                Toast.makeText(context, "Falha na autenticação. Email ou senha incorretos.", Toast.LENGTH_SHORT).show()
            }
        }

        LaunchedEffect(key1 = state.signInError) {
            state.signInError?.let { error ->
                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 22.dp, end = 22.dp),
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo_horizontal),
                        contentDescription = "App Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Bem-vindo ao seu novo e-mail Inteligente",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Faça Login e Experimente a Potência da IA e do Suporte de Chat em Tempo Real",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.grey_250)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Input(
                    value = email,
                    placeholder = "Email",
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    onValueChange = { email = it }
                )

                Input(
                    isPassword = true,
                    value = password,
                    placeholder = "Senha",
                    label = "Senha",
                    keyboardType = KeyboardType.Password,
                    onValueChange = { password = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Esqueceu a senha?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(16.dp))
                GradientButton(text = "Entrar", onClick = {
                    val loginDTO = LoginDTO(email, password)
                    val user = authorizationService.authenticateUser(loginDTO)
                    onAuthenticationResult(user)
                })
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "ou",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, colorResource(id = R.color.grey_550))
                            .clickable { onSignInClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp).padding(10.dp),
                            painter = painterResource(id = R.drawable.flat_color_icons_google),
                            contentDescription = "Google",
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, colorResource(id = R.color.grey_550))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp).padding(10.dp),
                            painter = painterResource(id = R.drawable.flat_color_icons_google),
                            contentDescription = "Outlook",
                            tint = Color.Unspecified
                        )
                    }


                }
                Spacer(modifier = Modifier.width(16.dp))
                Row {
                    Text(
                        text = "Não tem uma conta ainda? Registar",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

    }
}


