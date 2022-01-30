package com.udea.alerta.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udea.alerta.composables.Menu
import com.udea.alerta.screen.*
import com.udea.alerta.ui.theme.ColorBackground

sealed class Screen(val ruta:String){
    object MAIN: Screen("ALERTA")
    object GUARDIANES: Screen("GUARDIANES")
    object GUARDIAN: Screen("GUARDIAN")
    object AYUDA: Screen("AYUDA")
    object ENCUESTA: Screen("TEST")
}


@Composable
fun LayoutMain() {
    val navController = rememberNavController()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorBackground)
    ) {
        Menu(navController)
        NavHost(navController =navController, startDestination = Screen.MAIN.ruta){
            composable(Screen.MAIN.ruta){ ScreenMain()}
            composable(Screen.GUARDIANES.ruta){ ScreenGuardianes(navController) }
            composable(Screen.GUARDIAN.ruta){ ScreenGuardian() }
            composable(Screen.AYUDA.ruta){ ScreenAyuda() }
            composable(Screen.ENCUESTA.ruta){ ScreenEncuesta() }

        }
    }
}