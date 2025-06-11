package modelo

import java.time.LocalDate
import java.time.Period

/**
 * Modelo de domínio que representa um aluguel de jogo.
 * Calcula automaticamente o valor com base no plano do gamer.
 *
 * @property gamer Gamer que realizou o aluguel
 * @property jogo Jogo alugado
 * @property periodo Período de aluguel (data início/fim)
 * @property valorDoAluguel Valor calculado automaticamente pelo plano do gamer
 * @property id Identificador único (0 para novos aluguéis)
 */
data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo
) {
    /**
     * Valor calculado do aluguel baseado no plano do gamer.
     * Atualizado automaticamente quando qualquer propriedade muda.
     */
    val valorDoAluguel: Double = gamer.plano.obterValor(this)

    /**
     * ID do aluguel (0 indica que ainda não foi persistido)
     */
    var id: Int = 0

    /**
     * Representação textual amigável do aluguel
     * @return String formatada com informações principais
     */
    override fun toString(): String {
        return "Aluguel do jogo '${jogo.titulo}' por ${gamer.nome} " +
                "por R$${"%.2f".format(valorDoAluguel)} " +
                "(Período: ${periodo.inicio} a ${periodo.fim})"
    }
}