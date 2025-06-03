package principal
import com.google.gson.Gson
import modelo.Gamer
import modelo.InfoJogo
import modelo.Jogo
import servicos.ConsumoApi
import transformarEmIdade

import java.util.Scanner


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val leitura = Scanner(System.`in`)
    val gamer= Gamer.criarGamer(leitura)
    println("Cadastro concluido, dados:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite: \n")
        val busca = leitura.nextLine()


        val buscaApi = ConsumoApi()
        val infomacaoJogo = buscaApi.buscaJogo(busca)



        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                infomacaoJogo.info.title,
                infomacaoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Jogo Inexistente!")
        }
        resultado.onSuccess {
            println("Deseja inserir descrição? (S/N)")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            }else{
                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Nova busca? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)
    println("Busca completa.")


}