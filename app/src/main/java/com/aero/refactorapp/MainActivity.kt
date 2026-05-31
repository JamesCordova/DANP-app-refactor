package com.aero.refactorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.aero.refactorapp.ui.navigation.AppNavigation
import com.aero.refactorapp.ui.theme.AppThemeMode
import com.aero.refactorapp.ui.theme.RefactorAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentTheme by remember {
                mutableStateOf(AppThemeMode.BLUE)
            }
            RefactorAppTheme (
                themeMode = currentTheme
            ) {
                AppNavigation {
                    currentTheme = when(it) {
                        "GREEN" -> AppThemeMode.GREEN
                        "PURPLE" -> AppThemeMode.PURPLE
                        "BLACK" -> AppThemeMode.BLACK
                        else -> AppThemeMode.BLUE
                    }
                }
            }
        }
    }
}