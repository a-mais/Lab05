package repository;

import dao.BaseRepository;
import model.Filial;

public class FilialRepository extends BaseRepository<Filial> {

    public FilialRepository() {
        super(Filial.class);
    }
}
