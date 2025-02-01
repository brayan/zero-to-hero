package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit

sealed class HabitsViewState {
    data object Error : HabitsViewState()
    data object Loading : HabitsViewState()
    data class Success(val habits: List<Habit>) : HabitsViewState()
}
