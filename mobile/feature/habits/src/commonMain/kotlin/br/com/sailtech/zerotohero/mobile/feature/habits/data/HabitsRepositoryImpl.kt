package br.com.sailtech.zerotohero.mobile.feature.habits.data

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.repository.HabitsRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

internal class HabitsRepositoryImpl : HabitsRepository {

    private val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    private val completions = mapOf(
        today to 1,
        today.minus(DatePeriod(days = 2)) to 3,
        today.minus(DatePeriod(days = 10)) to 3,
        today.minus(DatePeriod(days = 30)) to 2,
        today.minus(DatePeriod(days = 60)) to 4,
        today.minus(DatePeriod(days = 90)) to 1
    )

    override suspend fun getAllHabits(): List<Habit> {
        return listOf(
            Habit(1, "Exercise", 20, completions),
            Habit(2, "Read a Book", 15, completions),
            Habit(3, "Meditate", 10, completions),
            Habit(4, "Drink Water", 25, completions),
            Habit(5, "Write Journal", 5, completions)
        )
    }

    override suspend fun getHabitsInLastDays(days: Int): List<Habit> {
        return listOf(
            Habit(1, "Exercise", 20, completions),
            Habit(2, "Read a Book", 15, completions),
            Habit(3, "Meditate", 10, completions),
            Habit(4, "Drink Water", 25, completions),
            Habit(5, "Write Journal", 5, completions)
        )
    }
}
