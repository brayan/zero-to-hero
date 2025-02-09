package br.com.sailtech.zerotohero.mobile.feature.habits.domain.model

enum class HabitFilter(val days: Int) {
    LAST_12_MONTHS(365),
    LAST_30_DAYS(30),
    LAST_7_DAYS(7)
}
