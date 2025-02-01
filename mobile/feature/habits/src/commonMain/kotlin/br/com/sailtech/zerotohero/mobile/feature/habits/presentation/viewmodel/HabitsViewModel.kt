package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel

import br.com.sailtech.zerotohero.mobile.feature.habits.BaseViewModel
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.usecase.GetHabitsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitsViewModel(
    private val getHabitsUseCase: GetHabitsUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<HabitsViewState>(HabitsViewState.Loading)
    val state: StateFlow<HabitsViewState> = _state

    private val _action = MutableSharedFlow<HabitsViewAction>()
    val action: SharedFlow<HabitsViewAction> = _action

    fun onIntent(intent: HabitsViewIntent) = scope.launch {
        runCatching {
            handleIntents(intent)
        }.onFailure {
            _state.value = HabitsViewState.Error
        }
    }

    private suspend fun handleIntents(intent: HabitsViewIntent) {
        when (intent) {
            is HabitsViewIntent.Init -> onInit()
            is HabitsViewIntent.HabitClicked -> onHabitClicked(intent)
        }
    }

    private suspend fun onInit() = runCatching {
        delay(5000)
        getHabitsUseCase(HabitFilter.ALL)
    }.onSuccess { habits ->
        _state.value = HabitsViewState.Success(habits)
    }.onFailure { error ->
        _state.value = HabitsViewState.Error
    }

    private suspend fun onHabitClicked(intent: HabitsViewIntent.HabitClicked) = runCatching {
        (_state.value as? HabitsViewState.Success)?.run {
            habits.getOrNull(intent.position)?.let { habit ->
                _action.emit(HabitsViewAction.NavigateToHabitDetails(habit.id))
            }
        }
    }
}
