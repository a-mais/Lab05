package repository;

import dao.BaseRepository;
import model.Cliente;

public class ClienteRepository extends BaseRepository<Cliente> {

    public ClienteRepository() {
        super(Cliente.class);
    }
}
