package dados


import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


/**
 * Objeto singleton que fornece acesso ao EntityManager para operações com o banco de dados.
 * Centraliza a criação do EntityManagerFactory e EntityManager para a unidade de persistência "alugames".
 */
object Banco {
    /**
     * Cria e retorna uma instância de EntityManager configurada.
     * @return EntityManager pronto para operações de banco de dados
     */
    fun getEntityManager(): EntityManager {
        val factory: EntityManagerFactory = Persistence.createEntityManagerFactory("alugames")
        return factory.createEntityManager()
    }
}