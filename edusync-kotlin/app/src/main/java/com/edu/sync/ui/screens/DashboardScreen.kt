package com.edu.sync.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.sync.ui.components.EduSyncTile
import com.edu.sync.ui.theme.GoldAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    username: String,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Welcome back,",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = username,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = GoldAccent
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Profile */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(32.dp))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Built by students, for the students who actually show up.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            // Search/Copilot Tile
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = GoldAccent)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("What's on your mind today?", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                }
            }

            // Bento Grid Items
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                EduSyncTile(
                    title = "Notes.Co",
                    description = "AI Study Help",
                    icon = Icons.Default.Book,
                    onClick = { onNavigate("notes") },
                    modifier = Modifier.weight(1f).height(160.dp)
                )
                EduSyncTile(
                    title = "J-Cafe",
                    description = "Skip the queue",
                    icon = Icons.Default.Coffee,
                    onClick = { onNavigate("jcafe") },
                    modifier = Modifier.weight(1f).height(160.dp)
                )
            }

            EduSyncTile(
                title = "Timetable",
                description = "View your daily sync",
                icon = Icons.Default.DateRange,
                onClick = { onNavigate("timetable") },
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                EduSyncTile(
                    title = "Marketplace",
                    description = "Buy & Sell",
                    icon = Icons.Default.ShoppingCart,
                    onClick = { onNavigate("marketplace") },
                    modifier = Modifier.weight(1f).height(140.dp)
                )
                EduSyncTile(
                    title = "Car Buddy",
                    description = "Ride sharing",
                    icon = Icons.Default.DirectionsCar,
                    onClick = { onNavigate("carbuddy") },
                    modifier = Modifier.weight(1f).height(140.dp)
                )
            }

            // Live Alert Tile
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = GoldAccent.copy(alpha = 0.1f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                        Icon(Icons.Default.NotificationsActive, contentDescription = null, modifier = Modifier.size(18.dp), tint = GoldAccent)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Live Portal", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Found a lost ID card in Library Wing A - Rahul",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
