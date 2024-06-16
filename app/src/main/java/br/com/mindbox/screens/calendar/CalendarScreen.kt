package br.com.mindbox.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mindbox.R
import br.com.mindbox.components.AnimatedGradientBackground
import br.com.mindbox.components.Avatar
import br.com.mindbox.components.GradientButton
import br.com.mindbox.components.NavigationLayout
import br.com.mindbox.components.loadNavBottomItemsWithIcons
import br.com.mindbox.database.repository.CalendarEventRepository
import br.com.mindbox.model.calendar.CalendarEventWithUser
import br.com.mindbox.model.calendar_onboarding.CalendarMonthItem
import br.com.mindbox.model.navbottom.NavBottomItem
import br.com.mindbox.presentation.sign_in.UserData
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.util.date.DateUtils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CalendarScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    calendarMonthItems: List<CalendarMonthItem>,
    rawNavBottomItems: List<NavBottomItem>,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val authorizationService = AuthorizationService(context)
    val user = authorizationService.getLoggedUsers()[0];
    val startAnimation by remember { mutableStateOf(false) }
    val calendarEventRepository = CalendarEventRepository(context)

    val alphaAnim: State<Float> = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 5000, easing = LinearEasing
        ), label = ""
    )

    data class DateItem(val dayNumber: String, val dayOfWeek: String, val meetingDay: Date)


    val selectedDate = remember { mutableStateOf<Date?>(null) }
    val dates = remember {
        val currentDate = Date()
        val datesList = mutableListOf<DateItem>()

        val calendar = Calendar.getInstance()
        calendar.time = currentDate

        val dateFormat = SimpleDateFormat("dd")
        val dayOfWeekFormat = SimpleDateFormat("EEE")

        repeat(30) {
            val dayNumber = dateFormat.format(calendar.time)
            val dayOfWeek = dayOfWeekFormat.format(calendar.time)
            datesList.add(DateItem(dayNumber, dayOfWeek, calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        datesList
    }


    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { calendarMonthItems.size })
    val (selectedPage, setSelectedPage) = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    val navBottomItems = loadNavBottomItemsWithIcons(items = rawNavBottomItems)
    val events = if (selectedDate.value != null) {
        calendarEventRepository.findEventsByParticipantIdAndDay(user.id, selectedDate.value!!)
    } else {
        calendarEventRepository.findEventsByParticipantId(user.id)
    }

    NavigationLayout(navController = navController, selectedItemIndex =  1, rawNavBottomItems = navBottomItems) {
        AnimatedGradientBackground(alphaAnimate = alphaAnim.value) {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                HorizontalPager(
                    state = pagerState, modifier = Modifier.height(70.dp)
                ) { page ->

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            val previousPage = selectedPage - 1
                                            pagerState.animateScrollToPage(previousPage)
                                        }
                                    }, modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Last Month",
                                        tint = colorResource(R.color.white)
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    calendarMonthItems[page].lastMonth,
                                    textAlign = TextAlign.Center,
                                    color = colorResource(id = R.color.white),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }

                            Text(
                                text = calendarMonthItems[page].currentMonth,
                                color = colorResource(R.color.white),
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    calendarMonthItems[page].nextMonth,
                                    textAlign = TextAlign.Center,
                                    color = colorResource(id = R.color.white),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            val nextPage = selectedPage + 1
                                            pagerState.animateScrollToPage(nextPage)
                                        }
                                    }, modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "Next Month",
                                        tint = colorResource(R.color.white)
                                    )
                                }
                            }
                        }

                    }

                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(dates) { date ->
                        val isSelected = date.meetingDay == selectedDate.value

                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(10.dp)
                                .background(
                                    color = if (date.meetingDay == selectedDate.value) colorResource(R.color.white) else Color.Transparent,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .clickable {
                                    if (isSelected) {
                                        selectedDate.value = null
                                    } else {
                                        selectedDate.value = date.meetingDay
                                    }
                                }) {
                            Text(
                                text = date.dayNumber,
                                Modifier.padding(10.dp),
                                color = if (date.meetingDay == selectedDate.value) colorResource(
                                    id = R.color.layer_mid
                                ) else colorResource(R.color.white),
                                fontSize = 14.sp
                            )
                            Text(
                                text = date.dayOfWeek,
                                Modifier.padding(5.dp),
                                color = if (date.meetingDay == selectedDate.value) colorResource(
                                    id = R.color.layer_mid
                                ) else colorResource(R.color.white),
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                LazyColumn {
                    if (events.isEmpty()) {
                        item {
                            NoMeetingsView(navController)
                        }
                    } else {
                        items(events) { event ->
                            Column {
                                Spacer(modifier = Modifier.height(16.dp))
                                EventItem(event = event)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }

            }

        }
    }

}


@Composable
fun EventItem(
    event: CalendarEventWithUser
) {
    val formattedTime = "${DateUtils.getTimeFormat().format(event.calendarEvent.startDate!!)} - ${DateUtils.getTimeFormat().format(event.calendarEvent.endDate!!)}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = colorResource(id = R.color.layer_mid),
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(
                elevation = 10.dp, shape = RoundedCornerShape(10.dp), clip = true
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = event.calendarEvent.title,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(900.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.calendarEvent.description,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(R.color.white),
                    modifier = Modifier.width(600.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    modifier = Modifier.padding(4.dp)
                ) {
                    items(event.participants) { participant ->
                        Avatar(user = participant, size = 28.dp, withText = false)
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.purple_mid))
                        .padding(5.dp)
                        .clip(
                            RoundedCornerShape(1200.dp)
                        )

                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = colorResource(R.color.white),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = formattedTime,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(R.color.white)
                )
            }
        }

    }
}

@Composable
fun NoMeetingsView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Agenda livre para este dia",
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.white),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Você pode conversar com Goma, para marcar uma nova reunião.",
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.white),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        GradientButton(text = "Marcar reunião com Goma", onClick = {
            navController.navigate("chatOnboarding")

        })
    }
}

