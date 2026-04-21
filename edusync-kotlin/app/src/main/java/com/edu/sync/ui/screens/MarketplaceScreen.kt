package com.edu.sync.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.edu.sync.data.models.MarketplaceListing
import com.edu.sync.ui.theme.GoldAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(onBack: () -> Unit) {
    val listings = listOf(
        MarketplaceListing("1", "Calculus Textbook", 500.0, "Books", "Library", "Good condition", "", "Ansh", "uid1"),
        MarketplaceListing("2", "Scientific Calculator", 1200.0, "Electronics", "Hostel 3", "Hardly used", "", "Rahul", "uid2"),
        MarketplaceListing("3", "Lab Coat", 300.0, "Essentials", "Block B", "White, Size L", "", "Priya", "uid3"),
        MarketplaceListing("4", "Engineering Drawing Set", 800.0, "Instruments", "Admin Block", "Full set with drafter", "", "Karan", "uid4")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buy & Sell", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Post Ad */ },
                containerColor = GoldAccent,
                contentColor = Color.White,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Sell Item") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            ScrollableTabRow(
                selectedTabIndex = 0,
                containerColor = Color.Transparent,
                edgePadding = 16.dp,
                divider = {},
                indicator = {}
            ) {
                listOf("All", "Books", "Electronics", "Essentials", "Instruments").forEachIndexed { index, cat ->
                    Tab(
                        selected = index == 0,
                        onClick = { /* Filter */ },
                        text = { 
                            Surface(
                                color = if (index == 0) GoldAccent else Color.Transparent,
                                shape = RoundedCornerShape(20.dp),
                                border = if (index == 0) null else androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                            ) {
                                Text(
                                    cat, 
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                                    color = if (index == 0) Color.White else Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    )
                }
            }
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listings) { listing ->
                    ListingCard(listing)
                }
            }
        }
    }
}

@Composable
fun ListingCard(listing: MarketplaceListing) {
    Card(
        modifier = Modifier.fillMaxWidth().height(220.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(40.dp))
            }
            
            Column(modifier = Modifier.padding(8.dp)) {
                Text(listing.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1)
                Text("₹${listing.price.toInt()}", color = GoldAccent, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(12.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(listing.location, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }
    }
}
