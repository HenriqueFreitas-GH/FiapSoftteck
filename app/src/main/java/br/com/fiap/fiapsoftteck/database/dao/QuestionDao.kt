package br.com.fiap.fiapsoftteck.database.dao

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.fiap.fiapsoftteck.model.Question

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(question: Question)

    @Query("SELECT * FROM questions WHERE usuarioId = :usuarioId")
    suspend fun listarPorUsuario(usuarioId: Int): List<Question>

    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<Question>

    @Query("DELETE FROM questions WHERE usuarioId = :usuarioId")
    suspend fun deletarPorUsuario(usuarioId: Int)
}

