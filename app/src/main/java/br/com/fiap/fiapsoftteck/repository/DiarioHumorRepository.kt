package br.com.fiap.fiapsoftteck.repository

import br.com.fiap.fiapsoftteck.database.dao.DiarioHumorDao
import br.com.fiap.fiapsoftteck.model.DiarioHumor

class DiarioHumorRepository (private val dao: DiarioHumorDao){
    suspend fun salvar(diario: DiarioHumor)= dao.inserir(diario)
    suspend fun listar(usuarioId: Int) = dao.listarPorUsuario(usuarioId)
}