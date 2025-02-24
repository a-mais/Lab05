package repository;

import dao.BaseRepository;
import model.Veiculo;

public class VeiculoRepository extends BaseRepository<Veiculo> {

    public VeiculoRepository() {
        super(Veiculo.class);
    }
}
