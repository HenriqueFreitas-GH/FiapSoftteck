package br.com.fiap.fiapsoftteck.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.fiapsoftteck.model.DiarioHumor
import br.com.fiap.fiapsoftteck.model.Humor
import br.com.fiap.fiapsoftteck.repository.DiarioHumorRepository
import kotlinx.coroutines.launch

class DiarioHumorViewModel(private val repository: DiarioHumorRepository): ViewModel() {
    var listaRegistros by mutableStateOf<List<DiarioHumor>>(emptyList())
        private set

    fun salvarHumor(usuarioId: Int, humor: Int) {
        viewModelScope.launch {
            repository.salvar(DiarioHumor(usuarioId = usuarioId, humor = humor))
            listarHumor(usuarioId)
        }
    }

    fun listarHumor(usuarioId: Int) {
        viewModelScope.launch {
            listaRegistros = repository.listar(usuarioId)
        }
    }

    fun humorParaTexto(nivel: Int): String {
        return Humor.fromNivel(nivel)?.descricao ?: "Desconhecido"
    }

}