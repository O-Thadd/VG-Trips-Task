package com.app.vgtask.ui.screens.tripCreation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.vgtask.CalendarItem
import com.app.vgtask.DayCalendarItem
import com.app.vgtask.R
import com.app.vgtask.generateCalendarItems
import com.app.vgtask.ui.theme.VGTaskTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DateScreen(
    startDate: CalendarItem?,
    endDate: CalendarItem?,
    setStart: (CalendarItem) -> Unit,
    setEnd: (CalendarItem) -> Unit,
    onDateConfirmed: () -> Unit
) {
    val calendarItems by remember { mutableStateOf(generateCalendarItems()) }
    var activeDateSelection by remember { mutableStateOf(ActiveDateSelection.START) }

    Surface(
        color = Color.White
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.blackx),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Date",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1D2433)
                )
            }


            Spacer(modifier = Modifier.height(18.dp))
            Divider()
            Spacer(modifier = Modifier.height(20.dp))

            Box {
                Column(
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    for (group in calendarItems) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = group.month,
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color(0xFF131A29),
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            FlowRow(
                                maxItemsInEachRow = 7,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                for (item in group.items) {
                                    Surface(
                                        color = when {
                                            item.active && item.dateTriple in listOf( endDate?.dateTriple, startDate?.dateTriple) -> MaterialTheme.colorScheme.primary
                                            item is DayCalendarItem -> Color.White
                                            item.active -> Color(0xFFF7F9FC)
                                            else -> Color(0xFFF9FAFB)
                                        },

                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier
                                            .clickable {
                                                if (!item.active) {
                                                    return@clickable
                                                }

                                                if(activeDateSelection == ActiveDateSelection.START){
                                                    setStart(item)
                                                } else {
                                                    setEnd(item)
                                                }
                                            }
                                            .size(44.dp, 40.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = item.name,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = when {
                                                    item.active && item.dateTriple in listOf(startDate?.dateTriple, endDate?.dateTriple) -> Color.White
                                                    item.value != null && !item.active -> Color(0xFFC4C4C4)
                                                    else -> Color(0xFF1D2433)
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                Surface(
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xFFF0F2F5)),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Row {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Start Date",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF1D2433),
                                )
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = if (activeDateSelection == ActiveDateSelection.START) MaterialTheme.colorScheme.primary else Color.White,
                                    border = BorderStroke(1.dp, Color(0xFF98A2B3)),
                                    modifier = Modifier.clickable { activeDateSelection = ActiveDateSelection.START }
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        Text(
                                            text = startDate?.formattedDate ?: "---",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (activeDateSelection == ActiveDateSelection.START) Color.White else Color(0xFF1D2433),
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Icon(
                                            painter = painterResource(id = R.drawable.calendarblanksmall),
                                            contentDescription = null,
                                            tint = if (activeDateSelection == ActiveDateSelection.START) Color.White else Color(0xFF344054)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "End Date",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color(0xFF1D2433),
                                )
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = if (activeDateSelection == ActiveDateSelection.END) MaterialTheme.colorScheme.primary else Color.White,
                                    border = BorderStroke(1.dp, Color(0xFF98A2B3)),
                                    modifier = Modifier.clickable { activeDateSelection = ActiveDateSelection.END }
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        Text(
                                            text = endDate?.formattedDate ?: "---",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (activeDateSelection == ActiveDateSelection.END) Color.White else Color(0xFF1D2433),
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Icon(
                                            painter = painterResource(id = R.drawable.calendarblanksmall),
                                            contentDescription = null,
                                            tint = if (activeDateSelection == ActiveDateSelection.END) Color.White else Color(0xFF344054)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        FilledTonalButton(
                            onClick = { onDateConfirmed() },
                            shape = RoundedCornerShape(4.dp),
                            enabled = startDate != null && endDate != null,
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Choose Date",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(vertical = 13.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevDateScreen() {
    VGTaskTheme {
        DateScreen(
            startDate = null,
            endDate = null,
            setStart = { },
            setEnd = { },
            onDateConfirmed = { }
        )
    }
}


enum class ActiveDateSelection{
    START,
    END
}