package br.com.sailtech.zerotohero.mobile.feature.habits.domain.repository

import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.Habit


internal interface HabitsRepository {
    suspend fun getAllHabits(): List<Habit>
    suspend fun getHabitsInLastDays(days: Int): List<Habit>
}
