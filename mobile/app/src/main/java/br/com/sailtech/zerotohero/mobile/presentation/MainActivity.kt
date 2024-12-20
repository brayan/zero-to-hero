package br.com.sailtech.zerotohero.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.sailtech.zerotohero.mobile.presentation.screen.HabitsScreen
import br.com.sailtech.zerotohero.mobile.presentation.theme.ZeroToHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZeroToHeroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HabitsScreen(innerPadding)
                }
            }
        }
    }
}
