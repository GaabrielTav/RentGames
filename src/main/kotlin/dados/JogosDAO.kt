package dados

import modelo.Jogo
import javax.persistence.EntityManager

/**
 * Data Access Object (DAO) para operações com a entidade Jogo.
 * Implementa as operações básicas de CRUD herdadas da classe DAO genérica.
 *
 * @param manager EntityManager para operações de persistência
 */
class JogosDAO(manager: EntityManager) : DAO<Jogo, JogoEntity>(manager, JogoEntity::class.java) {

    /**
     * Converte o modelo de domínio Jogo para entidade JPA JogoEntity.
     * Delega a conversão para o método toEntity() do próprio modelo.
     *
     * @param objeto Modelo Jogo a ser convertido
     * @return JogoEntity equivalente para persistência
     */
    override fun toEntity(objeto: Jogo): JogoEntity {
        return objeto.toEntity()
    }

    /**
     * Converte a entidade JPA JogoEntity para modelo de domínio Jogo.
     * Delega a conversão para o método toModel() da própria entidade.
     *
     * @param entity Entidade JogoEntity a ser convertida
     * @return Modelo Jogo equivalente
     */
    override fun toModel(entity: JogoEntity): Jogo {
        return entity.toModel()
    }
}