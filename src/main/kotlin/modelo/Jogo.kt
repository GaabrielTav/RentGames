package modelo

import com.google.gson.annotations.Expose

/**
 * Data class representing a game in the system.
 * Implements [Recomendavel] for rating functionality.
 *
 * @property titulo Game title (exposed for JSON serialization)
 * @property capa Game cover image URL/path (exposed for JSON serialization)
 */
data class Jogo(
    @Expose val titulo: String,
    @Expose val capa: String
) : Recomendavel {

    /** Optional game description */
    var descricao: String? = null

    /** Current game price (default 0.0) */
    var preco: Double = 0.0

    /** Database ID (0 when not persisted) */
    var id: Int = 0

    /** Internal list of ratings */
    private val listaNotas = mutableListOf<Int>()

    /** Calculated average rating */
    override val media: Double
        get() = listaNotas.average()

    /**
     * Adds a rating to the game
     * @param nota Rating value (typically 1-5)
     */
    override fun recomendar(nota: Int) {
        require(nota in 1..5) { "Rating must be between 1-5" }
        listaNotas.add(nota)
    }

    /**
     * Secondary constructor for full game initialization
     * @param titulo Game title
     * @param capa Cover image
     * @param preco Game price
     * @param descricao Game description
     * @param id Database ID (default 0)
     */
    constructor(
        titulo: String,
        capa: String,
        preco: Double,
        descricao: String?,
        id: Int = 0
    ) : this(titulo, capa) {
        require(preco >= 0) { "Price can't be negative" }
        this.preco = preco
        this.descricao = descricao
        this.id = id
    }

    /**
     * Formatted string representation of the game
     * @return Multiline string with game details
     */
    override fun toString(): String {
        return """
            Meu Jogo:
            Título: $titulo
            Capa: $capa
            Descrição: ${descricao ?: "N/A"}
            Preço: ${"%.2f".format(preco)}
            Reputação: ${"%.1f".format(media)}
            ID: $id
        """.trimIndent()
    }
}