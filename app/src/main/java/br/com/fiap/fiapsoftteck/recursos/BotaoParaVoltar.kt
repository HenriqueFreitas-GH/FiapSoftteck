package br.com.fiap.fiapsoftteck.recursos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController

@Composable
fun BotaoVoltarParaHome(navController: NavController) {
    Box(
        modifier = Modifier.padding(8.dp),
        contentAlignment = Alignment.TopStart
    ) {
        IconButton(onClick = { navController.navigate("home") }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar para Home",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
