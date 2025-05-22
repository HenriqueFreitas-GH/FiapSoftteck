package br.com.fiap.fiapsoftteck.model

enum class Humor(val nivel: Int, val descricao: String) {
    MUITO_MAL(1, "Muito Mal"),
    MAL(2, "Mal"),
    NEUTRO(3, "Neutro"),
    BEM(4, "Bem"),
    MUITO_BEM(5, "Muito Bem");

    companion object {
        fun fromNivel(nivel: Int): Humor? = values().find { it.nivel == nivel }
        fun descricaoDoNivel(nivel: Int): String = fromNivel(nivel)?.descricao ?: "Desconhecido"
    }
}