package br.com.sailtech.zerotohero.mobile.feature.habits.domain.usecase

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter

interface GetHabitsUseCase {
    suspend operator fun invoke(filter: HabitFilter): List<Habit>
}
