package modelo

/**
 * Data class representing game information in JSON format.
 * Used for serialization/deserialization of game data from external APIs or storage.
 *
 * @property titulo Game title
 * @property capa URL or path to the game cover image
 * @property preco Game price
 * @property descricao Game description
 */
data class InfoJogoJson(
    val titulo: String,
    val capa: String,
    val preco: Double,
    val descricao: String
) {
    /**
     * Validates the game information upon creation.
     * @throws IllegalArgumentException if any field violates business rules
     */
    init {
        require(titulo.isNotBlank()) { "Game title cannot be blank" }
        require(capa.isNotBlank()) { "Cover image URL cannot be blank" }
        require(preco >= 0) { "Price cannot be negative" }
    }

    /**
     * Converts this JSON DTO to a domain model [Jogo] entity.
     * @return New Jogo instance with equivalent properties
     */
    fun toJogoEntity(): Jogo {
        return Jogo(
            titulo = this.titulo,
            capa = this.capa,
            preco = this.preco,
            descricao = this.descricao
        )
    }
}