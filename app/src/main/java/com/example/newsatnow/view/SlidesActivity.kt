package com.example.newsatnow.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.wewox.pagecurl.ExperimentalPageCurlApi
import eu.wewox.pagecurl.page.PageCurl
import com.example.newsatnow.R
import android.content.Intent
import androidx.core.view.WindowCompat
import com.example.newsatnow.view.Logins.LoginActivity
import com.example.newsatnow.view.Logins.MobileLoginActivity

class SlidesActivity : ComponentActivity() {

    private val images = listOf(
        R.drawable.intro_1,
        R.drawable.intro_2,
        R.drawable.intro_3,
        R.drawable.intro_4
    )

    private val titles = listOf(
        "Informatics News",
        "Local News Feed",
        "Live Stream Podcasts",
        "News Now"
    )

    private val subtitles = listOf(
        "Stay informed on emerging technologies, innovations, and policy developments shaping the digital world.",
        "Get quick insights into what's happening in your city, every day.",
        "Tune into engaging podcasts for in-depth analysis, expert interviews, and daily news roundups on the go.",
        "Personalize your news experience with real-time updates, local stories, tech insights, and live broadcasts—all in one place."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        setContent {
            OnboardingPageCurl(images, titles, subtitles)
        }
    }
}

@OptIn(ExperimentalPageCurlApi::class)
@Composable
fun OnboardingPageCurl(
    images: List<Int>,
    titles: List<String>,
    subtitles: List<String>
) {
    val context = LocalContext.current

    // Remember the current page
    var currentPage by remember { mutableStateOf(0) }

    PageCurl(count = images.size) { pageIndex ->
        // Update the current page
        currentPage = pageIndex

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image
            Image(
                painter = painterResource(id = images[pageIndex]),
                contentDescription = "Intro Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.8f) // 50% of the screen height
            )

            // Skip button
            if (pageIndex != images.size - 1) {
                TextButton(
                    onClick = { /* Navigate to login */
                        context.startActivity(Intent(context, MobileLoginActivity::class.java))
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp, vertical = 40.dp)
                ) {
                    Text(text = "Skip", color = Color.White, fontSize = 16.sp)
                }
            }

            // Bottom overlay card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
                    .padding(horizontal = 35.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = titles[pageIndex],
                    fontSize = 18.sp,
                    color = Color(0xFFF47E24),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 20.dp)
                )

                if (pageIndex == images.size - 1) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Local News | informatics & Tech Live TV & Podcasts",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = subtitles[pageIndex],
                    fontSize = 15.sp,
                    color = Color(0xFFAEAEAE),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                if (pageIndex == images.size - 1) {
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, MobileLoginActivity::class.java))
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF47E24)
                        ),
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(
                            text = "Login",
                            color = Color.White,
                            fontSize = 17.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // PUSHES BELOW CONTENT

                if (pageIndex != images.size - 1) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
                    ) {
                        images.forEachIndexed { index, _ ->
                            Box(
                                modifier = Modifier
                                    .size(15.dp)
                                    .padding(4.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (index == currentPage) Color(0xFFF47E24)
                                        else Color(0xFFBDBDBD)
                                    )
                            )
                        }
                    }
                }
            }

        }
    }
}




