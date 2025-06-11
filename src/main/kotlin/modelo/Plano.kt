package modelo


// Classe base para diferentes tipos de plano de aluguel
sealed class Plano(val tipo: String, var id: Int = 0) {

    // Calcula o valor do aluguel com base na duração e no preço do jogo
    open fun obterValor(aluguel: Aluguel): Double {
        return aluguel.jogo.preco * aluguel.periodo.emDias
    }
}
