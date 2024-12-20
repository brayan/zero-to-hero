package br.com.sailtech.zerotohero.mobile.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HabitsSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(6) {
            HabitSkeletonItem()
        }
    }
}

@Composable
fun HabitSkeletonItem() {
    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f)
    )

    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val shimmerTranslate = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        ),
        label = "ShimmerTransition",
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .shimmerEffect(shimmerTranslate.value, shimmerColor)
    )
}

fun Modifier.shimmerEffect(
    translate: Float,
    colors: List<Color>
): Modifier = this.background(
    brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
        colors,
        startX = translate - 300f,
        endX = translate + 300f
    )
)
