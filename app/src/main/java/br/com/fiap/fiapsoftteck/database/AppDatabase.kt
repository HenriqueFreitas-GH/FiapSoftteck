package br.com.fiap.fiapsoftteck.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.fiap.fiapsoftteck.database.dao.DiarioHumorDao
import br.com.fiap.fiapsoftteck.database.dao.QuestionDao
import br.com.fiap.fiapsoftteck.database.dao.UsuarioDao
import br.com.fiap.fiapsoftteck.model.DiarioHumor
import br.com.fiap.fiapsoftteck.model.Question
import br.com.fiap.fiapsoftteck.model.Usuario

@Database(entities = [Usuario::class, DiarioHumor::class, Question::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun diarioHumorDao(): DiarioHumorDao
    abstract fun questionDao(): QuestionDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // MIGRATION exemplo: você pode editar ou adicionar mais conforme precisar
        private val MIGRATION_1_2 = object : Migration( 2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Criação da nova tabela para diario_humor
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS question (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        usuarioId INTEGER NOT NULL,
                        questionText TEXT NOT NULL,
                        answer TEXT NOT NULL
                    )
                """.trimIndent())
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()


                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}