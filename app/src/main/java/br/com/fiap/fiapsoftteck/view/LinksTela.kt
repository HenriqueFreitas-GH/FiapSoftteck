package br.com.fiap.fiapsoftteck.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.fiapsoftteck.recursos.BotaoVoltarParaHome

@Composable
fun ShowSupportLinks(navController: NavController) {
    val context = LocalContext.current
    val links = listOf(
        "CVV - Centro de Valorização da Vida" to "https://www.cvv.org.br",
        "Ministério da Saúde - Saúde Mental" to "https://www.gov.br/saude/pt-br/assuntos/saude-de-a-a-z/s/saude-mental",
        "Cartilha Saúde Emocional (PDF)" to "https://www.gov.br/pt-br/cartilha-saude-emocional.pdf",
        "Portal Bem-Estar Softtek (Mock)" to "https://www.softtek.com/bem-estar"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        BotaoVoltarParaHome(navController)

        Text("Canais de Apoio à Saúde Mental", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        links.forEach { (nome, url) ->
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(nome)
            }
        }
    }
}

