package repository;

import dao.BaseRepository;
import model.PessoaFisica;

public class PessoaFisicaRepository extends BaseRepository<PessoaFisica> {

    public PessoaFisicaRepository() {
        super(PessoaFisica.class);
    }
}
