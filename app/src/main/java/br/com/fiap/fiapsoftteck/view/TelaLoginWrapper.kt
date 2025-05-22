package br.com.fiap.fiapsoftteck.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.fiapsoftteck.AppNavigation
import br.com.fiap.fiapsoftteck.database.AppDatabase
import br.com.fiap.fiapsoftteck.repository.UsuarioRepository
import br.com.fiap.fiapsoftteck.viewmodel.UsuarioViewModel

@Composable
fun TelaLoginWrapper() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val usuarioDao = remember { db.usuarioDao() }
    val repository = remember { UsuarioRepository(usuarioDao) }
    val viewModel = remember { UsuarioViewModel(repository) }


    AppNavigation(viewModel)
}
