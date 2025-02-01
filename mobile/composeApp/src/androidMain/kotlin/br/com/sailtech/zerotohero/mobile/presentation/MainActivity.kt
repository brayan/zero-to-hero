package br.com.sailtech.zerotohero.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.screen.HabitsScreen
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewModel
import br.com.sailtech.zerotohero.mobile.presentation.theme.ZeroToHeroTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val habitsViewModel: HabitsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZeroToHeroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HabitsScreen(innerPadding, habitsViewModel)
                }
            }
        }
    }
}
