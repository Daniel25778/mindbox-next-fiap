package br.com.mindbox.screens.new_email


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.GradientButton
import br.com.mindbox.components.Input
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEmailScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    rawNavBottomItems: List<NavBottomItem>,
) {
    data class Suggestion(
        val displayText: String,
        val content: String
    )
    val context = LocalContext.current
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )
    var recipient by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    val suggestions = listOf(
        Suggestion(
            "Saudação",
            "Olá, espero que você esteja bem. Como estão as coisas por aí? Espero que esteja aproveitando seu dia."
        ),
        Suggestion(
            "Informativo",
            "Estou escrevendo para informá-lo sobre a nova política da empresa que entrará em vigor na próxima semana."
        ),
        Suggestion(
            "Reunião",
            "Gostaria de agendar uma reunião para discutir os resultados do último trimestre e definir metas para o próximo."
        ),
        Suggestion(
            "Agradecimento",
            "Gostaria de agradecer pelo seu tempo e atenção na última reunião. Suas contribuições foram muito valiosas."
        ),
        Suggestion(
            "Urgente",
            "Este é um assunto urgente e precisa de sua atenção imediata. Por favor, revise o documento anexo o mais rápido possível."
        ),
        Suggestion(
            "Sugestão",
            "Gostaria de sugerir uma nova abordagem para nosso projeto. Acredito que podemos melhorar os resultados implementando essa ideia."
        ),
        Suggestion(
            "Confirmação",
            "Gostaria de confirmar nossa reunião agendada para amanhã às 10h. Está confirmado o seu comparecimento?"
        ),
        Suggestion(
            "Solicitação",
            "Estou escrevendo para solicitar sua ajuda com o processo de integração do novo funcionário. Podemos agendar uma conversa para alinhar os próximos passos?"
        ),
        Suggestion(
            "Feedback",
            "Gostaria de compartilhar algumas observações sobre o último projeto. Estou impressionado com o trabalho realizado, mas acredito que podemos melhorar em alguns aspectos."
        ),
        Suggestion(
            "Convite",
            "Estou organizando um evento para a equipe no próximo mês e gostaria de convidá-lo para participar. Seria ótimo contar com sua presença."
        ),
        Suggestion(
            "Anúncio",
            "Tenho o prazer de informar que fomos selecionados para apresentar nosso projeto na conferência internacional. Parabéns a todos pelo excelente trabalho!"
        )
    )


    val onSendEmailResult: () -> Unit = {
        navController.navigate("dashboard")
        Toast.makeText(context, "E-mail enviado com sucesso", Toast.LENGTH_SHORT).show()
    }

    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

    NavigationLayout(navController = navController, selectedItemIndex =  2, rawNavBottomItems = navBottomItems) {
        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column(modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .verticalScroll(
                    rememberScrollState()
                )) {
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Quer uma ajudinha?",  style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(painter = painterResource(id = R.drawable.chat_unselected_icon), contentDescription = "chat", modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                ) {
                    items(suggestions) { suggestion ->
                        OutlinedButton(onClick = { body = suggestion.content }) {
                            Text(suggestion.displayText, color = colorResource(id = R.color.white))
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
                    onClick = {onSendEmailResult()}, text = "Enviar"
                )

            }

        }
    }
}





