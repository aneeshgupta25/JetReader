package com.example.jetreader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetreader.navigation.ReaderNavigation
import com.example.jetreader.screens.splash.SplashScreen
import com.example.jetreader.ui.theme.JetReaderTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val db = FirebaseFirestore.getInstance()
                    val user: MutableMap<String, Any> = HashMap()
                    user["firstName"] = "Aneesh"
                    user["lastName"] = "Gupta"
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("Firebase", "onCreate: ${it.id}")
                        }
                        .addOnFailureListener {
                            Log.d("Firebase", "onCreate: $it")
                        }
                    ReaderNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetReaderTheme {
//        SplashScreen()
    }
}