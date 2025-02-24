package repository;

import dao.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Cidade;
import model.Distancia;
import util.JpaUtil;

import java.util.Optional;

public class DistanciaRepository extends BaseRepository<Distancia> {

    public DistanciaRepository() {
        super(Distancia.class);
    }

    public int buscarDistanciaEntreCidades(Cidade origem, Cidade destino) {
        EntityManager entityManager = JpaUtil.getEntityManager(); // Obtém o EntityManager da JpaUtil
        int distancia = 0;

        try {
            TypedQuery<Distancia> query = entityManager.createQuery(
                    "SELECT d FROM Distancia d WHERE d.cidadeOrigem = :origem AND d.cidadeDestino = :destino", Distancia.class);
            query.setParameter("origem", origem);
            query.setParameter("destino", destino);

            distancia = query.getResultList().stream()
                    .findFirst()
                    .map(Distancia::getQuilometros)
                    .orElse(0); // Retorna 0 se não encontrar
        } finally {
            entityManager.close(); // Fecha o EntityManager para evitar vazamento de conexão
        }

        return distancia;
    }

    public Optional<Distancia> findByCidades(Cidade cidadeOrigem, Cidade cidadeDestino) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            TypedQuery<Distancia> query = entityManager.createQuery(
                    "SELECT d FROM Distancia d WHERE d.cidadeOrigem = :origem AND d.cidadeDestino = :destino",
                    Distancia.class
            );
            query.setParameter("origem", cidadeOrigem);
            query.setParameter("destino", cidadeDestino);

            return query.getResultStream().findFirst(); // Retorna um Optional<Distancia>
        } finally {
            entityManager.close();
        }
    }
}
