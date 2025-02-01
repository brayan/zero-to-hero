package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel

sealed class HabitsViewIntent {
    data class HabitClicked(val position: Int) : HabitsViewIntent()
    data object Init : HabitsViewIntent()
}
