package br.com.mindbox.screens.register.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.GradientButton
import br.com.mindbox.components.Input
import br.com.mindbox.presentation.sign_in.SignInState

@SuppressLint("SuspiciousIndentation")
@Composable
    fun Login(navController: NavController, state: SignInState,
              onSignInClick: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = LinearEasing
        )
    )
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

                AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, start = 22.dp, end = 22.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo_horizontal),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(150.dp).height(1.dp)
                        )
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
                        Spacer(modifier = Modifier.height(24.dp))
                        Input(
                            value = email,
                            placeholder = "Email",
                            label = "Email",
                            keyboardType = KeyboardType.Email,
                            onValueChange = { email = it }
                        )
                        Input(
                            value = password,
                            placeholder = "Senha",
                            label = "Senha",
                            keyboardType = KeyboardType.Password,
                            onValueChange = { password = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        GradientButton(text = "Entrar")
                    }
                }
            }
    }

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val state = SignInState(signInError = null) // Provide a sample state

        Login(
            navController = navController,
            state = state,
            onSignInClick = { /* Sample onSignInClick handler */ }
        )
}

