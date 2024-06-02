
import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
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
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.model.onboarding.OnboardingItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    onboardingItems: List<OnboardingItem>,
    destinationScreen: String
) {
    val startAnimation by remember { mutableStateOf(false) }
    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 5000,
            easing = LinearEasing
        ), label = ""
    )
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { onboardingItems.size })
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
                        Image(
                            painter = painterResource(id = onboardingItems[page].resId),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(15.dp)
                        ) {
                            Text(
                                onboardingItems[page].desc,
                                textAlign = TextAlign.Center,
                                color = colorResource(id = R.color.white),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge,
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
                    for (i in onboardingItems.indices) {
                        Box(
                            modifier = Modifier
                                .padding(end = if (i == onboardingItems.size - 1) 0.dp else 5.dp)
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

                if (selectedPage != onboardingItems.size - 1) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TextButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(onboardingItems.size - 1)
                                }
                            },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(text = "Pular", color = colorResource(id = R.color.white))
                        }

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                disabledContentColor = Color.Transparent,
                                containerColor = colorResource(id = R.color.purple_mid),
                                contentColor = colorResource(id = R.color.white)
                            ),
                            onClick = {
                                scope.launch {
                                    val nextPage = selectedPage + 1
                                    pagerState.animateScrollToPage(nextPage)
                                }
                            },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text(text = "Próximo", color = colorResource(id = R.color.white))
                        }
                    }
                }

                if (selectedPage == onboardingItems.size - 1) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            disabledContentColor = Color.Transparent,
                            containerColor = colorResource(id = R.color.purple_mid),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        onClick = {
                            navController.navigate(destinationScreen)
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(36.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Text(text = "Vamos lá", color = colorResource(id = R.color.white))
                    }
                }
            }
        }

    }

}
