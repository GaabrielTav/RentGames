package modelo

import java.math.BigDecimal
import java.math.RoundingMode

// Representa um plano de assinatura mensal com benefícios e descontos
class PlanoAssinatura(
    tipo: String,
    val mensalidade: Double,                  // Valor mensal do plano
    val jogosIncluidos: Int,                  // Quantos jogos o gamer pode alugar por mês sem custo
    val percentualDescontoReputacao: Double,  // Desconto aplicado se a reputação for alta (>8)
    id: Int = 0
) : Plano(tipo, id) {

    // Calcula o valor do aluguel com base nas regras do plano
    override fun obterValor(aluguel: Aluguel): Double {
        val totalJogosNoMes = aluguel.gamer.jogosDoMes(aluguel.periodo.dataInicial.monthValue).size + 1

        return if (totalJogosNoMes <= jogosIncluidos) {
            0.0 // Ainda dentro do limite de jogos gratuitos
        } else {
            var valorOriginal = super.obterValor(aluguel)
            if (aluguel.gamer.media > 8) {
                valorOriginal -= valorOriginal * percentualDescontoReputacao // Aplica desconto
            }
            valorOriginal
        }
    }

    // Representação textual do plano
    override fun toString(): String {
        return "Plano Assinatura\n" +
                "Tipo: $tipo\n" +
                "Id: $id\n" +
                "Mensalidade: $mensalidade\n" +
                "Jogos Incluidos: $jogosIncluidos\n" +
                "Percentual Desconto Reputacao: $percentualDescontoReputacao\n"
    }
}
