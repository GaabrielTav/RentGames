package dados

import modelo.Periodo
import javax.persistence.*

/**
 * Entidade JPA que mapeia a tabela de aluguéis no banco de dados.
 * Armazena informações sobre aluguéis de jogos por gamers.
 */
@Entity
@Table(name = "aluguel")
class AluguelEntity(
    // Gamer que realizou o aluguel (relacionamento N:1)
    @ManyToOne
    val gamer: GamerEntity = GamerEntity(),

    // Jogo alugado (relacionamento N:1)
    @ManyToOne
    val jogo: JogoEntity = JogoEntity(),

    // Período de aluguel (data início/fim - valor embutido)
    @Embedded
    val periodo: Periodo = Periodo()
) {
    // Valor total do aluguel
    var valorDoAluguel = 0.0

    // ID único gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0
}