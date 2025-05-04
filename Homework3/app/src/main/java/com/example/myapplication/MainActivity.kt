package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageListScreen()
                }
            }
        }
    }
}

data class ImageWithCaption(
    val imageResId: Int,
    val caption: String
)

@Composable
fun ImageListScreen() {
    val images = listOf(
        ImageWithCaption(R.drawable.image, "Beautiful mountains with a lake view"),
        ImageWithCaption(R.drawable.image, "Sunset at the beach"),
        ImageWithCaption(R.drawable.image, "City skyline at night"),
        ImageWithCaption(R.drawable.image, "Forest trail in autumn"),
        ImageWithCaption(R.drawable.image, "Desert landscape with cactus"),
        ImageWithCaption(R.drawable.image, "Snow-covered cabin in winter"),
        ImageWithCaption(R.drawable.image, "Rolling hills in spring"),
        ImageWithCaption(R.drawable.image, "Tropical island with palm trees"),
        ImageWithCaption(R.drawable.image, "Bridge over river at dawn"),
        ImageWithCaption(R.drawable.image, "Starry night sky in the mountains")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(images) { item ->
            ImageCard(item)
        }
    }
}

@Composable
fun ImageCard(item: ImageWithCaption) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.caption,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )
            Text(
                text = item.caption,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}
