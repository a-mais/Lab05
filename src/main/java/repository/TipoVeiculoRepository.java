package repository;

import dao.BaseRepository;
import model.TipoVeiculo;

public class TipoVeiculoRepository extends BaseRepository<TipoVeiculo> {

    public TipoVeiculoRepository() {
        super(TipoVeiculo.class);
    }
}
