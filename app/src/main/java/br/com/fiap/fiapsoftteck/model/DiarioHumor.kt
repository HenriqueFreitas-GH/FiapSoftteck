package br.com.fiap.fiapsoftteck.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "diario_humor",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id"],
        childColumns = ["usuarioId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["usuarioId"]), Index(value = ["dataRegistro"])]
)
data class DiarioHumor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuarioId: Int,
    val humor: Int, // ENUM como String: BEM, MAL, etc.
    val dataRegistro: String = LocalDate.now().toString()
)