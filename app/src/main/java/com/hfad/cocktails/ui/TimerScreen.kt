package com.hfad.cocktails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.viewmodel.TimerViewModel

@Composable
fun TimerScreen(
    defaultTime: Int = 60,
    timerViewModel: TimerViewModel = viewModel()
) {
    LaunchedEffect(key1 = defaultTime) {
        timerViewModel.initDefaultTime(defaultTime)
    }

    val timeLeft by timerViewModel.timeLeft
    var inputTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pozostały czas: $timeLeft s",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputTime,
            onValueChange = { inputTime = it },
            label = { Text("Ustaw nowy czas (sekundy)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val newTime = inputTime.toIntOrNull() ?: 60
                timerViewModel.setUserTime(newTime)
                inputTime = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ustaw")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { timerViewModel.startTimer() }) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { timerViewModel.pauseTimer() }) {
                Text("Pauza")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { timerViewModel.resetTimer() }) {
                Text("Reset")
            }
        }
    }
}
