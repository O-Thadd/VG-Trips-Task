package com.app.vgtask.ui.screens.tripCreation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.vgtask.R
import com.app.vgtask.data.models.City
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.VGTaskData
import com.app.vgtask.ui.previewTestTripCreationUiState
import com.app.vgtask.ui.theme.VGTaskTheme

@Composable
fun DestinationScreen(
    cities: VGTaskData<List<City>>,
    search: (String) -> Unit,
    refreshCities: () -> Unit,
    onDestinationClicked: (String) -> Unit
) {
    var searchTerm by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        refreshCities()
    }

    Surface(
        color = Color.White
    )  {
        Column(
            modifier = Modifier.fillMaxHeight()
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
                Text(
                    text = "Where",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1D2433)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))
            Divider()
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Please select a city",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF647995),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            TextField(
                value = searchTerm,
                onValueChange = {
                    searchTerm = it
                    search(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.labelLarge,
                placeholder = {
                    Text(
                        text = "Search city",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF647995),
                        modifier = Modifier.padding(start = 20.dp)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(2.dp),
                    )
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Log.e("zzz", "cities is: $cities" )
            when (cities.status) {
                DataStatus.BUSY -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                DataStatus.FAILED -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Failed to load cities\nCheck network and try again",
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        FilledTonalButton(
                            onClick = { refreshCities() },
                            colors = ButtonDefaults.filledTonalButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.White),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(text = "Refresh")
                        }
                    }

                }

                else -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(40.dp),
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (city in cities.data!!) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { onDestinationClicked(city.id) }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mappinfilled),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(18.dp))
                                Column {
                                    Text(
                                        text = "${city.name}, ${city.country}",
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = Color(0xFF1D2433),
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                    Text(
                                        text = city.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF676E7E),
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f, true))
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(city.flag)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(50.dp, 30.dp)
                                    )
                                    Text(
                                        text = city.countryCode,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF676E7E),
                                    )
                                }
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
private fun PrevDestinationScreen() {
    VGTaskTheme {
        DestinationScreen(
            cities = previewTestTripCreationUiState.cities,
            search = {  },
            onDestinationClicked = {  },
            refreshCities = {  }
        )
    }
}

