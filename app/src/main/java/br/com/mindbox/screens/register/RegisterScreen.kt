package br.com.mindbox.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiapstatupone.mindbox.R
import br.com.fiapstatupone.mindbox.ui.theme.Montserrat


@Composable
fun Register(navController: NavController) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }


    LazyColumn(){
        item {
            Column(Modifier.padding(20.dp)) {


                Image(
                    ImageBitmap.imageResource(R.drawable.shield),
                    contentDescription = "",
                    modifier = Modifier
                        .aspectRatio(3.5f)
                )

                Text(
                    "Junte-se à comunidade",
                    color = Color(0xFF2b7deb),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Cadastre-se para proteger e ser protegido!",
                    fontSize = 19.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    modifier = Modifier.padding(bottom = 20.dp)
                )



                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = nameState.value,
                    onValueChange = { newValue -> nameState.value = newValue },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = { newValue -> emailState.value = newValue },
                    label = { Text(text = "Senha") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = { newValue -> passwordState.value = newValue },
                    label = { Text(text = "Confirmação de senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = { newValue -> passwordState.value = newValue },
                    label = { Text(text = "Nome completo") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = { newValue -> passwordState.value = newValue },
                    label = { Text(text = "Celular") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF2B7DEB)),
                    onClick = { navController.navigate("Dashboard") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2B7DEB))
                ) {
                    Text(text = "CADASTRAR")
                }
            }
        }
    }


}
