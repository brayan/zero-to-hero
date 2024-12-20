package br.com.sailtech.zerotohero.mobile.presentation.viewmodel

sealed class HabitsViewIntent {
    data class HabitClicked(val position: Int) : HabitsViewIntent()
    data object Init : HabitsViewIntent()
}
