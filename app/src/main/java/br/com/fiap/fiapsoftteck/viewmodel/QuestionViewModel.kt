package br.com.fiap.fiapsoftteck.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.fiapsoftteck.api.RetrofitClient
import br.com.fiap.fiapsoftteck.database.AppDatabase
import br.com.fiap.fiapsoftteck.database.dao.QuestionDao
import br.com.fiap.fiapsoftteck.model.Question
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).questionDao()

    fun submitAnswers(usuarioId: Int, lista: List<Question>) {
        viewModelScope.launch {
            lista.forEach {
                dao.inserir(it.copy(usuarioId = usuarioId)) // Garante que cada item tem o ID do usuário
            }
        }
    }

    suspend fun carregarRespostas(usuarioId: Int): List<Question> {
        return dao.listarPorUsuario(usuarioId)
    }
}