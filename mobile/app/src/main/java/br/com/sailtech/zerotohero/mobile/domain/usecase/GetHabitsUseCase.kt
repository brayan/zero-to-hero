package br.com.sailtech.zerotohero.mobile.domain.usecase

import br.com.sailtech.zerotohero.mobile.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.domain.model.HabitFilter

internal interface GetHabitsUseCase {
    suspend operator fun invoke(filter: HabitFilter): List<Habit>
}
