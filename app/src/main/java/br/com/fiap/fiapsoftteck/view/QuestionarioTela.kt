package br.com.fiap.fiapsoftteck.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.fiapsoftteck.model.Question
import br.com.fiap.fiapsoftteck.recursos.BotaoVoltarParaHome
import br.com.fiap.fiapsoftteck.viewmodel.QuestionViewModel

@Composable
fun ShowPsychosocialQuestionnaire(
    usuarioId: Int,
    viewModel: QuestionViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val perguntas = listOf(
        "Você se sente estressado no trabalho?",
        "Sua carga de trabalho é equilibrada?",
        "Você se sente apoiado por seus colegas?",
        "Você tem tempo suficiente para descansar?",
        "Avalie sua satisfação no trabalho (1 a 10):"
    )
    val respostas = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        BotaoVoltarParaHome(navController)

        perguntas.forEachIndexed { index, pergunta ->
            Text(text = pergunta)
            if (pergunta.contains("Avalie")) {
                var nota by remember { mutableStateOf(5f) }
                Slider(
                    value = nota,
                    onValueChange = { nota = it },
                    valueRange = 1f..10f,
                    steps = 8
                )
                if (respostas.size > index) respostas[index] = nota.toInt().toString()
                else respostas.add(nota.toInt().toString())
            } else {
                var selected by remember { mutableStateOf<String?>(null) }
                Row {
                    RadioButton(
                        selected = selected == "Sim",
                        onClick = {
                            selected = "Sim"
                            if (respostas.size > index) respostas[index] = "Sim"
                            else respostas.add("Sim")
                        }
                    )
                    Text("Sim")
                    Spacer(Modifier.width(8.dp))
                    RadioButton(
                        selected = selected == "Não",
                        onClick = {
                            selected = "Não"
                            if (respostas.size > index) respostas[index] = "Não"
                            else respostas.add("Não")
                        }
                    )
                    Text("Não")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = {
            val lista = perguntas.indices.map { idx ->
                Question(
                    questionText = perguntas[idx],
                    answer = respostas.getOrNull(idx) ?: "",
                    usuarioId = usuarioId // certifique-se de ter o usuárioId disponível aqui
                )
            }
            viewModel.submitAnswers(usuarioId, lista)
            Toast.makeText(context, "Respostas enviadas!", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Enviar Respostas")
        }
    }
}

