package com.example.royaal.vkpokemonapp.common_ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.royaal.vkpokemonapp.R

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: () -> Unit,
    onTryAgain: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(10)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = onTryAgain) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}