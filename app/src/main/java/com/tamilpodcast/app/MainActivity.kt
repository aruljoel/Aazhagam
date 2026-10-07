package com.tamilpodcast.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TamilPodcastApp()
        }
    }
}

@Composable
fun TamilPodcastApp() {

    var selectedTab by remember { mutableIntStateOf(0) }

    MaterialTheme {

        Scaffold(
            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        icon = { Text("⌂") },
                        label = { Text("முகப்பு") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        icon = { Text("⌕") },
                        label = { Text("தேடல்") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        icon = { Text("♫") },
                        label = { Text("Playlist") }
                    )

                    NavigationBarItem(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        icon = { Text("☺") },
                        label = { Text("சுயவிவரம்") }
                    )
                }
            }
        ) { padding ->

            when (selectedTab) {

                0 -> HomeScreen(
                    modifier = Modifier.padding(padding)
                )

                1 -> SearchScreen(
                    modifier = Modifier.padding(padding)
                )

                2 -> PlaylistScreen(
                    modifier = Modifier.padding(padding)
                )

                3 -> ProfileScreen(
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "தமிழ் பாட்காஸ்ட்",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "தொடர்ந்து பாருங்கள்",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("▶ தொடங்கு")
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "பிரபலமான பாட்காஸ்ட்கள்",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("🎙️ தமிழ் செய்திகள்")
        Text("🎙️ வரலாறு மற்றும் கலாசாரம்")
        Text("🎙️ தொழில்நுட்பம்")
        Text("🎙️ பொழுதுபோக்கு")
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "தேடல்",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("தலைப்பு, முக்கியச் சொல் அல்லது ஆசிரியர்")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("தேடல் முடிவுகள்")
    }
}

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "எனது Playlist",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ புதிய Playlist உருவாக்கு")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("சேமித்த Playlist-கள்")
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "சுயவிவரம்",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Email மூலம் உள்நுழைக")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OTP பெறுக")
        }
    }
}