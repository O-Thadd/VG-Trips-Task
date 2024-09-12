package com.app.vgtask.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.vgtask.R
import com.app.vgtask.ui.homeUiState
import com.app.vgtask.ui.theme.VGTaskTheme
import com.app.vgtask.ui.theme.black
import com.app.vgtask.ui.theme.bold

@Composable
fun HomeScreen() {

}

@Composable
fun StatelessHomeScreen(uiState: HomeUiState){
    Column {
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
            Text(text = "Plan a Trip", fontFamily = bold, fontSize = 18.sp, color = Color(0xFF1D2433))
        }
    }
}

@Preview(widthDp = 412, showBackground = true)
@Composable
private fun PrevHomeScreen() {
    VGTaskTheme {
        StatelessHomeScreen(homeUiState)
    }
}
