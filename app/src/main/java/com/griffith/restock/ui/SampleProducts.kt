package com.griffith.restock.ui

import androidx.annotation.StringRes
import com.griffith.restock.R

enum class StockStatus(@param:StringRes val label: Int) {
    IN_STOCK(R.string.in_stock),
    RUNNING_LOW(R.string.running_low),
    OUT_OF_STOCK(R.string.out_of_stock)
}

enum class ProductCategory(@param:StringRes val label: Int) {
    FRIDGE(R.string.fridge),
    PANTRY(R.string.pantry),
    HOUSEHOLD(R.string.household)
}

data class SampleProduct(
    val id: Int,
    val name: String,
    val category: ProductCategory,
    val status: StockStatus
)

// UI fixtures for the first milestone. No database or stock mutations yet.
val sampleProducts = listOf(
    SampleProduct(1, "Oat milk", ProductCategory.FRIDGE, StockStatus.RUNNING_LOW),
    SampleProduct(2, "Greek yogurt", ProductCategory.FRIDGE, StockStatus.IN_STOCK),
    SampleProduct(3, "Eggs", ProductCategory.FRIDGE, StockStatus.IN_STOCK),
    SampleProduct(4, "Pasta", ProductCategory.PANTRY, StockStatus.IN_STOCK),
    SampleProduct(5, "Coffee", ProductCategory.PANTRY, StockStatus.OUT_OF_STOCK),
    SampleProduct(6, "Olive oil", ProductCategory.PANTRY, StockStatus.RUNNING_LOW),
    SampleProduct(7, "Dish soap", ProductCategory.HOUSEHOLD, StockStatus.OUT_OF_STOCK),
    SampleProduct(8, "Laundry detergent", ProductCategory.HOUSEHOLD, StockStatus.IN_STOCK)
)
