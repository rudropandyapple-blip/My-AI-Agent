package com.myagent.app

import android.os.Bundle
import android.content.Intent
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgentAppUI(onOpenSettings = {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            })
        }
    }
}

@Composable
fun AgentAppUI(onOpenSettings: () -> Unit) {
    var apiKey by remember { mutableStateOf("") }
    var statusText by remember { mutableStateOf("AI Agent Ready") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // হেডার
        Text(text = "My Private AI Agent", style = MaterialTheme.typography.headlineMedium)

        // স্ট্যাটাস ও ইনপুট বক্স
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = statusText, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = apiKey,
                onValueChange = { apiKey = it },
                label = { Text("OpenRouter / Retell API Key") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = onOpenSettings) {
                Text("Enable Screen Control (Accessibility)")
            }
        }

        // Retell AI ভয়েস কল বাটন
        Button(
            onClick = {
                statusText = "Listening..."
                // Retell AI-এর কানেকশন ট্রিগার
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text("Start Voice Assistant Call 🎙️")
        }
    }
}