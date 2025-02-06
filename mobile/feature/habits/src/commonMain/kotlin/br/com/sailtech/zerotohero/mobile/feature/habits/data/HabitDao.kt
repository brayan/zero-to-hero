package br.com.sailtech.zerotohero.mobile.feature.habits.data

import br.com.sailtech.zerotohero.mobile.feature.habits.database.ZeroToHeroDatabase
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

internal class HabitDao(private val db: ZeroToHeroDatabase) {

    suspend fun insertHabit(name: String): Long = withContext(Dispatchers.IO) {
        db.zeroToHeroDatabaseQueries.insertHabit(name)
        db.zeroToHeroDatabaseQueries.lastInsertRowId().executeAsOne()
    }

    suspend fun getAllHabits(): List<Habit> = withContext(Dispatchers.IO) {
        db.zeroToHeroDatabaseQueries.getAllHabits().executeAsList().map {
            Habit(it.id, it.name, it.points.toInt(), getHabitCompletions(it.id))
        }
    }

    suspend fun addCompletion(habitId: Long, date: LocalDate) = withContext(Dispatchers.IO) {
        db.zeroToHeroDatabaseQueries.insertCompletion(habitId, date.toString(), 1)
    }

    suspend fun getHabitCompletions(habitId: Long): Map<LocalDate, Int> = withContext(Dispatchers.IO) {
        db.zeroToHeroDatabaseQueries.getHabitCompletionHistory(habitId).executeAsList()
            .associate { LocalDate.parse(it.date) to it.count.toInt() }
    }
}
