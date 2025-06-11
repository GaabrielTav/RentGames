package dados

import PlanoEntity
import modelo.Plano
import javax.persistence.EntityManager

/**
 * Data Access Object (DAO) para operações com a hierarquia de Planos.
 * Implementa as operações CRUD básicas e conversão entre modelos/entidades.
 */
class PlanosDAO(manager: EntityManager) : DAO<Plano, PlanoEntity>(manager, PlanoEntity::class.java) {

    /**
     * Converte o modelo de domínio Plano para entidade JPA PlanoEntity.
     * Delega a conversão específica para cada subclasse de Plano.
     *
     * @param plano Modelo a ser persistido (PlanoAvulso ou PlanoAssinatura)
     * @return Entidade JPA correspondente
     */
    override fun toEntity(plano: Plano): PlanoEntity {
        return plano.toEntity()
    }

    /**
     * Converte a entidade JPA PlanoEntity para modelo de domínio.
     * Preserva o tipo específico (assinatura/avulso) durante a conversão.
     *
     * @param entity Entidade a ser convertida
     * @return Modelo correspondente com todos os atributos específicos
     */
    override fun toModel(entity: PlanoEntity): Plano {
        return entity.toModel()
    }

    /**
     * Busca planos por tipo (Opcional - método específico para planos)
     * @param tipo "Avulso" ou "Assinatura"
     * @return Lista de planos do tipo especificado
     */
    fun buscarPorTipo(tipo: String): List<Plano> {
        val query = manager.createQuery(
            "FROM PlanoEntity WHERE tipo = :tipo",
            PlanoEntity::class.java
        )
        query.setParameter("tipo", tipo)
        return query.resultList.map { toModel(it) }
    }
}