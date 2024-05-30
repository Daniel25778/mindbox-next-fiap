import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.util.data.listData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GetStartedScreen(
    navController: NavController,
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = LinearEasing
        ), label = ""
    )
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { listData.size })
    val (selectedPage, setSelectedPage) = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }
    Scaffold {
        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(0.6f)
                ) { page ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.height(450.dp)) {
                            Image(
                                painter = painterResource(id = listData[page].resId),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()

                            )
                        }
                        Box(Modifier.padding(15.dp)) {
                            Text(
                                listData[page].desc,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    for (i in listData.indices) {
                        Box(
                            modifier = Modifier
                                .padding(end = if (i == listData.size - 1) 0.dp else 5.dp)
                                .width(if (i == selectedPage) 20.dp else 10.dp)
                                .height(10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (i == selectedPage) colorResource(id = R.color.purple_mid) else colorResource(
                                        id = R.color.white
                                    )
                                )
                        )
                    }
                }

                if (selectedPage != listData.size - 1) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TextButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(listData.size - 1)
                                }
                            },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(text = "Pular", color = colorResource(id = R.color.white))
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    val nextPage = selectedPage + 1
                                    pagerState.animateScrollToPage(nextPage)
                                }
                            },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(text = "Próximo")
                        }
                    }
                }

                if (selectedPage == listData.size - 1) {
                    Button(
                        onClick = {
                            navController.navigate("login")
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(36.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Text(text = "Vamos lá")
                    }
                }
            }
        }

    }

}
