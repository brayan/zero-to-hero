package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.component.HabitItem
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.component.HabitsSkeleton
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewAction
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewIntent
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewModel
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewState
import kotlinx.coroutines.launch

@Composable
fun HabitsScreen(innerPadding: PaddingValues, habitsViewModel: HabitsViewModel) {
    val state by habitsViewModel.state.collectAsState()
    val action by habitsViewModel.action.collectAsState(null)

    var selectedFilter by remember { mutableStateOf(HabitFilter.LAST_12_MONTHS) }
    val coroutineScope = rememberCoroutineScope()

    val totalPoints = (state as? HabitsViewState.Success)?.habits?.sumOf { it.points } ?: 0

    LaunchedEffect(Unit) {
        habitsViewModel.onIntent(HabitsViewIntent.Init(selectedFilter))
    }

    action?.let {
        when (it) {
            is HabitsViewAction.NavigateToHabitDetails -> {
                println("Navigating to habit details for ID: ${it.habitId}")
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarWithFilter(selectedFilter, totalPoints) { newFilter ->
            selectedFilter = newFilter
            coroutineScope.launch {
                habitsViewModel.onIntent(HabitsViewIntent.FilterChanged(newFilter))
            }
        }

        Box(modifier = Modifier.padding(innerPadding)) {
            when (val currentState = state) {
                is HabitsViewState.Loading -> {
                    HabitsSkeleton()
                }

                is HabitsViewState.Success -> {
                    HabitList(currentState.habits, selectedFilter) { position ->
                        habitsViewModel.onIntent(HabitsViewIntent.HabitClicked(position))
                    }
                }

                is HabitsViewState.Error -> {
                    Text("Error")
                }
            }
        }
    }
}

@Composable
fun HabitList(habits: List<Habit>, filter: HabitFilter, onHabitClicked: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(habits) { index, habit ->
            HabitItem(habit, filter, index, onHabitClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithFilter(
    selectedFilter: HabitFilter,
    totalPoints: Int,
    onFilterSelected: (HabitFilter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = {
                Column {
                    Text("Habits")
                    Text(
                        text = "Filter: ${selectedFilter.name.replace("_", " ")} • Points: $totalPoints",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            actions = {
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Filter Options")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        HabitFilter.entries.forEach { filter ->
                            DropdownMenuItem(
                                text = { Text(filter.name.replace("_", " ")) },
                                onClick = {
                                    expanded = false
                                    onFilterSelected(filter)
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
