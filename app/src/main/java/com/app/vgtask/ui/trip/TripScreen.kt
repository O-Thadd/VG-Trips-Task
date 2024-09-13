package com.app.vgtask.ui.trip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.vgtask.R
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.ui.theme.VGTaskTheme
import com.app.vgtask.ui.theme.bold
import com.app.vgtask.ui.theme.regular
import com.app.vgtask.ui.trip1

@Composable
fun TripScreen(trip: UiTrip) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
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

        Spacer(modifier = Modifier.height(18.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(trip.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.bodypalm),
            modifier = Modifier
                .height(height = 235.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Surface(
                    color = Color(0xFFF7F9FC),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendarblanksmall),
                            contentDescription = null
                        )
                        Text(
                            text = trip.startDate,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF1D2433)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.arrowright),
                            contentDescription = null
                        )
                        Text(
                            text = trip.endDate,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF1D2433)
                        )
                    }
                }
                Text(
                    text = trip.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
                Text(
                    text = "${trip.destination} | ${trip.style}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF676E7E)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.handshake), contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Trip Collaboration",
                            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Surface(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.sharefat), contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Share Trip",
                            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Icon(painter = painterResource(id = R.drawable.dotsthree), contentDescription = null)
            }

            Spacer(modifier = Modifier.height(28.dp))

            // add-itinerary cards
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AddItineraryCard(
                    title = "Activities",
                    buttonText = "Add Activities",
                    containerColor = Color(0xFF000031),
                    titleColor = Color.White,
                    bodyColor = Color.White,
                    buttonColor = MaterialTheme.colorScheme.primary,
                    buttonTextColor = Color.White
                )

                AddItineraryCard(
                    title = "Hotels",
                    buttonText = "Add Hotels",
                    containerColor = Color(0xFFE7F0FF),
                    titleColor = Color.Black,
                    bodyColor = Color(0xFF1D2433),
                    buttonColor = MaterialTheme.colorScheme.primary,
                    buttonTextColor = Color.White
                )

                AddItineraryCard(
                    title = "Flights",
                    buttonText = "Add Flights",
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleColor = Color.White,
                    bodyColor = Color.White,
                    buttonColor = Color.White,
                    buttonTextColor = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Trip itineraries",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
            Text(
                text = "Your trip itineraries are placed here",
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF676E7E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            FlightItineraryCard(hasFlight = true)
        }
    }
}

//@Preview(widthDp = 412, showBackground = true)
//@Composable
//private fun PrevTestScreen() {
//    VGTaskTheme {
//        TripScreen(trip1)
//    }
//}

