package com.griffith.restock.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.griffith.restock.R

@Composable
fun HomeScreen(padding: PaddingValues, onProductClick: (SampleProduct) -> Unit) {
    var selectedCategory by rememberSaveable { mutableStateOf<ProductCategory?>(null) }
    val visibleCategories = if (selectedCategory == null) {
        ProductCategory.entries.toList()
    } else {
        listOfNotNull(selectedCategory)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize().consumeWindowInsets(padding),
        contentPadding = pagePadding(padding),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            ScreenHeading(stringResource(R.string.home_title), stringResource(R.string.home_subtitle))
        }
        item { Spacer(Modifier.height(8.dp)) }
        item { InventorySummary() }
        item {
            Column(Modifier.padding(top = 16.dp, bottom = 4.dp)) {
                Text(stringResource(R.string.inventory_title), style = MaterialTheme.typography.titleLarge)
                Text(stringResource(R.string.sample_data), style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text(stringResource(R.string.all_items)) }
                    )
                }
                items(ProductCategory.entries) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(stringResource(category.label)) }
                    )
                }
            }
        }
        for (category in visibleCategories) {
            val categoryProducts = sampleProducts.filter { it.category == category }
            item(key = category.name) {
                Text(
                    stringResource(category.label),
                    modifier = Modifier.padding(top = 8.dp, bottom = 2.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            items(categoryProducts, key = { it.id }) { product ->
                ProductCard(product) { onProductClick(product) }
            }
        }
    }
}

@Composable
private fun InventorySummary() {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(stringResource(R.string.overview_label), style = MaterialTheme.typography.labelSmall)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StockStatus.entries.forEach { status ->
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            sampleProducts.count { it.status == status }.toString(),
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(stringResource(status.label), style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingScreen(padding: PaddingValues, onProductClick: (SampleProduct) -> Unit) {
    val shoppingProducts = sampleProducts.filter { it.status != StockStatus.IN_STOCK }
    LazyColumn(
        modifier = Modifier.fillMaxSize().consumeWindowInsets(padding),
        contentPadding = pagePadding(padding),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            ScreenHeading(stringResource(R.string.shopping_title), stringResource(R.string.shopping_subtitle))
        }
        item {
            Surface(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(Modifier.fillMaxWidth().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Icon(painterResource(R.drawable.ic_shopping), contentDescription = null, modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(stringResource(R.string.shopping_summary), style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(stringResource(R.string.shopping_explanation),
                        color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
        item {
            Text(stringResource(R.string.shopping_count, shoppingProducts.size),
                style = MaterialTheme.typography.titleMedium)
        }
        items(shoppingProducts, key = { it.id }) { product ->
            ProductCard(product) { onProductClick(product) }
        }
        item {
            Text(
                stringResource(R.string.shopping_preview),
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
