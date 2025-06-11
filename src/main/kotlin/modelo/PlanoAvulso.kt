package modelo

import java.math.BigDecimal

// Representa um plano onde o gamer paga por cada aluguel (sem mensalidade)
class PlanoAvulso(
    tipo: String, id: Int = 0
) : Plano(tipo, id) {

    // Calcula o valor do aluguel com desconto se reputação for alta
    override fun obterValor(aluguel: Aluguel): Double {
        var valorOriginal = super.obterValor(aluguel)
        if (aluguel.gamer.media > 8) {
            valorOriginal -= valorOriginal * 0.1 // Aplica 10% de desconto
        }
        return valorOriginal
    }

    // Representação textual do plano
    override fun toString(): String {
        return "Plano Avulso\n" +
                "Tipo: $tipo\n" +
                "Id: $id\n"
    }
}
