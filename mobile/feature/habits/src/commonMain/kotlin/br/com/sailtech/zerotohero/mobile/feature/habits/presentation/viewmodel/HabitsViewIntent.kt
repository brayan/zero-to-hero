package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter

sealed class HabitsViewIntent {
    data class HabitClicked(val position: Int) : HabitsViewIntent()
    data class Init(val filter: HabitFilter) : HabitsViewIntent()
    data class FilterChanged(val filter: HabitFilter) : HabitsViewIntent()
}
