package ifpb.pos.ativ2.soap.repositories;

import ifpb.pos.ativ2.soap.entities.ReservaPassagem;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.PersistenceContext;

/**
 * @author natarajan
 */
@DataSourceDefinition(
        name = "java:app/jdbc/passagem",
        className = "org.postgresql.Driver",
//        url = "jdbc:postgresql://localhost:5432/agencia",
        url = "jdbc:postgresql://banco-agencia:5432/agencia",
        user = "postgres",
        password = "12345"
)

@Stateless
public class ReservaPassagemRepository {

    @PersistenceContext
    private EntityManager em;

    public ReservaPassagem salvar(ReservaPassagem reservaPassagem ) {
        em.persist(reservaPassagem);
        return reservaPassagem ;

    }

    public ReservaPassagem remover(Long key) {
        ReservaPassagem reservaPassagem  = this.buscar(key);
        em.remove(reservaPassagem);
        return reservaPassagem ;
    }

    public ReservaPassagem atualizar(ReservaPassagem reservaPassagem) {
        return em.merge(reservaPassagem);
    }

    public ReservaPassagem buscar(Long key) {
        return em.find(ReservaPassagem.class, key);
    }

    public List<ReservaPassagem> listar() {
        return em.createQuery("FROM ReservaPassagem h", ReservaPassagem.class)
                .getResultList();
    }
}
