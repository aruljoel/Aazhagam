package com.tamilpodcast.app

import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import com.tamilpodcast.app.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val KalanjiyamBlack = Color(0xFF07090D)
private val KalanjiyamCard = Color(0xFF12151B)
private val KalanjiyamCard2 = Color(0xFF1A1D24)
private val KalanjiyamRed = Color(0xFFFF1744)
private val KalanjiyamOrange = Color(0xFFFF7043)
private val KalanjiyamWhite = Color(0xFFF5F5F5)
private val KalanjiyamGray = Color(0xFF9AA0AA)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KalanjiyamApp()
        }
    }
}

@Composable
fun KalanjiyamApp() {

    var showSplash by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        delay(2500)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        MainScreen()
    }
}

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KalanjiyamBlack)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.kalanjiyam_splash
            ),
            contentDescription = "களஞ்சியம்",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MainScreen() {

    var selectedTab by remember {
        mutableStateOf(0)
    }

    Scaffold(
        containerColor = KalanjiyamBlack,

        bottomBar = {

            NavigationBar(
                containerColor = Color(0xFF0D1015)
            ) {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Text(
                            text = "⌂",
                            fontSize = 22.sp
                        )
                    },
                    label = {
                        Text("முகப்பு")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Text(
                            text = "⌕",
                            fontSize = 24.sp
                        )
                    },
                    label = {
                        Text("தேடல்")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Text(
                            text = "▤",
                            fontSize = 21.sp
                        )
                    },
                    label = {
                        Text("நூலகம்")
                    }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = {
                        Text(
                            text = "●",
                            fontSize = 20.sp
                        )
                    },
                    label = {
                        Text("சுயவிவரம்")
                    }
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (selectedTab) {
                0 -> HomeScreen()
                1 -> SearchScreen()
                2 -> LibraryScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KalanjiyamBlack)
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.kalanjiyam_logo
                ),
                contentDescription = "களஞ்சியம்",
                modifier = Modifier.height(55.dp),
                contentScale = ContentScale.Fit
            )

            Row {

                Text(
                    text = "♡",
                    color = KalanjiyamWhite,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(end = 18.dp)
                )

                Text(
                    text = "⋮",
                    color = KalanjiyamWhite,
                    fontSize = 28.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
        ) {

            CategoryTab(
                title = "முகப்பு",
                selected = true
            )

            CategoryTab(
                title = "பாட்காஸ்ட்"
            )

            CategoryTab(
                title = "வீடியோக்கள்"
            )

            CategoryTab(
                title = "படைப்பாளர்கள்"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        FeaturedCard()

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("தொடர்ந்து பாருங்கள்")

        Spacer(modifier = Modifier.height(12.dp))

        VideoRow(
            listOf(
                "தமிழின் பயணம்",
                "மறைக்கப்பட்ட வரலாறு",
                "நம் இசையின் கதை"
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("பிரபலமானவை")

        Spacer(modifier = Modifier.height(12.dp))

        VideoRow(
            listOf(
                "தமிழ் சினிமா உரையாடல்",
                "தொழில்நுட்ப உலகம்",
                "உலகத்தை சுற்றி"
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("புதிய வெளியீடுகள்")

        Spacer(modifier = Modifier.height(12.dp))

        VideoRow(
            listOf(
                "இன்றைய சிறப்பு",
                "புதிய தலைமுறை",
                "ஒரு கதை சொல்லட்டுமா?"
            )
        )
    }
}

@Composable
fun CategoryTab(
    title: String,
    selected: Boolean = false
) {

    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (selected)
                    KalanjiyamRed
                else
                    KalanjiyamCard
            )
            .padding(
                horizontal = 18.dp,
                vertical = 9.dp
            )
    ) {

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun FeaturedCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .height(230.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF3A0D16),
                        Color(0xFF751C20),
                        Color(0xFF181A20)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {

            Text(
                text = "இன்றைய சிறப்பு",
                color = KalanjiyamOrange,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "மாநாடு முதல்\nமாற்றம் வரை",
                color = KalanjiyamWhite,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = KalanjiyamWhite,
                    contentColor = Color.Black
                )
            ) {

                Text(
                    text = "▶  பார்க்க",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {

    Text(
        text = title,
        color = KalanjiyamWhite,
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 18.dp)
    )
}

@Composable
fun VideoRow(
    titles: List<String>
) {

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 18.dp)
    ) {

        titles.forEachIndexed { index, title ->

            VideoCard(
                title = title,
                number = index + 1
            )
        }
    }
}

@Composable
fun VideoCard(
    title: String,
    number: Int
) {

    Column(
        modifier = Modifier
            .width(170.dp)
            .padding(end = 12.dp)
            .clickable { }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF351018),
                            Color(0xFF81222B),
                            Color(0xFF24262D)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "▶",
                color = KalanjiyamWhite,
                fontSize = 34.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${number * 1}.2K பார்வைகள்",
            color = KalanjiyamGray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SearchScreen() {

    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KalanjiyamBlack)
            .padding(18.dp)
    ) {

        Text(
            text = "தேடல்",
            color = KalanjiyamWhite,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("வீடியோ, பாட்காஸ்ட், படைப்பாளர்...")
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = KalanjiyamCard,
                unfocusedContainerColor = KalanjiyamCard,
                focusedTextColor = KalanjiyamWhite,
                unfocusedTextColor = KalanjiyamWhite,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "பிரபலமான தேடல்கள்",
            color = KalanjiyamWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            )
        ) {

            SearchChip("தமிழ் சினிமா")
            SearchChip("வரலாறு")
            SearchChip("இசை")
            SearchChip("தொழில்நுட்பம்")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "வகைகள்",
            color = KalanjiyamWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        CategoryCard("வரலாறு")
        CategoryCard("இலக்கியம்")
        CategoryCard("தொழில்நுட்பம்")
        CategoryCard("பயணம்")
    }
}

@Composable
fun SearchChip(title: String) {

    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(KalanjiyamCard)
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
    ) {

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 13.sp
        )
    }
}

@Composable
fun CategoryCard(title: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(KalanjiyamCard)
            .padding(18.dp)
    ) {

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun LibraryScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KalanjiyamBlack)
            .padding(18.dp)
    ) {

        Text(
            text = "நூலகம்",
            color = KalanjiyamWhite,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            )
        ) {

            CategoryTab(
                title = "வரலாறு",
                selected = true
            )

            CategoryTab(
                title = "பிடித்தவை"
            )

            CategoryTab(
                title = "Playlist"
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "உங்கள் Playlist",
            color = KalanjiyamWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        LibraryItem("பிடித்த வீடியோக்கள்")
        LibraryItem("பின்னர் பார்க்க")
        LibraryItem("எனது Playlist")
    }
}

@Composable
fun LibraryItem(title: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(KalanjiyamCard)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(KalanjiyamCard2),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "▶",
                color = KalanjiyamRed,
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KalanjiyamBlack)
            .padding(18.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(
                id = R.drawable.kalanjiyam_logo
            ),
            contentDescription = "களஞ்சியம்",
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "வணக்கம்!",
            color = KalanjiyamWhite,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "களஞ்சியத்தை ரசிக்க உங்களுக்கு Viewer account தேவையில்லை.",
            color = KalanjiyamGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileOption("♡  பிடித்தவை")
        ProfileOption("▤  என் Playlist")
        ProfileOption("◷  பார்வை வரலாறு")
        ProfileOption("⚙  அமைப்புகள்")

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = KalanjiyamRed
            )
        ) {

            Text(
                text = "நான் ஒரு படைப்பாளர்",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "படைப்பாளர் Login விரைவில்...",
            color = KalanjiyamGray,
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ProfileOption(title: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(KalanjiyamCard)
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            color = KalanjiyamWhite,
            fontSize = 16.sp
        )
    }
}