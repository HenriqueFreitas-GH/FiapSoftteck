package br.com.fiap.fiapsoftteck.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.fiapsoftteck.model.Humor
import br.com.fiap.fiapsoftteck.recursos.BotaoVoltarParaHome
import br.com.fiap.fiapsoftteck.recursos.DicasPersonalizadas
import br.com.fiap.fiapsoftteck.recursos.GraficoEvolucaoHumor
import br.com.fiap.fiapsoftteck.viewmodel.DiarioHumorViewModel

@Composable
fun TelaDiarioHumor(
    viewModel: DiarioHumorViewModel,
    usuarioId: Int,
    navController: NavController
) {
    val registros = viewModel.listaRegistros

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotaoVoltarParaHome(navController)

        Text("Como você está se sentindo hoje?", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val emojis = listOf("😞", "😕", "😐", "🙂", "😄")

            emojis.forEachIndexed { index, emoji ->
                Text(
                    text = emoji,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .clickable {
                            viewModel.salvarHumor(usuarioId, index + 1)
                        }
                        .padding(8.dp)
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        val ultimosRegistros = registros.takeLast(5)

        Text("Histórico de humor:", style = MaterialTheme.typography.titleMedium)

        ultimosRegistros.forEach {
            Text("${it.dataRegistro}: ${Humor.descricaoDoNivel(it.humor)}")
        }

        if (registros.isNotEmpty()) {
            GraficoEvolucaoHumor(registros)
            DicasPersonalizadas(registros)
        }
    }
}
