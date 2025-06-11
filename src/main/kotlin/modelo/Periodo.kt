package modelo

import java.time.LocalDate
import java.time.Period
import javax.persistence.Embeddable

// Classe embutida que representa um período entre duas datas
@Embeddable
data class Periodo(
    val dataInicial: LocalDate = LocalDate.now(),         // Data de início (padrão: hoje)
    val dataFinal: LocalDate = LocalDate.now().plusDays(7) // Data de fim (padrão: +7 dias)
) {
    // Duração do período em dias
    val emDias = Period.between(dataInicial, dataFinal).days
}

