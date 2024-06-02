package br.com.mindbox


import GetStartedScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import br.com.mindbox.dto.auth.LoginDTO
import br.com.mindbox.presentation.sign_in.GoogleAuthUiClient
import br.com.mindbox.presentation.sign_in.SignInViewModel
import br.com.mindbox.screens.ChatScreen
import br.com.mindbox.screens.alert.Alert
import br.com.mindbox.screens.dashboard.DashBoard
import br.com.mindbox.screens.login.Login
import br.com.mindbox.screens.register.alert.AnimatedSplashScreen
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.service.ChatBot
import br.com.mindbox.service.EmailService
import br.com.mindbox.ui.theme.Theme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val emailService: EmailService by lazy { EmailService(applicationContext) }
    private val authorizationService: AuthorizationService by lazy { AuthorizationService(applicationContext) }
    private val chatBot: ChatBot by lazy { ChatBot(emailService, authorizationService) }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()

                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "splash",
                        exitTransition = {
                            slideOutOfContainer(
                                towards =
                                AnimatedContentTransitionScope.SlideDirection.End,
                                animationSpec = tween(1000)
                            )
                        }
                    ) {
                        composable(route = "login") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if(googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("dashboard")
                                }
                            }
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if(result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )
                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if(state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Autenticado com sucesso",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    AuthorizationService(applicationContext).authenticateUser(LoginDTO("gilberto@locaweb.com.br", "Senha@123"))
                                    navController.navigate("dashboard")
                                    viewModel.resetState()
                                }
                            }

                            Login(navController, state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                })
                        }
                        composable(route = "splash") { AnimatedSplashScreen(navController) }

                        composable(route = "presentation") { GetStartedScreen(navController) }

                        composable(route = "dashboard") {
                            DashBoard(
                                navController,
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Saiu da conta",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }
                        composable(route = "alert") { Alert(navController, MenuText="Alertas Próximos") }
                        composable(route = "chat") { ChatScreen(navController, chatBot) }
                    }
                }
            }
        }
    }
}
