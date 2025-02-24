package repository;

import dao.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Cliente;
import model.Frete;
import util.JpaUtil;

import java.util.List;

public class FreteRepository extends BaseRepository<Frete> {

    public FreteRepository() {
        super(Frete.class);
    }

    public List<Frete> buscarPorCliente(Cliente cliente) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        List<Frete> resultado = null;

        try {
            TypedQuery<Frete> query = entityManager.createQuery(
                    "SELECT f FROM Frete f WHERE f.cliente = :cliente", Frete.class);
            query.setParameter("cliente", cliente);
            resultado = query.getResultList();
        } finally {
            entityManager.close();
        }

        return resultado;
    }
}
