package com.app.vgtask.ui.screens.tripCreation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.vgtask.CalendarItem
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.TripStyle
import javax.annotation.Untainted

@Composable
fun TripCreationScreen(
    goToTrip: (String) -> Unit,
    goBackToHome: () -> Unit
) {
    val viewModel: TripCreationViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle(null)

    state?.let {
        StatelessTripCreationScreen(
            state = state!!,
            search = { viewModel.searchCity(it) },
            onDestinationSelected = { viewModel.setDestination(it) },
            onStartDateSelected = { viewModel.setStartDate(it) },
            onEndDateSelected = { viewModel.setEndDate(it) },
            onComplete = { name, style, description -> viewModel.completeInfo(name, style, description) },
            resetCreationStatus = {  viewModel.resetCreationStatus() },
            refreshCities = { viewModel.refreshCities() },
            goBackToHome = goBackToHome
        )
    }

    LaunchedEffect(key1 = state?.creationStatus) {
        if ((state?.creationStatus ?: DataStatus.DEFAULT)  == DataStatus.PASSED){
            goToTrip(state!!.newlyCreatedTripId!!)
        }
    }

    DisposableEffect(true) {
        onDispose { viewModel.resetState() }
    }
}


@Composable
fun StatelessTripCreationScreen(
    state: TripCreationUiState,
    search: (String) -> Unit,
    onDestinationSelected: (String) -> Unit,
    onStartDateSelected: (CalendarItem) -> Unit,
    onEndDateSelected: (CalendarItem) -> Unit,
    onComplete: (String, String, String) -> Unit,
    resetCreationStatus: () -> Unit,
    refreshCities: () -> Unit,
    goBackToHome: () -> Unit
) {

    var tripCreationStage by remember { mutableIntStateOf(1) }

    Box {
        AnimatedContent(
            targetState = tripCreationStage,
            label = "trip creation stages animation",
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { fullWidth -> fullWidth } togetherWith
                            slideOutHorizontally { fullWidth -> -fullWidth }
                } else {
                    slideInHorizontally { fullWidth -> -fullWidth } togetherWith
                            slideOutHorizontally { fullWidth -> fullWidth }
                }
            }
        ) {
            when (it) {
                1 -> DestinationScreen(
                    cities = state.cities,
                    search = search,
                    onDestinationClicked = {
                        onDestinationSelected(it)
                        tripCreationStage = 2
                    },
                    refreshCities = refreshCities,
                    goBackToHome = goBackToHome
                )

                2 -> {
                    DateScreen(
                        startDate = state.startDate,
                        endDate = state.endDate,
                        setStart = onStartDateSelected,
                        setEnd = onEndDateSelected,
                        onDateConfirmed = { tripCreationStage = 3 },
                        goBackToHome = goBackToHome
                    )
                    BackHandler { tripCreationStage = 1 }
                }

                3 -> {
                    FinalizeScreen(
                        tripName = state.tripName,
                        tripStyle = TripStyle.getStyle(state.style),
                        tripDescription = state.description,
                        onComplete = onComplete,
                        goBackToHome = goBackToHome
                    )
                    BackHandler { tripCreationStage = 2 }
                }
            }
        }

        if (state.creationStatus == DataStatus.BUSY || state.creationStatus == DataStatus.FAILED){
            TripCreationStatusDialog(
                status = state.creationStatus,
                resetCreationStatus = resetCreationStatus
            )
        }
    }
}

@Composable
fun TripCreationStatusDialog(
    status: DataStatus,
    resetCreationStatus: () -> Unit
) {
    Surface(
        color = Color.Black.copy(0.5f)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                    .background(Color(0xFFE7F0FF), RoundedCornerShape(4.dp))
                    .size(330.dp, 280.dp)
            ) {

                if ( status == DataStatus.FAILED){
                    Text(
                        text = "Failed to create trip.\nCheck network and try again.",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FilledTonalButton(
                        onClick = { resetCreationStatus() },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Okay")
                    }
                }

                else {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Creating trip...")
                }
            }
        }
    }
}

//@Preview(widthDp = 412, showBackground = true)
//@Composable
//private fun PrevDialog() {
//    VGTaskTheme {
//        TripCreationStatusDialog(
//            status = TripCreationStatus.BUSY,
//            resetCreationStatus = { }
//        )
//    }
//}