package br.com.fiap.fiapsoftteck.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashTela(onTimeout: () -> Unit) {
    LaunchedEffect(true) {
        delay(3000) // espera 3 segundos
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF007BFF)), // cor de fundo
        contentAlignment = Alignment.Center
    ) {
        Text("Bem-vindo ao App", color = Color.White, fontSize = 24.sp)
        // ou coloque um logo aqui
    }
}
