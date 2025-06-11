import javax.persistence.*

/**
 * Classe base abstrata para hierarquia de planos (Single Table Inheritance).
 * Todas as subclasses são armazenadas na mesma tabela 'planos' com discriminador.
 */
@Entity
@Table(name = "planos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoPlano", discriminatorType = DiscriminatorType.STRING)
sealed class PlanoEntity(
    /** Tipo do plano (usado para discriminação) */
    val tipo: String,

    /** ID único gerado automaticamente */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
)

/**
 * Entidade para plano avulso (pay-as-you-go).
 * Representa cobrança por aluguel individual.
 */
@Entity
@DiscriminatorValue("Avulso")
class PlanoAvulsoEntity(
    tipo: String = "Plano Avulso",
    id: Int = 0
) : PlanoEntity(tipo, id)

/**
 * Entidade para plano de assinatura (subscription).
 * Inclui benefícios como jogos incluídos e descontos.
 */
@Entity
@DiscriminatorValue("Assinatura")
class PlanoAssinaturaEntity(
    tipo: String = "Plano Assinatura",

    /** Valor mensal da assinatura */
    val mensalidade: Double = 0.0,

    /** Quantidade de jogos incluídos mensalmente */
    val jogosIncluidos: Int = 0,

    /** Desconto (%) baseado na reputação do gamer */
    val percentualDescontoReputacao: Double = 0.0,

    id: Int = 0
) : PlanoEntity(tipo, id)