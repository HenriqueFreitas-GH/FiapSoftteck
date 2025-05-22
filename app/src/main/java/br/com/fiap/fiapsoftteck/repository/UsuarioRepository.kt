package br.com.fiap.fiapsoftteck.repository

import br.com.fiap.fiapsoftteck.database.dao.UsuarioDao
import br.com.fiap.fiapsoftteck.model.Usuario

class UsuarioRepository(private val dao: UsuarioDao) {

    suspend fun inserirUsuario(usuario: Usuario): Long {
        return dao.inserir(usuario)
    }

    suspend fun buscarUsuarioPorEmail(email: String): Usuario? {
        return dao.buscarPorEmail(email)
    }

    suspend fun buscarPorId(id: Int): Usuario?{
        return dao.buscarPorId(id)
    }
}