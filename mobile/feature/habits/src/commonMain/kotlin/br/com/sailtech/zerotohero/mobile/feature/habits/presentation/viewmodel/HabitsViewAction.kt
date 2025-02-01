package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel

sealed class HabitsViewAction {
    data class NavigateToHabitDetails(val habitId: Long) : HabitsViewAction()
}
