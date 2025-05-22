package br.com.fiap.fiapsoftteck.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.fiapsoftteck.R
import br.com.fiap.fiapsoftteck.recursos.BotaoComIcone
import br.com.fiap.fiapsoftteck.viewmodel.UsuarioViewModel

@Composable
fun TelaHome(
    viewModel: UsuarioViewModel,
    onNavigateToHumor: () -> Unit,
    navController: NavController
) {
    val usuario = viewModel.usuarioLogado

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo, ${usuario?.nome ?: "Usuário"}!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Card de Ações
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Menu Rápido", style = MaterialTheme.typography.titleMedium)

                BotaoComIcone(
                    texto = "Registrar Humor",
                    painterResource(id= R.drawable.mood),
                    onClick = onNavigateToHumor
                )

                BotaoComIcone(
                    texto = "Questionário Psicossocial",
                    painterResource(id = R.drawable.assessment),
                    onClick = { navController.navigate("questionario") }
                )

                BotaoComIcone(
                    texto = "Canais de Apoio",
                    painterResource(id = R.drawable.supportagent),
                    onClick = { navController.navigate("apoio") }
                )
            }
        }
    }
}
