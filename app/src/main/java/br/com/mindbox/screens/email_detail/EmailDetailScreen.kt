package br.com.mindbox.screens.email_detail

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.util.date.DateUtils

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailScreen(
    navController: NavHostController,
    emailId: Long,
    userData: UserData?,
    rawNavBottomItems: List<NavBottomItem>
) {
    data class Suggestion(
        val displayText: String, val content: String
    )

    val context = LocalContext.current
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )

    val emailRepository = EmailRepository(context)
    val emailWithTasks = emailRepository.findEmailByIdWithSender(emailId)

    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)

    NavigationLayout(navController = navController, selectedItemIndex =  0, rawNavBottomItems = navBottomItems) {
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
                            text = DateUtils.getBrazilianDateTimeFormat().format(emailWithTasks.email.sendDate),
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
                        var isChecked by remember { mutableStateOf(false) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Checkbox(checked = isChecked,
                                onCheckedChange = { isChecked = !isChecked })
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





