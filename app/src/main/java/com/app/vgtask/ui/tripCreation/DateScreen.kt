package com.app.vgtask.ui.tripCreation

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
import androidx.compose.material3.Divider
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
import com.app.vgtask.CalendarItemsMonthGroup
import com.app.vgtask.DayCalendarItem
import com.app.vgtask.R
import com.app.vgtask.generateCalendarItems
import com.app.vgtask.ui.theme.VGTaskTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DateScreen() {

    var state by remember {
        mutableStateOf(DateScreenState())
    }

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
                for (group in state.calendarItems) {
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
                                        item.formattedDate in listOf(
                                            state.startDate,
                                            state.endDate
                                        ) -> MaterialTheme.colorScheme.primary

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
                                            val newState =
                                                if (state.activeDateSelection == DateSelection.START) {
                                                    state.copy(startDate = item.formattedDate!!)
                                                } else {
                                                    state.copy(endDate = item.formattedDate!!)
                                                }
                                            state = newState
                                        }
                                        .size(44.dp, 40.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = when {
                                                item.formattedDate in listOf(
                                                    state.startDate,
                                                    state.endDate
                                                ) -> Color.White

                                                item.value != null && !item.active -> Color(
                                                    0xFFC4C4C4
                                                )

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
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Row {

                }
            }
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevDateScreen() {
    VGTaskTheme {
        DateScreen()
    }
}

data class DateScreenState(
    val activeDateSelection: DateSelection = DateSelection.START,
    val startDate: String = "---",
    val endDate: String = "---",
    val calendarItems: List<CalendarItemsMonthGroup> = generateCalendarItems()
)

enum class DateSelection{
    START,
    END
}