package com.app.vgtask.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.vgtask.R
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.ui.testPreviewTrips
import com.app.vgtask.ui.theme.VGTaskTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val trips by viewModel.trips.collectAsStateWithLifecycle()
    StatelessHomeScreen(trips = trips)
}

@Composable
fun StatelessHomeScreen(trips: List<UiTrip>){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(state = scrollState)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrowleft),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Plan a Trip", style = MaterialTheme.typography.headlineLarge, color = Color(0xFF1D2433))
        }

        Box(
            modifier = Modifier.height(549.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.landing_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "Plan Your Dream Trip in Minutes",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF1D2433),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Build, personalize, and optimize your itineraries with our trip planner. Perfect for getaways, remote workcations, and any spontaneous escapade.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF676E7E)
                )
                Spacer(modifier = Modifier.weight(1f, true))
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color.White,
                    modifier = Modifier
                        .size(334.dp, 278.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Surface(
                            color = Color(0xFFF9FAFB),
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, Color(0xFFE4E7EC)),
                            modifier = Modifier
                                .size(302.dp, 86.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mappin),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Where to ?",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color(0xFF647995)
                                    )
                                    Text(
                                        text = "Select City",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color(0xFF676E7E)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Surface(
                                color = Color(0xFFF9FAFB),
                                shape = RoundedCornerShape(4.dp),
                                border = BorderStroke(1.dp, Color(0xFFE4E7EC)),
                                modifier = Modifier
                                    .size(147.dp, 82.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.calendarblank),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "Start Date",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFF647995)
                                        )
                                        Text(
                                            text = "Enter Date",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color(0xFF676E7E)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = Color(0xFFF9FAFB),
                                shape = RoundedCornerShape(4.dp),
                                border = BorderStroke(1.dp, Color(0xFFE4E7EC)),
                                modifier = Modifier
                                    .size(147.dp, 82.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.calendarblank),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "End Date",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFF647995)
                                        )
                                        Text(
                                            text = "Enter Date",
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color(0xFF676E7E)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.size(302.dp, 62.dp)
                        ) {
                            Text(text = "Creat a Trip")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //Trips

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Your Trips",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1D2433),

            )
            Text(
                text = "Your trip itineraries and  planned trips are placed here",
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF676E7E),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFFF0F2F5),

            ) {

                var dropDopDownExpanded by remember {
                    mutableStateOf(false)
                }

                var selectedItem by remember {
                    mutableStateOf("Planned Trips")
                }

                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable { dropDopDownExpanded = true }
                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = selectedItem)
                        Spacer(modifier = Modifier.weight(1f, true))
                        Image(painter = painterResource(id = R.drawable.caretdown), contentDescription = null)
                    }
                }

                DropdownMenu(
                    expanded = dropDopDownExpanded,
                    onDismissRequest = { dropDopDownExpanded = false }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Planned Trips",
                            modifier = Modifier.clickable {
                                selectedItem = "Planned Trips"
                                dropDopDownExpanded = false
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Trip Itineraries",
                            modifier = Modifier.clickable {
                                selectedItem = "Trip Itineraries"
                                dropDopDownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


//            Surface(
//                    shape = RoundedCornerShape(4.dp),
//                    border = BorderStroke(1.dp, Color(0xFFE4E7EC)),
//                    modifier = Modifier.size(358.dp, 384.dp)
//                ) {
//                    AsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(uiState.trips[0].imageUrl)
//                            .crossfade(true)
//                            .build(),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.size(326.dp, 230.dp)
//                    )
//                }

            for (trip in trips){
                Column {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, Color(0xFFE4E7EC)),
                        modifier = Modifier.size(358.dp, 384.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 12.dp, end = 16.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(trip.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(326.dp, 230.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = trip.name,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFF1D2433)
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Row {
                                Text(
                                    text = trip.ordinalStartDate,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF1D2433)
                                )
                                Spacer(modifier = Modifier.weight(1f, true))
                                Text(
                                    text = trip.duration,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF676E7E)
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            FilledTonalButton(
                                onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier.size(326.dp, 48.dp)
                            ) {
                                Text(
                                    text = "View",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true,)
@Composable
private fun PrevHomeScreen() {
    VGTaskTheme {
        StatelessHomeScreen(testPreviewTrips)
    }
}
