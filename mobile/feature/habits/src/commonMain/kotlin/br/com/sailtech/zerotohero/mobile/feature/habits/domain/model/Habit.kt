package br.com.sailtech.zerotohero.mobile.feature.habits.domain.model

import kotlinx.datetime.LocalDate

data class Habit(
    val id: Long,
    val name: String,
    val points: Int,
    val completions: Map<LocalDate, Int>
)
