package com.griffith.restock.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.griffith.restock.R

@Composable
fun ScreenHeading(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.headlineLarge)
        Text(subtitle, style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun StatusBadge(status: StockStatus) {
    val colors = MaterialTheme.colorScheme
    val backgroundColor = when (status) {
        StockStatus.IN_STOCK -> colors.primaryContainer
        StockStatus.RUNNING_LOW -> colors.tertiaryContainer
        StockStatus.OUT_OF_STOCK -> colors.errorContainer
    }
    val textColor = when (status) {
        StockStatus.IN_STOCK -> colors.onPrimaryContainer
        StockStatus.RUNNING_LOW -> colors.onTertiaryContainer
        StockStatus.OUT_OF_STOCK -> colors.onErrorContainer
    }

    Surface(color = backgroundColor, contentColor = textColor, shape = RoundedCornerShape(8.dp)) {
        Text(
            stringResource(status.label),
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun ProductCard(product: SampleProduct, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(shape = RoundedCornerShape(14.dp), color = MaterialTheme.colorScheme.surfaceContainer) {
                Icon(
                    painterResource(R.drawable.ic_product), contentDescription = null,
                    modifier = Modifier.padding(12.dp).size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(product.name, style = MaterialTheme.typography.titleMedium)
                StatusBadge(product.status)
            }
            Icon(painterResource(R.drawable.ic_next), contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(18.dp))
        }
    }
}

// Add space around the list and below its last item for the Add product button.
@Composable
fun pagePadding(insets: PaddingValues): PaddingValues {
    val direction = LocalLayoutDirection.current
    return PaddingValues(
        start = insets.calculateStartPadding(direction) + 24.dp,
        end = insets.calculateEndPadding(direction) + 24.dp,
        top = insets.calculateTopPadding() + 16.dp,
        bottom = insets.calculateBottomPadding() + 96.dp
    )
}
