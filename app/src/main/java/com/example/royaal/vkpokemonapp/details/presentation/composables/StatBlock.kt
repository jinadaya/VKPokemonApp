package com.example.royaal.vkpokemonapp.details.presentation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.compose.AppTheme
import com.example.royaal.vkpokemonapp.R
import com.example.royaal.vkpokemonapp.models.presentation.UiStats

@Composable
fun StatBlock(
    modifier: Modifier = Modifier,
    stats: UiStats,
) {
    val statNames = stats.names
    val statValues = stats.statValues
    val statEfforts = stats.efforts
    val maxStat = statValues.maxBy { it.value }.value
    Column(
        modifier = modifier,
    ) {
        statNames.forEachIndexed { i, name ->
            StatRow(
                statName = statNames[i],
                statValue = statValues[name] ?: -1,
                statEffort = statEfforts[name] ?: -1,
                maxStatValue = maxStat
            )
        }
    }
}

@Composable
private fun StatRow(
    statName: String,
    statValue: Int,
    statEffort: Int,
    maxStatValue: Int,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val density = LocalDensity.current
    val actualValue = remember { Animatable(0.31f) }
    var statHeight by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = statValue) {
        actualValue.animateTo(
            statValue.toFloat() / maxStatValue.toFloat()
        )
    }
    if (statValue < 0 || statEffort < 0) return
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(0.8f)
                    .height((statHeight * 2f / density.density).dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterStart)
                        .width(
                            (screenWidth * actualValue.value) * 0.8f.dp
                        )
                        .height((statHeight * 1.8f / density.density).dp)
                        .background(
                            color = MaterialTheme.colorScheme.inversePrimary,
                            shape = RoundedCornerShape(7.dp)
                        )
                ) {
                    if (actualValue.value > 0.3f) {
                        Text(
                            text = "$statValue",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(4.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
                    .onPlaced {
                        statHeight = it.size.height
                    },
                text = statName,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.CenterEnd),
                text = stringResource(
                    id = R.string.ef_title,
                    statEffort
                )
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    AppTheme(
        dynamicColor = false
    ) {
        StatBlock(
            stats = UiStats(
                names = listOf("Stat1", "Stat2", "Stat3"),
                statValues = mapOf("Stat1" to 3, "Stat2" to 9),
                efforts = mapOf("Stat1" to 3, "Stat2" to 3, "Stat3" to 10)
            )
        )
    }
}