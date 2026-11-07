package com.griffith.restock.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.griffith.restock.R
import com.griffith.restock.ui.theme.RestockTheme

private enum class Screen(val title: Int, val icon: Int) {
    HOME(R.string.home, R.drawable.ic_home),
    SHOPPING(R.string.shopping, R.drawable.ic_shopping),
    SETTINGS(R.string.settings, R.drawable.ic_settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestockApp() {
    var currentScreen by rememberSaveable { mutableStateOf(Screen.HOME) }
    var showProductForm by rememberSaveable { mutableStateOf(false) }
    // A null ID means the form is for a new product.
    var selectedProductId by rememberSaveable { mutableStateOf<Int?>(null) }

    // Keep each tab's scroll position and filter when switching tabs.
    val screenState = rememberSaveableStateHolder()
    val addProductLabel = stringResource(R.string.add_product)

    val title = when {
        !showProductForm -> R.string.app_name
        selectedProductId == null -> R.string.add_product
        else -> R.string.product_details
    }

    BackHandler(enabled = showProductForm || currentScreen != Screen.HOME) {
        if (showProductForm) {
            showProductForm = false
        } else {
            currentScreen = Screen.HOME
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(title), style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    if (showProductForm) {
                        IconButton(onClick = { showProductForm = false }) {
                            Icon(painterResource(R.drawable.ic_back), contentDescription = stringResource(R.string.back))
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            if (!showProductForm) {
                NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainerLow) {
                    for (screen in Screen.entries) {
                        NavigationBarItem(
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            icon = { Icon(painterResource(screen.icon), contentDescription = null) },
                            label = { Text(stringResource(screen.title)) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (!showProductForm && currentScreen == Screen.HOME) {
                ExtendedFloatingActionButton(
                    onClick = {
                        selectedProductId = null
                        showProductForm = true
                    },
                    modifier = Modifier.semantics { contentDescription = addProductLabel },
                    icon = { Icon(painterResource(R.drawable.ic_add), contentDescription = null) },
                    text = { Text(addProductLabel) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { padding ->
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            // Keep the content readable on wider screens.
            Box(Modifier.widthIn(max = 680.dp).fillMaxSize()) {
                if (showProductForm) {
                    val selectedProduct = sampleProducts.find { it.id == selectedProductId }
                    key(selectedProductId) {
                        ProductDetailsScreen(product = selectedProduct, padding = padding)
                    }
                } else {
                    screenState.SaveableStateProvider(currentScreen.name) {
                        when (currentScreen) {
                            Screen.HOME -> HomeScreen(padding) { product ->
                                selectedProductId = product.id
                                showProductForm = true
                            }
                            Screen.SHOPPING -> ShoppingScreen(padding) { product ->
                                selectedProductId = product.id
                                showProductForm = true
                            }
                            Screen.SETTINGS -> SettingsScreen(padding)
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Home · light", showBackground = true, widthDp = 393, heightDp = 852)
@Preview(name = "Home · dark", showBackground = true, widthDp = 393, heightDp = 852,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RestockPreview() {
    RestockTheme { RestockApp() }
}
