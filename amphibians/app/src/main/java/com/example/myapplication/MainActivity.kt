package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
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
                    AmphibianListScreen()
                }
            }
        }
    }
}

data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    val imgSrc: String
)

@Composable
fun AmphibianListScreen() {
    val amphibians = listOf(
        Amphibian(
            name = "Great Basin Spadefoot",
            type = "Toad",
            description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        ),
        Amphibian(
            name = "Roraima Bush Toad",
            type = "Toad",
            description = "This toad is typically found in South America. Specifically on Mount Roraima at the borders of Venezuela, Brazil, and Guyana. The Roraima Bush Toad is typically black with yellow spots or marbling along the throat and belly.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/roraima-bush-toad.png"
        ),
        Amphibian(
            name = "Pacific Chorus Frog",
            type = "Frog",
            description = "Also known as the Pacific Treefrog, it is the most common frog on the Pacific Coast of North America. These frogs can vary in color between green and brown and can be identified by a brown stripe that runs from their nostril, through the eye, to the ear.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png"
        ),
        Amphibian(
            name = "Blue Jeans Frog",
            type = "Frog",
            description = "Sometimes called the Strawberry Poison-Dart Frog, this little amphibian is identifiable by its bright red body and blueish-purple arms and legs. The Blue Jeans Frog is not toxic to humans like some of its close relatives, but it can be harmful to some predators.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/blue-jeans-frog.png"
        ),
        Amphibian(
            name = "California Giant Salamander",
            type = "Salamander",
            description = "As the name implies, this salamander can be found in Northern California, as well as other parts of the Pacific Northwest. They prefer temperate areas with plenty of moisture and they typically can grow up to 30 cm.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/california-giant-salamander.png"
        ),
        Amphibian(
            name = "Tiger Salamander",
            type = "Salamander",
            description = "Tiger Salamanders are typically found on the Atlantic Coast of North America. They are named for their coloring which is typically a brown body broken up by yellowish-greenish spots. While they like moist conditions, they don't spend very much time in bodies of water. Instead they prefer to burrow into loose soil.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/tiger-salamander.png"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(amphibians) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = amphibian.imgSrc,
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
            Text(
                text = amphibian.description,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}
