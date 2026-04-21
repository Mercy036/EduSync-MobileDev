package com.edu.sync.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.edu.sync.ui.theme.GoldAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyShelfScreen(onBack: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("PDFs", "YouTube", "Notes")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Shelf", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = GoldAccent,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = GoldAccent
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTab) {
                0 -> ShelfPdfContent()
                1 -> ShelfVideoContent()
                2 -> ShelfNotesContent()
            }
        }
    }
}

@Composable
fun ShelfPdfContent() {
    val items = listOf("Lecture 1: Intro.pdf", "Lab Manual.pdf", "Quiz Review.pdf")
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items) { name ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color.Red)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(name, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
fun ShelfVideoContent() {
    val videos = listOf(
        "OS Tutorials by Neso Academy" to "https://youtube.com/...",
        "DBMS Full Course" to "https://youtube.com/...",
        "Networking Basics" to "https://youtube.com/..."
    )
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(videos) { (title, url) ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.Black), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.PlayCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(48.dp))
                    }
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(url, fontSize = 10.sp, color = GoldAccent)
                    }
                }
            }
        }
    }
}

@Composable
fun ShelfNotesContent() {
    val notes = listOf("Maths Formulas", "History Key points", "Chemistry Equations")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(notes) { name ->
            Card(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Text(name, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.EditNote, contentDescription = null, modifier = Modifier.align(Alignment.BottomEnd), tint = GoldAccent)
                }
            }
        }
    }
}
