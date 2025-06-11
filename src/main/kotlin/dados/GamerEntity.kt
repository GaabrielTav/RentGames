package dados

import javax.persistence.*

/**
 * Entidade JPA que representa um gamer no banco de dados.
 * Mapeada para a tabela "gamers" com relacionamento ManyToOne com PlanoEntity.
 */
@Entity
@Table(name = "gamers")
class GamerEntity(
    /** ID único gerado automaticamente pelo banco de dados */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    /** Nome completo do gamer */
    val nome: String = "Nome do Gamer",

    /** E-mail de contato do gamer */
    val email: String = "email@email.com",

    /** Data de nascimento no formato String (opcional) */
    val dataNascimento: String? = null,

    /** Nome de usuário único (opcional) */
    val usuario: String? = null,

    /** Plano associado ao gamer (relacionamento N:1) */
    @ManyToOne
    val plano: PlanoEntity = PlanoAvulsoEntity()
) {
    // Pode adicionar métodos de negócio ou utilitários aqui
}