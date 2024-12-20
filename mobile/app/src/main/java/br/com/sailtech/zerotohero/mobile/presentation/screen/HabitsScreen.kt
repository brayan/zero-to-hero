package br.com.sailtech.zerotohero.mobile.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.sailtech.zerotohero.mobile.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.presentation.component.HabitItem
import br.com.sailtech.zerotohero.mobile.presentation.component.HabitsSkeleton
import br.com.sailtech.zerotohero.mobile.presentation.viewmodel.HabitsViewAction
import br.com.sailtech.zerotohero.mobile.presentation.viewmodel.HabitsViewIntent
import br.com.sailtech.zerotohero.mobile.presentation.viewmodel.HabitsViewModel
import br.com.sailtech.zerotohero.mobile.presentation.viewmodel.HabitsViewState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HabitsScreen(innerPadding: PaddingValues) {
    val habitsViewModel: HabitsViewModel = koinViewModel()

    val state by habitsViewModel.state.collectAsState()
    val action by habitsViewModel.action.collectAsState(null)

    LaunchedEffect(Unit) {
        habitsViewModel.onIntent(HabitsViewIntent.Init)
    }

    action?.let {
        when (it) {
            is HabitsViewAction.NavigateToHabitDetails -> {
                println("Navigating to habit details for ID: ${it.habitId}")
            }
        }
    }

    Box(modifier = Modifier.padding(innerPadding)) {
        when (val currentState = state) {
            is HabitsViewState.Loading -> {
                HabitsSkeleton()
            }

            is HabitsViewState.Success -> {
                HabitList(currentState.habits) { position ->
                    habitsViewModel.onIntent(HabitsViewIntent.HabitClicked(position))
                }
            }

            is HabitsViewState.Error -> {
                Text("Error")
            }
        }
    }
}

@Composable
fun HabitList(habits: List<Habit>, onHabitClicked: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(habits) { index, habit ->
            HabitItem(habit, index, onHabitClicked)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHabitTrackerMainScreen() {
    MaterialTheme {
        HabitsScreen(PaddingValues(16.dp))
    }
}
