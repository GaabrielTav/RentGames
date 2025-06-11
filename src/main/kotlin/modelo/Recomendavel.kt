package modelo

// Interface para classes que podem ser recomendadas/avaliadas com notas
interface Recomendavel {
    val media: Double  // Média atual das notas recebidas

    fun recomendar(nota: Int)  // Adiciona uma nova recomendação com nota de 0 a 10
}
