package repository;

import dao.BaseRepository;
import model.CategoriaFrete;

public class CategoriaFreteRepository extends BaseRepository<CategoriaFrete> {

    public CategoriaFreteRepository() {
        super(CategoriaFrete.class);
    }
}
