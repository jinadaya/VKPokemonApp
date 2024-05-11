package com.example.royaal.presentation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.example.royaal.presentation.models.UiListStats

@Composable
internal fun ListStatsBlock(
    modifier: Modifier = Modifier,
    stats: UiListStats,
) {
    val maxValue = stats.values.values.max()
    Column(
        modifier = modifier,
    ) {
        stats.names.fastForEach { statName ->
            StatRow(
                Modifier,
                statName,
                stats.values[statName] ?: -1,
                maxValue,
            )
        }
    }
}

@Composable
private fun StatRow(
    modifier: Modifier = Modifier,
    name: String,
    value: Int,
    maxValue: Int,
) {
    if (value < 0) return
    val currValue = remember { Animatable(0f) }
    LaunchedEffect(key1 = value) {
        currValue.animateTo(
            (value.toFloat() / maxValue.toFloat()),
            spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }
    Card(
        modifier = modifier.fillMaxWidth().padding(vertical = 2.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = name,
            style = MaterialTheme.typography.labelMedium
        )
        Box(
            modifier = Modifier
                .height(12.dp)
                .padding(4.dp)
                .fillMaxWidth(currValue.value)
                .background(
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
        )
    }
}