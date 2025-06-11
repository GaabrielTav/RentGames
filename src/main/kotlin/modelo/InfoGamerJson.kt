package modelo


// Representa as informações de um gamer em formato JSON, usado em APIs
data class InfoGamerJson(
    val nome: String,             // Nome completo do gamer
    val email: String,            // E-mail do gamer
    val dataNascimento: String,   // Data de nascimento (formato texto)
    val usuario: String           // Nome de usuário no sistema
)

