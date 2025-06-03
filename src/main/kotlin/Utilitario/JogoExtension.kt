import modelo.Jogo
import modelo.InfoJogoJson

fun InfoJogoJson.criaJogo(): Jogo {
    return Jogo(this.titulo, this.capa, this.preco, this.descricao)
}