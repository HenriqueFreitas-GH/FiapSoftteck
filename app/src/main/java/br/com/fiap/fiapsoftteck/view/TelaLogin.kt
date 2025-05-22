package br.com.fiap.fiapsoftteck.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.fiapsoftteck.viewmodel.UsuarioViewModel


@Composable
fun TelaLogin(
    viewModel: UsuarioViewModel,
    onNavigateToCadastro: () -> Unit,
    onLoginSucesso: () ->Unit
) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }


    val usuario = viewModel.usuarioLogado
    val erro = viewModel.mensagemErro
    val carregando = viewModel.isLoading

    LaunchedEffect(usuario) {
        if (usuario != null) {
            onLoginSucesso()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.fazerLogin(email, senha)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }


        TextButton(onClick = onNavigateToCadastro) {
            Text("Não tem conta? Cadastre-se")
        }

        if (carregando) {
            Spacer(Modifier.height(16.dp))
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
        }

        erro?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
