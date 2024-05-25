package br.com.fiapstatupone.vigiaseguro.screens.register.alert

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiapstatupone.vigiaseguro.R
import br.com.fiapstatupone.vigiaseguro.model.Advice
import br.com.fiapstatupone.vigiaseguro.service.RetrofitFactory
import br.com.fiapstatupone.vigiaseguro.ui.theme.Montserrat
import br.com.fiapstatupone.vigiaseguro.ui.theme.MontserratSemibold
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Alert(navController: NavController, MenuText: String) {

        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(text = MenuText, fontFamily = MontserratSemibold,  color = Color(0xFF2b7deb))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("dashboard") }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go back" ,
                                tint = Color(0xFF2b7deb)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Mark as favorite",
                                tint = Color(0xFF2b7deb)
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Edit notes",
                                tint = Color(0xFF2b7deb)
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            }

            
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                dangerClose()
            }
        }



    }


@Composable
fun dangerClose() {
    Image( ImageBitmap.imageResource(R.drawable.shield), contentDescription = "",modifier = Modifier
            .aspectRatio(2.5f) )
        Text("Perigo próximo!", color = Color(0xFF2b7deb), fontSize = 25.sp ,fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text("Clique em 'Alertar' se avista-lo", color = Color(0xFFFFFFFF), fontSize = 15.sp ,fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp))
        picture()
        picture()
        picture()
        picture()
        picture()
        picture()
        picture()
        picture()



}

@Composable
fun picture () {
    Column(
        modifier = Modifier.padding(top = 40.dp).background(color = Color(0xFF272727))
            .padding(vertical = 20.dp, horizontal = 20.dp)
    ) {
        Image(
            ImageBitmap.imageResource(R.drawable.picture),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.FillWidth
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(10),
            modifier = Modifier.padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFf21e1e))
        ){
            Text(
                "ALERTAR",
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}