package modelo

data class Jogo(val titulo:String,
                val capa:String): Recomendavel {
    var descricao: String? = null
    var preco = 0.0
    override val media: Double
        get() = TODO("Not yet implemented")

    constructor(titulo: String, capa: String, preco: Double, descricao: String):
            this(titulo, capa) {
        this.preco = preco
        this.descricao = descricao
    }
    override fun toString(): String {
        return "Meu Jogo: \n" +
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao" +
                "Preço: $preco"
    }


}