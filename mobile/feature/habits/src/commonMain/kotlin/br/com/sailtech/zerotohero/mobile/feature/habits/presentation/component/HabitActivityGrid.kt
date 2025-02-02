package br.com.sailtech.zerotohero.mobile.feature.habits.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.model.HabitFilter
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun HabitActivityGrid(completions: Map<LocalDate, Int>, filter: HabitFilter) {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    // Step 1: Calculate the start date based on the selected filter (e.g., 365, 30, 7 days ago)
    val originalStartDate = today.minus(DatePeriod(days = filter.days - 1))

    // Step 2: Align the first row so that it starts on Monday
    var startDate = originalStartDate
    while (startDate.dayOfWeek != DayOfWeek.MONDAY) {
        startDate = startDate.minus(DatePeriod(days = 1)) // Move back to Monday
    }

    // Step 3: Generate exactly `filterDays` of completion data
    val completionData = (0 until filter.days).map { daysAgo ->
        val date = originalStartDate.plus(DatePeriod(days = daysAgo))
        completions[date] ?: 0
    }

    // Step 4: Add blank placeholders before the first tracked date to align the first row
    val firstMondayOffset = startDate.daysUntil(originalStartDate)
    val paddedCompletion = List(firstMondayOffset) { null } + completionData

    // Step 5: Split into 7-day weeks
    val dailyCompletion = paddedCompletion.chunked(7)

    val listState = rememberLazyListState()

    LaunchedEffect(dailyCompletion.flatten().size, filter) {
        if (dailyCompletion.flatten().isNotEmpty()) {
            listState.scrollToItem(dailyCompletion.size - 1) // Scroll to last week
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        itemsIndexed(dailyCompletion) { _, week ->
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                week.forEach { count ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(getCompletionColor(count), RoundedCornerShape(2.dp))
                    )
                }
            }
        }
    }
}

// Function to determine color intensity based on habit completion frequency
fun getCompletionColor(count: Int?): Color {
    return when {
        count == null -> Color.Transparent // Empty placeholders for alignment
        count == 0 -> Color.LightGray.copy(alpha = 0.3f) // No completion
        count == 1 -> Color(0xFF9BE9A8) // Light green
        count == 2 -> Color(0xFF40C463) // Medium green
        count == 3 -> Color(0xFF30A14E) // Darker green
        count >= 4 -> Color(0xFF216E39) // Darkest green (most frequent)
        else -> Color.LightGray
    }
}
