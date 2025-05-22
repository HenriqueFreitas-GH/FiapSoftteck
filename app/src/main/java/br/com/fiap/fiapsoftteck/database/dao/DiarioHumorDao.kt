package br.com.fiap.fiapsoftteck.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.fiap.fiapsoftteck.model.DiarioHumor

@Dao
interface DiarioHumorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(diario: DiarioHumor)

    @Query("Select * FROM diario_humor WHERE usuarioId = :usuarioId ORDER BY dataRegistro ASC")
    suspend fun listarPorUsuario(usuarioId: Int): List<DiarioHumor>
}