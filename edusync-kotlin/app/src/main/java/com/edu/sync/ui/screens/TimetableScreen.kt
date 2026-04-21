package com.edu.sync.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.sync.ui.components.EduSyncTile
import com.edu.sync.ui.theme.GoldAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(onBack: () -> Unit) {
    var step by remember { mutableStateOf(1) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Timetable Generator", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (step == 1) {
                TimetableSetupContent(onGenerate = { step = 2 })
            } else {
                TimetableResultContent(onReset = { step = 1 })
            }
        }
    }
}

@Composable
fun TimetableSetupContent(onGenerate: () -> Unit) {
    Text("Preference Setup", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
    Text("Let AI sync your study schedule based on your needs.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    
    Spacer(modifier = Modifier.height(24.dp))
    
    Text("How many subjects do you have?", fontWeight = FontWeight.Medium)
    var subjectCount by remember { mutableStateOf("5") }
    OutlinedTextField(
        value = subjectCount,
        onValueChange = { subjectCount = it },
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    Text("Total study hours per week?", fontWeight = FontWeight.Medium)
    var totalHours by remember { mutableStateOf("40") }
    OutlinedTextField(
        value = totalHours,
        onValueChange = { totalHours = it },
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    )
    
    Spacer(modifier = Modifier.height(32.dp))
    
    Button(
        onClick = onGenerate,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(Icons.Default.AutoFixHigh, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Generate Optimized Schedule", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TimetableResultContent(onReset: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text("Your AI Sync", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        TextButton(
            onClick = onReset,
            colors = ButtonDefaults.textButtonColors(contentColor = GoldAccent)
        ) {
            Text("Edit Prefs")
        }
    }
    
    Spacer(modifier = Modifier.height(16.dp))
    
    val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val mockSchedule = listOf(
        "Operating Systems" to "09:00 - 11:00",
        "Database Systems" to "11:30 - 13:00",
        "Lunch Break" to "13:00 - 14:00",
        "Computer Networks" to "14:00 - 16:00"
    )
    
    weekDays.forEach { day ->
        Card(
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = if (day == "Mon") GoldAccent.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(day, fontWeight = FontWeight.ExtraBold, color = if (day == "Mon") GoldAccent else Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                mockSchedule.forEach { (subject, time) ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(subject, fontSize = 14.sp)
                        Text(time, fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}
