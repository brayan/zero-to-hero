package br.com.sailtech.zerotohero.mobile.domain.usecase

import br.com.sailtech.zerotohero.mobile.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.domain.model.HabitFilter
import br.com.sailtech.zerotohero.mobile.domain.repository.HabitsRepository

internal class GetHabitsUseCaseImpl(
    private val habitsRepository: HabitsRepository,
) : GetHabitsUseCase {

    override suspend fun invoke(filter: HabitFilter): List<Habit> {
        return when (filter) {
            HabitFilter.ALL -> habitsRepository.getAllHabits()
            HabitFilter.LAST_7_DAYS -> habitsRepository.getHabitsInLastDays(7)
            HabitFilter.LAST_30_DAYS -> habitsRepository.getHabitsInLastDays(30)
        }
    }
}
