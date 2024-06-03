package com.example.massive.ui.screen.splash

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.massive.R
import com.example.massive.data.UserDataStore
import com.example.massive.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val isDarkMode = isSystemInDarkTheme()
    val scale = remember { Animatable(0f) }
    val userDataStore = UserDataStore(context)
    val loginStatusFlow = userDataStore.getStatusLogin
    val statusLoggedIn by loginStatusFlow.collectAsState(initial = false)
    val isFirstLaunch = userDataStore.getFirstLaunch.collectAsState(initial = true)
    
    LaunchedEffect(
        key1 = statusLoggedIn,
        block = {
            delay(500L)
            if (isFirstLaunch.value){
                navController.navigate(Screen.Onboarding1.route){
                    popUpTo(Screen.Splash.route){
                        inclusive = true
                    }
                }
                userDataStore.setFirstTimeLaunch(false)
            }
            else if (statusLoggedIn) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
            else {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
    )

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .scale(scale.value)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val image = if (isDarkMode) {
                R.drawable.logo2
            } else {
                R.drawable.logo2
            }
            Image(painter = painterResource(id = image), contentDescription = null)
        }
    }
}
