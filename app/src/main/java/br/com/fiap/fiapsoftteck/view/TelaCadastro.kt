package br.com.fiap.fiapsoftteck.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.fiapsoftteck.model.Usuario
import br.com.fiap.fiapsoftteck.viewmodel.UsuarioViewModel

@Composable
fun TelaCadastro(
    viewModel: UsuarioViewModel,
    onCadastroConcluido: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") }, modifier = Modifier.fillMaxWidth(), visualTransformation = PasswordVisualTransformation())
        CampoGenero(
            generoSelecionado = genero,
            onGeneroSelecionado = { genero = it }
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val novoUsuario = Usuario(
                    nome = nome,
                    email = email,
                    senha = senha,
                    genero = genero,
                )
                viewModel.cadastrarUsuario(novoUsuario) {
                    onCadastroConcluido()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoGenero(
    generoSelecionado: String,
    onGeneroSelecionado: (String) -> Unit
) {
    val opcoes = listOf("Masculino", "Feminino", "Outro")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = generoSelecionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Gênero") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor() // necessário para posicionamento correto
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opcoes.forEach { genero ->
                DropdownMenuItem(
                    text = { Text(genero) },
                    onClick = {
                        onGeneroSelecionado(genero)
                        expanded = false
                    }
                )
            }
        }
    }
}
