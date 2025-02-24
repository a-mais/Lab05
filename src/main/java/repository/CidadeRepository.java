package repository;

import dao.BaseRepository;
import model.Cidade;

public class CidadeRepository extends BaseRepository<Cidade> {

    public CidadeRepository() {
        super(Cidade.class);
    }
}
