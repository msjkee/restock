package com.griffith.restock.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.griffith.restock.ui.theme.RestockTheme

// Android Studio previews for checking each screen without using the emulator.
@Preview(showBackground = true, widthDp = 393, heightDp = 852)
@Composable
fun ShoppingPreview() {
    RestockTheme {
        Surface(Modifier.fillMaxSize()) {
            ShoppingScreen(PaddingValues(), onProductClick = {})
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 852)
@Composable
fun ProductPreview() {
    RestockTheme {
        Surface(Modifier.fillMaxSize()) {
            ProductDetailsScreen(sampleProducts.first(), PaddingValues())
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 852)
@Composable
fun SettingsPreview() {
    RestockTheme {
        Surface(Modifier.fillMaxSize()) {
            SettingsScreen(PaddingValues())
        }
    }
}
