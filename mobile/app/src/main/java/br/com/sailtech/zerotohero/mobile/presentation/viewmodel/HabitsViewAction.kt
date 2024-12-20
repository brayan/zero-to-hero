package br.com.sailtech.zerotohero.mobile.presentation.viewmodel

sealed class HabitsViewAction {
    data class NavigateToHabitDetails(val habitId: Long) : HabitsViewAction()
}
