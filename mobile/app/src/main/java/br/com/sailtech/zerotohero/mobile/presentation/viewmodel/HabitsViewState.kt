package br.com.sailtech.zerotohero.mobile.presentation.viewmodel

import br.com.sailtech.zerotohero.mobile.domain.model.Habit

internal sealed class HabitsViewState {
    data object Error : HabitsViewState()
    data object Loading : HabitsViewState()
    data class Success(val habits: List<Habit>) : HabitsViewState()
}
