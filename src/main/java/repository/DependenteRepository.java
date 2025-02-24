package repository;

import dao.BaseRepository;
import model.Dependente;

public class DependenteRepository extends BaseRepository<Dependente> {

    public DependenteRepository() {
        super(Dependente.class);
    }
}
