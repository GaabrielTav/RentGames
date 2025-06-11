package dados

import modelo.Aluguel
import javax.persistence.EntityManager

/**
 * DAO para operações de banco de dados relacionadas a Aluguel.
 * Converte entre modelos de domínio (Aluguel) e entidades JPA (AluguelEntity).
 */
class AluguelDAO(manager: EntityManager): DAO<Aluguel, AluguelEntity>(manager, AluguelEntity::class.java) {

    // Converte modelo Aluguel para entidade JPA (persistência)
    override fun toEntity(objeto: Aluguel): AluguelEntity {
        return AluguelEntity(objeto.gamer.toEntity(), objeto.jogo.toEntity(), objeto.periodo)
            .apply {
                valorDoAluguel = objeto.valorDoAluguel
                id = objeto.id
            }
    }

    // Converte entidade JPA para modelo Aluguel (consulta)
    override fun toModel(entity: AluguelEntity): Aluguel {
        return Aluguel(entity.gamer.toModel(), entity.jogo.toModel(), entity.periodo)
            .apply {
                id = entity.id
            }
    }
}