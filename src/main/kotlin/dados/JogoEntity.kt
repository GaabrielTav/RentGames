package dados

import javax.persistence.*

/**
 * Entidade JPA que representa um jogo na base de dados.
 * Mapeada para a tabela "jogos" com informações básicas de jogos.
 */
@Entity
@Table(name = "jogos")
class JogoEntity(
    /** Título principal do jogo (obrigatório) */
    val titulo: String = "Título do jogo",

    /** URL ou caminho da imagem da capa (obrigatório) */
    val capa: String = "Capa do jogo",

    /** Preço atual do jogo (default 0.0) */
    val preco: Double = 0.0,

    /** Descrição detalhada do jogo (opcional) */
    val descricao: String? = null,

    /** ID único gerado automaticamente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
) {
    // Pode adicionar métodos auxiliares ou lógica de negócio aqui
}