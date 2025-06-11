package modelo

import java.util.Scanner
import kotlin.random.Random

/**
 * Classe que representa um jogador (Gamer) no sistema.
 * Implementa a interface Recomendavel para sistema de reputação.
 *
 * @property nome Nome completo do jogador (obrigatório)
 * @property email E-mail do jogador (validado automaticamente)
 */
data class Gamer(var nome: String, var email: String) : Recomendavel {
    // Informações opcionais
    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }

    // Identificação
    var id = 0  // ID público (banco de dados)
    var idInterno: String? = null  // ID interno gerado automaticamente
        private set

    // Relacionamentos
    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo?>()  // Histórico de buscas
    val jogosAlugados = mutableListOf<Aluguel>()  // Aluguéis ativos
    private val listaNotas = mutableListOf<Int>()  // Notas recebidas
    val jogosRecomendados = mutableListOf<Jogo>()  // Recomendações personalizadas

    // Média de reputação (implementação da interface Recomendavel)
    override val media: Double
        get() = listaNotas.average()

    /**
     * Adiciona uma nota de reputação ao jogador
     * @param nota Valor entre 1-5 para avaliação
     */
    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

    /**
     * Recomenda um jogo específico e avalia o jogador
     * @param jogo Jogo a ser recomendado
     * @param nota Avaliação do jogador (1-5)
     */
    fun recomendarJogo(jogo: Jogo, nota: Int) {
        jogo.recomendar(nota)
        jogosRecomendados.add(jogo)
    }

    // Construtor secundário para cadastro completo
    constructor(nome: String, email: String, dataNascimento: String?, usuario: String?, id: Int = 0) :
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        this.id = id
        criarIdInterno()
    }

    // Validações iniciais
    init {
        require(nome.isNotBlank()) { "Nome não pode estar em branco" }
        this.email = validarEmail()
    }

    /**
     * Gera representação textual formatada do jogador
     * @return String com todas informações relevantes
     */
    override fun toString(): String {
        return """Gamer:
            |Nome: $nome
            |Email: $email
            |Data Nascimento: ${dataNascimento ?: "Não informada"}
            |Usuário: ${usuario ?: "Não definido"}
            |ID Interno: ${idInterno ?: "Não gerado"}
            |Reputação: ${"%.1f".format(media)}
            |ID: $id
            |Plano: ${plano.tipo}""".trimMargin()
    }

    // Métodos privados
    private fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)
        idInterno = "$usuario#$tag"
    }

    private fun validarEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        require(regex.matches(email)) { "E-mail inválido" }
        return email
    }

    // Operações de aluguel
    fun alugaJogo(jogo: Jogo, periodo: Periodo): Aluguel {
        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)
        return aluguel
    }

    fun jogosDoMes(mes: Int): List<Jogo> {
        return jogosAlugados
            .filter { it.periodo.dataInicial.monthValue == mes }
            .map { it.jogo }
    }

    companion object {
        /**
         * Factory method para criação interativa de Gamer
         * @param leitura Scanner para entrada de dados
         * @return Nova instância de Gamer
         */
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro.")
            print("Digite seu nome: ")
            val nome = leitura.nextLine()

            print("Digite seu e-mail: ")
            val email = leitura.nextLine()

            print("Completar cadastro com usuário e data nascimento? (S/N): ")
            when (leitura.nextLine().uppercase()) {
                "S" -> {
                    print("Data de nascimento (DD/MM/AAAA): ")
                    val nascimento = leitura.nextLine()
                    print("Nome de usuário: ")
                    val usuario = leitura.nextLine()
                    return Gamer(nome, email, nascimento, usuario)
                }
                else -> return Gamer(nome, email)
            }
        }
    }
}