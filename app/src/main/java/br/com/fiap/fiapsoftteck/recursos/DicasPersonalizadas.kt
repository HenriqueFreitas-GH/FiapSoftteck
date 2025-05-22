package br.com.fiap.fiapsoftteck.recursos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.fiapsoftteck.model.DiarioHumor

@Composable
fun DicasPersonalizadas(registros: List<DiarioHumor>) {
    if (registros.isEmpty()) return

    val ultimos = registros.takeLast(5)
    val media = ultimos.map { it.humor }.average()

    val mensagem = when {
        media <= 2 -> "Você tem se sentido para baixo ultimamente. Tente reservar um tempo para si mesmo, conversar com alguém de confiança ou buscar apoio profissional."
        media in 2.1..3.5 -> "Seu humor está oscilando. Talvez praticar exercícios físicos, meditação ou uma pausa na rotina possa ajudar."
        else -> "Você está mantendo um bom nível de humor! Continue com os hábitos positivos que vêm funcionando para você."
    }

    Spacer(modifier = Modifier.height(24.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Dica de Bem-Estar", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(mensagem)
        }
    }
}
