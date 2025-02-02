package br.com.sailtech.zerotohero.mobile.feature.habits.domain.usecase

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.repository.HabitsRepository

internal class GetHabitsUseCaseImpl(
    private val habitsRepository: HabitsRepository,
) : GetHabitsUseCase {

    override suspend fun invoke(filter: HabitFilter): List<Habit> {
        return habitsRepository.getHabitsInLastDays(filter.days)
    }
}
