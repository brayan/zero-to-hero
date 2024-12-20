package br.com.sailtech.zerotohero.mobile.domain.repository

import br.com.sailtech.zerotohero.mobile.domain.model.Habit

internal interface HabitsRepository {
    suspend fun getAllHabits(): List<Habit>
    suspend fun getHabitsInLastDays(days: Int): List<Habit>
}
