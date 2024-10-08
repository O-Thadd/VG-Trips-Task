package com.app.vgtask.ui.screens.tripCreation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.vgtask.R
import com.app.vgtask.ui.models.TripStyle
import com.app.vgtask.ui.theme.VGTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalizeScreen(
    tripName: String?,
    tripStyle: TripStyle?,
    tripDescription: String?,
    onComplete: (String, String, String) -> Unit,
    goBackToHome: () -> Unit
) {
    var name by remember(tripName) { mutableStateOf(tripName) }
    var style by remember(tripStyle) { mutableStateOf(tripStyle) }
    var description by remember(tripDescription) { mutableStateOf(tripDescription) }

    Column {
        Surface(
            onClick = goBackToHome,
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        ){  }

        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.overlay),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(vertical = 30.dp, horizontal = 16.dp)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.weight(1f, true))
                        Icon(
                            painter = painterResource(id = R.drawable.blackx),
                            contentDescription = null,
                            modifier = Modifier.clickable { goBackToHome() }.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Create a Trip",
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp),
                        color = Color(0xFF1D2433),
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Let's Go! Build Your Next Adventure",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF647995),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Trip Name",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF1D2433),
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        value = name ?: "",
                        onValueChange = { name = it},
                        placeholder = {
                            Text(
                                text = "Enter the trip name",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF98A2B3),
                            )
                        },
                        modifier = Modifier
                            .border(1.dp, Color(0xFF98A2B3), RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Travel Style",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF1D2433),
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    var dropdownExpanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = dropdownExpanded,
                        onExpandedChange = { dropdownExpanded = it }) {
                        OutlinedTextField(
                            value = style?.displayName ?: "",
                            onValueChange = { style = TripStyle.getStyle(it) },
                            placeholder = {
                                Text(
                                    text = "Select your travel style",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF98A2B3),
                                )
                            },
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.caretdown),
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .border(1.dp, Color(0xFF98A2B3), RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                        )

                        DropdownMenu(
                            expanded = dropdownExpanded,
                            onDismissRequest = { dropdownExpanded = false },
                            modifier = Modifier.exposedDropdownSize()
                        ) {
                            TripStyle.entries.map {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = it.displayName,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFF1D2433),
                                        )
                                    },
                                    onClick = {
                                        style = TripStyle.getStyle(it.displayName)
                                        dropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Trip Description",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF1D2433),
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    OutlinedTextField(
                        value = description ?: "",
                        onValueChange = { description = it },
                        placeholder = {
                            Text(
                                text = "Tell us more about the trip",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF98A2B3),
                            )
                        },
                        modifier = Modifier
                            .border(1.dp, Color(0xFF98A2B3), RoundedCornerShape(4.dp))
                            .fillMaxWidth()
                            .height(188.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FilledTonalButton(
                        onClick = { onComplete(name!!, style!!.displayName, description!!) },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        enabled = name?.isNotBlank() ?: false && style != null && description?.isNotBlank() ?: false,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 13.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevFinalizeScreen() {
    VGTaskTheme {
        FinalizeScreen(
            tripName = null,
            tripStyle = null,
            tripDescription = null,
            onComplete = { _, _, _ -> },
            goBackToHome = {  }
        )
    }
}