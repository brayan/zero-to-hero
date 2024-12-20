package br.com.sailtech.zerotohero.mobile.data

import br.com.sailtech.zerotohero.mobile.domain.model.Habit
import br.com.sailtech.zerotohero.mobile.domain.repository.HabitsRepository

internal class HabitsRepositoryImpl : HabitsRepository {

    override suspend fun getAllHabits(): List<Habit> {
        return listOf(
            Habit(1, "Exercise", 20),
            Habit(2, "Read a Book", 15),
            Habit(3, "Meditate", 10),
            Habit(4, "Drink Water", 25),
            Habit(5, "Write Journal", 5)
        )
    }

    override suspend fun getHabitsInLastDays(days: Int): List<Habit> {
        return listOf(
            Habit(1, "Exercise", 20),
            Habit(2, "Read a Book", 15),
            Habit(3, "Meditate", 10),
            Habit(4, "Drink Water", 25),
            Habit(5, "Write Journal", 5)
        )
    }
}