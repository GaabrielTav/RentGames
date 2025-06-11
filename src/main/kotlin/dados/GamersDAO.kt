package dados

import modelo.Gamer
import javax.persistence.EntityManager


/**
 * Data Access Object (DAO) para operações com a entidade Gamer.
 * Implementa as operações básicas de CRUD herdadas da classe DAO.
 *
 * @param manager EntityManager para operações no banco de dados
 */
class GamersDAO(manager: EntityManager) : DAO<Gamer, GamerEntity>(manager, GamerEntity::class.java) {

    /**
     * Converte um objeto Gamer (modelo) para GamerEntity (entidade JPA).
     * @param objeto Modelo Gamer a ser convertido
     * @return GamerEntity equivalente para persistência
     */
    override fun toEntity(objeto: Gamer): GamerEntity {
        return objeto.toEntity()
    }

    /**
     * Converte uma GamerEntity (entidade JPA) para Gamer (modelo).
     * Inclui a conversão do plano associado.
     * @param entity Entidade Gamer a ser convertida
     * @return Modelo Gamer equivalente
     */
    override fun toModel(entity: GamerEntity): Gamer {
        return entity.toModel().apply {
            plano = entity.plano.toModel()
        }
    }
}