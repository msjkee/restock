package com.griffith.restock.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.griffith.restock.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductDetailsScreen(product: SampleProduct?, padding: PaddingValues) {
    var name by rememberSaveable { mutableStateOf(product?.name.orEmpty()) }
    var category by rememberSaveable { mutableStateOf(product?.category ?: ProductCategory.PANTRY) }
    var status by rememberSaveable { mutableStateOf(product?.status ?: StockStatus.IN_STOCK) }

    // The Scaffold includes safeDrawing (including IME); do not add imePadding again.
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(padding)
            .consumeWindowInsets(padding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(stringResource(R.string.product_intro), style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.product_name)) },
            placeholder = { Text(stringResource(R.string.product_name_hint)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(R.string.category), style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ProductCategory.entries.forEach { option ->
                    FilterChip(
                        selected = category == option,
                        onClick = { category = option },
                        label = { Text(stringResource(option.label)) }
                    )
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(R.string.stock_status), style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StockStatus.entries.forEach { option ->
                    FilterChip(
                        selected = status == option,
                        onClick = { status = option },
                        label = { Text(stringResource(option.label)) }
                    )
                }
            }
        }
        Text(
            stringResource(R.string.form_preview),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(onClick = {}, enabled = false, modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)) {
            Text(stringResource(R.string.save_product))
        }
    }
}
