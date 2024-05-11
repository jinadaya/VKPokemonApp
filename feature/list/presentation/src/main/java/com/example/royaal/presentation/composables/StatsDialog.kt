package com.example.royaal.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.royaal.common.R
import com.example.royaal.presentation.models.UiListStats

@Composable
internal fun StatsDialog(
    modifier: Modifier = Modifier,
    default: String?,
    stats: UiListStats,
    name: String,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier, shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.W800
                    ),
                )
                AsyncImage(
                    modifier = Modifier,
                    model = default,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.pokemon_placeholder),
                    error = painterResource(id = R.drawable.pokemon_placeholder),
                )
                ListStatsBlock(
                    stats = stats
                )
            }
        }
    }
}