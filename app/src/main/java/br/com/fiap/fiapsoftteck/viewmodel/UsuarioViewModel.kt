package br.com.fiap.fiapsoftteck.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.fiapsoftteck.model.Usuario
import br.com.fiap.fiapsoftteck.repository.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel (
    private val repository: UsuarioRepository
) : ViewModel() {

    var usuarioLogado by mutableStateOf<Usuario?>(null)
        private set

    var mensagemErro by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun fazerLogin( email: String, senha: String){
        viewModelScope.launch {
            isLoading = true
            val usuario = repository.buscarUsuarioPorEmail(email)
            isLoading = false
            if (usuario != null){
                if(usuario.senha == senha) {
                    usuarioLogado = usuario
                    mensagemErro = null

                }else{
                    mensagemErro = "Senha incorreta"
                }
                }else{
                    mensagemErro = "Usuário não encontrado"
            }

            }
        }

    fun cadastrarUsuario(usuario: Usuario, onSuccess: (Int) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            val id = repository.inserirUsuario(usuario).toInt()
            isLoading = false
            onSuccess(id)
        }
    }



}

  //  fun cadastrarUsuario(usuario: Usuario, onSuccess: (Int) -> Unit){
    //    viewModelScope.launch {
      //      isLoading = true
        //    val id = repository.InserirUsuario(usuario).toInt()
            //isLoading = false
          //  onSuccess(id)
        //}
//}



