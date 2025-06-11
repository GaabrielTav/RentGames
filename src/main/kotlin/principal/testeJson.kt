import com.google.gson.GsonBuilder
import modelo.Periodo
import modelo.PlanoAssinatura
import servicos.ConsumoApi
import java.io.File
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val listaJogosJson = consumo.buscaJogosJson()
    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogosJson.get(10)
    val jogoSpider = listaJogosJson.get(13)
    val jogoTheLastOfUs = listaJogosJson.get(2)
    val jogoDandara = listaJogosJson.get(5)
    val jogoAssassins = listaJogosJson.get(4)
    val jogoCyber = listaJogosJson.get(6)
    val jogoGod = listaJogosJson.get(7)
    val jogoSkyrim = listaJogosJson.get(18)

    gamerCaroline.recomendarJogo(jogoResidentVillage, 7)
    gamerCaroline.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCaroline.recomendarJogo(jogoAssassins, 8)
    gamerCaroline.recomendarJogo(jogoCyber, 7)
    gamerCaroline.recomendarJogo(jogoGod, 10)
    gamerCaroline.recomendarJogo(jogoDandara, 8)
    gamerCaroline.recomendarJogo(jogoSkyrim, 8)
    gamerCaroline.recomendarJogo(jogoSpider, 6)

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializacao = gson.toJson(gamerCaroline.jogosRecomendados)
    println(serializacao)

    val arquivo = File("jogosRecomendados-${gamerCaroline.nome}.json")
    arquivo.writeText(serializacao)
    println(arquivo.absolutePath)
}
