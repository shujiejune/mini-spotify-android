package com.laioffer.spotify

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laioffer.spotify.ui.theme.SpotifyTheme

// customized extend AppCompatActivity
class MainActivity : AppCompatActivity() {
    private val TAG = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "We are at onCreate()");
        setContent {
            SpotifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun ArtistCardBox() {
        Box {
            Text("Alfred Sisley")
            Text("3 minutes ago")
        }
    }

    @Composable
    fun ArtistCardColumn() {
        Column {
            Text("Alfred Sisley")
            Text("3 minutes ago")
        }
    }

    @Composable
    fun ArtistCardRow() {
        Row {
            Text("Alfred Sisley")
            Text("3 minutes ago")
        }
    }

    @Preview
    @Composable
    fun PreviewArtistCardBox() {
        SpotifyTheme {
            Surface {
                ArtistCardBox()
            }
        }
    }

    @Preview
    @Composable
    fun PreviewArtistCardRow() {
        SpotifyTheme {
            Surface {
                ArtistCardRow()
            }
        }
    }

    @Preview
    @Composable
    fun PreviewArtistCardColumn() {
        SpotifyTheme {
            Surface {
                ArtistCardColumn()
            }
        }
    }

/*
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "We are at onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "We are at onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "We are at onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "We are at onStop()")
    }

    override fun onDestroy() {
        Log.d(TAG, "We are at onDestroy()")
        super.onDestroy()
    }
*/

    @Composable
    private fun Greeting(name: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
//        .padding(24.dp)
                .background(Color.Yellow)
                .padding(24.dp)

        ) {
            Text(text = "Hello,")
            Text(text = name)
        }
    }

    @Composable
    fun HelloContent(name: String, onNameChange: (String) -> Unit) {
//    var name: String = "" // state
        // by: delegation
        // var name by remember { mutableStateOf("") } // state
        Column {
            if (name.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "Hello! $name",
                    style = MaterialTheme.typography.body2
                )
            }
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text(text = "name") }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewGreeting() {
        SpotifyTheme {
            Surface {
                Greeting("Android")
            }
        }
    }
}
