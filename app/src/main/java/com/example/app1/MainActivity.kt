package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app1.ui.theme.APP1Theme
import androidx.compose.material3.TopAppBar

data class NewsItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            APP1Theme {
                NewsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    val news = listOf(
        NewsItem(
            title = "Campus Wi-Fi upgrade completed",
            description = "New access points installed for better coverage in the main building.",
            icon = Icons.Filled.Wifi
        ),
        NewsItem(
            title = "Android Studio update available",
            description = "Patch improves build speed and reduces Gradle sync issues.",
            icon = Icons.Filled.Android
        ),
        NewsItem(
            title = "Scholarship applications open",
            description = "Submit required documents before Friday at the student office.",
            icon = Icons.Filled.School
        ),
        NewsItem(
            title = "Library hours extended",
            description = "The library now stays open until 10 PM starting next week.",
            icon = Icons.AutoMirrored.Filled.MenuBook
        ),
        NewsItem(
            title = "Tech meetup this weekend",
            description = "Beirut meetup: mobile dev, networking, and student projects.",
            icon = Icons.Filled.Event
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            itemsIndexed(news) { index, item ->
                NewsRow(item)

                // Bonus 1: divider between items
                if (index != news.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(start = 88.dp))
                }
            }
        }
    }
}

@Composable
fun NewsRow(item: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}