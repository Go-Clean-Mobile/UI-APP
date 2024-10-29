package com.mobile.goclean

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mobile.goclean.theme.GoCleanTheme
import com.mobile.goclean.utility.Nav

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoCleanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Nav()
                }
            }
        }
    }
}