@Composable
fun AddItineraryCard(
    title: String,
    buttonText: String,
    containerColor: Color,
    titleColor: Color,
    bodyColor: Color,
    buttonColor: Color,
    buttonTextColor: Color
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = containerColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 14.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = titleColor,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Build, personalize, and optimize your itineraries with our trip planner.",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = regular),
                color = bodyColor
            )
            Spacer(modifier = Modifier.height(36.dp))
            FilledTonalButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(buttonColor, buttonTextColor),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun FlightItineraryCard(hasFlight: Boolean) {
    Surface(
        color = Color(0xFFF0F2F5),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Image(painter = painterResource(id = R.drawable.airplaneinflight), contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Flights",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF1D2433)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(4.dp)
            ) {
                if (!hasFlight) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 54.dp, horizontal = 50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wing),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "No request yet",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF1D2433)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(),
                            modifier = Modifier.width(228.dp)
                        ) {
                            Text(
                                text = "Add Flight",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }

                else {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.american_airlines_symbol_svg),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "American Airlines",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color(0xFF1D2433)
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "AA-829",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF676E7E)
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "08:35",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color(0xFF1D2433)
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "Sun, 20 Aug",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF676E7E)
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.airplanetakeoff),
                                        contentDescription = null
                                    )

                                    Text(
                                        text = "1h 45m",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF676E7E)
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.airplanelanding),
                                        contentDescription = null
                                    )

                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFFE7F0FF),
                                    modifier = Modifier.size(120.dp, 8.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 30.dp)
                                    ) {

                                    }
                                }

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "LOS",
                                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                        color = Color(0xFF1D2433)
                                    )

                                    Text(
                                        text = "Direct",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF676E7E)
                                    )

                                    Text(
                                        text = "SIN",
                                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                        color = Color(0xFF1D2433)
                                    )
                                }
                            }

                            Column(
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "09:55",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color(0xFF1D2433)
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "Sun, 20 Aug",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF676E7E)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Divider(color = Color(0xFFE4E7EC))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(38.dp, Alignment.CenterHorizontally),
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Flight details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Price details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Edit details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Divider(color = Color(0xFFE4E7EC))


                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.currencyngn),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "123,450.00",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFF1D2433)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            color = Color(0xFFFBEAE9),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(vertical = 24.dp, horizontal = 125.dp)
                            ) {
                                Text(
                                    text = "Remove",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                    color = Color(0xFF9E0A05)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.x),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(widthDp = 412, showBackground = true)
//@Composable
//private fun PrevFlightItCard() {
//    VGTaskTheme {
//        FlightItineraryCard(hasFlight = false)
//    }
//}

@Composable
fun HotelItineraryCard(hasHotel: Boolean) {
    Surface(
        color = Color(0xFF344054),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Image(painter = painterResource(id = R.drawable.buildings), contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Hotels",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(4.dp)
            ) {
                if (!hasHotel) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 54.dp, horizontal = 50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hotel),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "No request yet",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF1D2433)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(),
                            modifier = Modifier.width(228.dp)
                        ) {
                            Text(
                                text = "Add Hotel",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }

                else {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.rectangle_3437),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(224.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Riviera Resort, Lekki",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "18, Kenneth Agbakuru Street, Off Access Bank Admiralty Way, Lekki Phase1",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1D2433)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mappin),
                                    contentDescription = null,
                                )

                                Text(
                                    text = "Flight details",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(painter = painterResource(id = R.drawable.star), contentDescription = null)

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "8.5 (436)",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF676E7E)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(painter = painterResource(id = R.drawable.bed), contentDescription = null)

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "King size room",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF676E7E)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Divider(color = Color(0xFFE4E7EC))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                        ) {
                            Image(painter = painterResource(id = R.drawable.calendarblank), contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "In: 20-04-2024",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF647995)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Image(painter = painterResource(id = R.drawable.calendarblank), contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = " Out: 29-04-2024",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF647995)
                            )
                        }

                        Divider(color = Color(0xFFE4E7EC))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(38.dp),
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Hotel details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Price details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Edit details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Divider(color = Color(0xFFE4E7EC))


                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.currencyngn),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "123,450.00",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFF1D2433)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //Remove button
                        Surface(
                            color = Color(0xFFFBEAE9),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(vertical = 24.dp, horizontal = 125.dp)
                            ) {
                                Text(
                                    text = "Remove",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                    color = Color(0xFF9E0A05)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.x),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(widthDp = 412, showBackground = true)
//@Composable
//private fun PrevFlightItCard() {
//    VGTaskTheme {
//        HotelItineraryCard(hasHotel = true)
//    }
//}



@Composable
fun ActivityItineraryCard(hasActivity: Boolean) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Image(painter = painterResource(id = R.drawable.roadhorizon), contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Activities",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                shape = RoundedCornerShape(4.dp)
            ) {
                if (!hasActivity) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 54.dp, horizontal = 50.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hotel),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "No request yet",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF1D2433)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        FilledTonalButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(),
                            modifier = Modifier.width(228.dp)
                        ) {
                            Text(
                                text = "Add Hotel",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }

                else {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.rectangle_3437),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(224.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = "Riviera Resort, Lekki",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "18, Kenneth Agbakuru Street, Off Access Bank Admiralty Way, Lekki Phase1",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1D2433)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mappin),
                                    contentDescription = null,
                                )

                                Text(
                                    text = "Flight details",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                    color = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(painter = painterResource(id = R.drawable.star), contentDescription = null)

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "8.5 (436)",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF676E7E)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(painter = painterResource(id = R.drawable.bed), contentDescription = null)

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "King size room",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF676E7E)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Divider(color = Color(0xFFE4E7EC))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                        ) {
                            Image(painter = painterResource(id = R.drawable.calendarblank), contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "In: 20-04-2024",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF647995)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Image(painter = painterResource(id = R.drawable.calendarblank), contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = " Out: 29-04-2024",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF647995)
                            )
                        }

                        Divider(color = Color(0xFFE4E7EC))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(38.dp),
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Hotel details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Price details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "Edit details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Divider(color = Color(0xFFE4E7EC))


                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.currencyngn),
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "123,450.00",
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color(0xFF1D2433)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //Remove button
                        Surface(
                            color = Color(0xFFFBEAE9),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(vertical = 24.dp, horizontal = 125.dp)
                            ) {
                                Text(
                                    text = "Remove",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                                    color = Color(0xFF9E0A05)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.x),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevActivityCard() {
    VGTaskTheme {
        ActivityItineraryCard(hasActivity = false)
    }
}