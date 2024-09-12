package com.app.vgtask.ui.tripCreation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.vgtask.R
import com.app.vgtask.ui.testPreviewCities
import com.app.vgtask.ui.theme.VGTaskTheme

@Composable
fun DestinationScreen(uiState: TripCreationUiState) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.x),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Where", style = MaterialTheme.typography.headlineLarge, color = Color(0xFF1D2433))
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
            value = "",
            onValueChange = {},
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

        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            for (city in uiState.cities){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.mappinfilled), contentDescription = null)
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

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevDestinationScreen() {
    VGTaskTheme {
        DestinationScreen(TripCreationUiState(
            testPreviewCities,
            Triple(0, 0, 0),
            Triple(0, 0, 0),
            "",
            "",
            ""
        ))
    }
}