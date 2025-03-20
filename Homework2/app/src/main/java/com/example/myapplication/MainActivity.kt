package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageBrowserApp()
                }
            }
        }
    }
}

data class ImageWithCaption(
    val imageResId: Int,
    val caption: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageBrowserApp() {
    // Sample images and captions - replace R.drawable.image1 with your actual drawable resources
    val images = listOf(
        ImageWithCaption(R.drawable.image, "Beautiful mountains with a lake view"),
        ImageWithCaption(R.drawable.image, "Sunset at the beach"),
        ImageWithCaption(R.drawable.image, "City skyline at night"),
        ImageWithCaption(R.drawable.image, "Forest trail in autumn"),
        ImageWithCaption(R.drawable.image, "Desert landscape with cactus")
    )

    var currentIndex by remember { mutableStateOf(0) }
    var jumpToText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image counter
        Text(
            text = "Image ${currentIndex + 1} of ${images.size}",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Image display area
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Image
                Image(
                    painter = painterResource(id = images[currentIndex].imageResId),
                    contentDescription = "Image ${currentIndex + 1}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Caption
        Text(
            text = images[currentIndex].caption,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Navigation controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            Button(
                onClick = {
                    currentIndex = if (currentIndex > 0) {
                        currentIndex - 1
                    } else {
                        images.size - 1
                    }
                }
            ) {
                Text("Back")
            }

            // Jump to specific image
            OutlinedTextField(
                value = jumpToText,
                onValueChange = { jumpToText = it },
                label = { Text("Go to #") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        jumpToText.toIntOrNull()?.let { number ->
                            if (number in 1..images.size) {
                                currentIndex = number - 1
                                jumpToText = ""
                            }
                        }
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier.width(100.dp),
                singleLine = true
            )

            // Next button
            Button(
                onClick = {
                    currentIndex = if (currentIndex < images.size - 1) {
                        currentIndex + 1
                    } else {
                        0
                    }
                }
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Jump button
        Button(
            onClick = {
                jumpToText.toIntOrNull()?.let { number ->
                    if (number in 1..images.size) {
                        currentIndex = number - 1
                        jumpToText = ""
                    }
                }
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageBrowserPreview() {
    MyApplicationTheme {
        ImageBrowserApp()
    }
}