package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image (Replace with your own image in res/drawable)
        Image(
            painter = painterResource(id = R.drawable.image), // Make sure to add an image in res/drawable
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name
        Text(text = "Mohammad Bazrouk", fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)

        // Job Title
        Text(text = "Software Engineer", fontSize = 18.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        // Contact Info
        Text(text = "Email: mohammad.bazrouk@cix.csi.cuny.edu", fontSize = 16.sp)
        Text(text = "Phone: (888)-888-8888", fontSize = 16.sp)
        Text(text = "Website: www.mohammadbazrouk.com", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}
