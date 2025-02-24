package service;

import model.Cidade;
import model.Frete;
import model.CategoriaFrete;
import model.Cliente;
import repository.FreteRepository;
import repository.DistanciaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class FreteService {

    private final FreteRepository freteRepository;
    private final DistanciaRepository distanciaRepository;

    public FreteService() {
        this.freteRepository = new FreteRepository();
        this.distanciaRepository = new DistanciaRepository();
    }

    public void registrarFrete(Frete frete) {
        if (frete == null) {
            throw new IllegalArgumentException("O frete não pode ser nulo.");
        }
        if (frete.getCidadeOrigem() == null || frete.getCidadeDestino() == null) {
            throw new IllegalArgumentException("As cidades de origem e destino devem ser informadas.");
        }
        if (frete.getCategoriaFrete() == null) {
            throw new IllegalArgumentException("A categoria do frete deve ser informada.");
        }
        if (frete.getCliente() == null) {
            throw new IllegalArgumentException("O cliente deve ser informado.");
        }
        if (frete.getVeiculo() == null) {
            throw new IllegalArgumentException("O veículo deve ser informado.");
        }

        BigDecimal valorFrete = calcularValorFrete(frete.getCidadeOrigem(), frete.getCidadeDestino(), frete.getCategoriaFrete());
        frete.setValorKmRodado(valorFrete);
        freteRepository.save(frete);
    }

    public BigDecimal calcularValorFrete(Cidade origem, Cidade destino, CategoriaFrete categoriaFrete) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("As cidades de origem e destino devem ser informadas.");
        }
        if (categoriaFrete == null) {
            throw new IllegalArgumentException("A categoria do frete deve ser informada.");
        }

        int distanciaKm = distanciaRepository.buscarDistanciaEntreCidades(origem, destino);
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("A distância entre as cidades deve ser maior que zero.");
        }

        BigDecimal taxa = BigDecimal.valueOf(categoriaFrete.getPercentualAdicional());
        BigDecimal valorBase = BigDecimal.valueOf(distanciaKm).multiply(BigDecimal.valueOf(2.0));
        BigDecimal adicional = valorBase.multiply(taxa.divide(BigDecimal.valueOf(100)));

        return valorBase.add(adicional);
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

    public Optional<Frete> buscarFretePorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do frete deve ser maior que zero.");
        }

        return freteRepository.findById(id);
    }
}
