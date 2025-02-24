package repository;

import dao.BaseRepository;
import model.Funcionario;

public class FuncionarioRepository extends BaseRepository<Funcionario> {

    public FuncionarioRepository() {
        super(Funcionario.class);
    }
}
