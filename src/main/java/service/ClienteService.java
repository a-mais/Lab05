package service;

import model.Cliente;
import model.Frete;
import repository.FreteRepository;

import java.util.List;

public class ClienteService {

    FreteRepository freteRepository;

    public ClienteService() {
        this.freteRepository = new FreteRepository();
    }

    public List<Frete> listarFretesPorCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente deve ser informado.");
        }
        if (cliente.getId() == 0) {
            throw new IllegalArgumentException("O ID do cliente é inválido.");
        }

        return freteRepository.buscarPorCliente(cliente);
    }
}
