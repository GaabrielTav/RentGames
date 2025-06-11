package dados

import javax.persistence.EntityManager

/**
 * Classe abstrata base para operações DAO (Data Access Object).
 * Define operações CRUD básicas e conversão entre modelos e entidades.
 *
 * @param TModel Tipo do modelo de domínio
 * @param TEntity Tipo da entidade JPA
 * @property manager EntityManager para operações no banco
 * @property entityType Classe da entidade JPA
 */
abstract class DAO<TModel, TEntity>(
    protected val manager: EntityManager,
    protected val entityType: Class<TEntity>
) {
    /**
     * Converte modelo para entidade (persistência)
     */
    abstract fun toEntity(objeto: TModel): TEntity

    /**
     * Converte entidade para modelo (consulta)
     */
    abstract fun toModel(entity: TEntity): TModel

    /**
     * Recupera todos os registros como lista de modelos
     * @return List<TModel> Lista de modelos convertidos
     */
    open fun getLista(): List<TModel> {
        val query = manager.createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map { toModel(it) }
    }

    /**
     * Adiciona um novo registro no banco
     * @param objeto Modelo a ser persistido
     */
    open fun adicionar(objeto: TModel) {
        manager.transaction.begin()
        manager.persist(toEntity(objeto))
        manager.transaction.commit()
    }

    /**
     * Busca um registro por ID
     * @param id ID do registro a ser buscado
     * @return TModel Modelo convertido
     * @throws NoResultException Se nenhum registro for encontrado
     */
    open fun recuperarPeloId(id: Int): TModel {
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        return toModel(query.singleResult)
    }

    /**
     * Remove um registro do banco por ID
     * @param id ID do registro a ser removido
     * @throws NoResultException Se nenhum registro for encontrado
     */
    open fun apagar(id: Int) {
        manager.transaction.begin()
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        manager.remove(query.singleResult)
        manager.transaction.commit()
    }
}