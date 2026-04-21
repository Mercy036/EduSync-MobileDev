package com.edu.sync.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.edu.sync.ui.theme.GoldAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(onBack: () -> Unit) {
    val announcements = listOf(
        Announcement("Mid-Sem Exam Schedule Out", "Academic", "The schedule for March 2024 Mid-Sem exams has been updated on the portal.", "2h ago"),
        Announcement("Campus Hackathon 2024", "Events", "Registration opens tomorrow for the annual 48-hour hackathon.", "5h ago"),
        Announcement("Lost & Found: Blue Wallet", "Urgent", "Found a blue leather wallet in the library basement.", "1d ago"),
        Announcement("Hostel Fee Deadline", "Academic", "Reminder: Last day to pay hostel mess fees is Friday.", "2d ago")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Announcements", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(announcements) { ann ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Surface(
                                color = when(ann.type) {
                                    "Urgent" -> Color.Red.copy(alpha = 0.1f)
                                    "Academic" -> GoldAccent.copy(alpha = 0.1f)
                                    else -> Color.Blue.copy(alpha = 0.1f)
                                },
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    ann.type, 
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = when(ann.type) {
                                        "Urgent" -> Color.Red
                                        "Academic" -> GoldAccent
                                        else -> Color.Blue
                                    }
                                )
                            }
                            Text(ann.time, fontSize = 10.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(ann.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(ann.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}

data class Announcement(val title: String, val type: String, val description: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarBuddyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Car Buddy", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
            // Map Placeholder
            Surface(
                modifier = Modifier.fillMaxWidth().height(250.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.LightGray.copy(alpha = 0.3f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Map, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Text("Live Carpool Map Placeholder", color = Color.Gray)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Active Rides Near You", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(3) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.Gray))
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Rahul Singh", fontWeight = FontWeight.Bold)
                                Text("Heading to: City Center", fontSize = 12.sp, color = Color.Gray)
                            }
                            Button(
                                onClick = { /* Chat */ },
                                colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
                                shape = RoundedCornerShape(20.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp)
                            ) {
                                Text("Chat", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
