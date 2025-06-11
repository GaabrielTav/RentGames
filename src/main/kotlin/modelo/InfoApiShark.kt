package modelo

// Representa os dados básicos de um item retornado pela API do Shark
data class InfoApiShark(
    val title: String,  // Título ou nome do item
    val thumb: String   // URL da imagem em miniatura (thumbnail)
)

