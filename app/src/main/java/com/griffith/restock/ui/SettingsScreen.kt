package com.griffith.restock.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.griffith.restock.R

@Composable
fun SettingsScreen(padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().consumeWindowInsets(padding),
        contentPadding = pagePadding(padding),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            ScreenHeading(stringResource(R.string.settings_title), stringResource(R.string.settings_subtitle))
        }
        item {
            PlannedSetting(
                section = stringResource(R.string.shortcuts),
                title = stringResource(R.string.shake_title),
                description = stringResource(R.string.shake_description)
            )
        }
        item {
            PlannedSetting(
                section = stringResource(R.string.reminders),
                title = stringResource(R.string.expiry_title),
                description = stringResource(R.string.expiry_description)
            )
        }
        item {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(painterResource(R.drawable.ic_home), contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(stringResource(R.string.about_title), style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(stringResource(R.string.about_description),
                        color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
        item {
            Text(stringResource(R.string.prototype_note), style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun PlannedSetting(section: String, title: String, description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(section, style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                    Text(stringResource(R.string.coming_soon), style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary)
                }
                Text(description, style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
