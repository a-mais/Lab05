package tests;

import model.*;
import repository.*;
import service.ClienteService;
import service.FreteService;

import java.math.BigDecimal;
import java.util.List;

public class ClienteTeste {
    public static void main(String[] args) {
        ClienteRepository clienteRepository = new ClienteRepository();
        CidadeRepository cidadeRepository = new CidadeRepository();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        CategoriaFreteRepository categoriaFreteRepository = new CategoriaFreteRepository();
        FreteRepository freteRepository = new FreteRepository();
        FreteService freteService = new FreteService();
        ClienteService clienteService = new ClienteService();

        // Criar ou buscar Cliente
        Cliente cliente = clienteRepository.findById(1).orElseGet(() -> {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome("João Carlos");
            novoCliente.setCpf("123.456.789-00");
            novoCliente.setContato("joao.carlos@gmail.com");
            novoCliente.setTelefone("11988887777");
            novoCliente.setAtivo(true);
            clienteRepository.save(novoCliente);
            return novoCliente;
        });

        // Criar ou buscar Cidades
        Cidade cidadeOrigem = cidadeRepository.findById(1).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("São Paulo");
            novaCidade.setUf("SP");
            novaCidade.setEstado("São Paulo");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        Cidade cidadeDestino = cidadeRepository.findById(2).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("Rio de Janeiro");
            novaCidade.setUf("RJ");
            novaCidade.setEstado("Rio de Janeiro");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        // Criar ou buscar Categoria de Frete
        CategoriaFrete categoriaFrete = categoriaFreteRepository.findById(1).orElseGet(() -> {
            CategoriaFrete novaCategoria = new CategoriaFrete();
            novaCategoria.setNome("SEDEX");
            novaCategoria.setDescricao("Entrega rápida");
            novaCategoria.setPercentualAdicional(30.0F);
            categoriaFreteRepository.save(novaCategoria);
            return novaCategoria;
        });

        // Criar ou buscar Veículo
        Veiculo veiculo = veiculoRepository.findById(1).orElseGet(() -> {
            Veiculo novoVeiculo = new Veiculo();
            novoVeiculo.setNumeroPlaca("ABC-1234");
            veiculoRepository.save(novoVeiculo);
            return novoVeiculo;
        });

        // Criar e registrar 4 fretes para o cliente
        for (int i = 1; i <= 4; i++) {
            Frete frete = new Frete();
            frete.setCliente(cliente);
            frete.setCidadeOrigem(cidadeOrigem);
            frete.setCidadeDestino(cidadeDestino);
            frete.setVeiculo(veiculo);
            frete.setCategoriaFrete(categoriaFrete);
            frete.setCodigo(i);
            frete.setNumeroNotaFiscal(1000 + i);
            frete.setValorKmRodado(new BigDecimal("50.00"));

            freteService.registrarFrete(frete);
        }

        // Buscar e listar os fretes do cliente
        List<Frete> fretes = clienteService.listarFretesPorCliente(cliente);

        System.out.println("=== FRETES REGISTRADOS PARA O CLIENTE ===");
        for (Frete frete : fretes) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Código do Frete: " + frete.getCodigo());
            System.out.println("Nota Fiscal: " + frete.getNumeroNotaFiscal());
            System.out.println("Valor por Km Rodado: R$ " + frete.getValorKmRodado());

            System.out.println("Origem: " + frete.getCidadeOrigem().getNome() + " - " + frete.getCidadeOrigem().getUf());
            System.out.println("Destino: " + frete.getCidadeDestino().getNome() + " - " + frete.getCidadeDestino().getUf());

            System.out.println("Categoria do Frete: " + frete.getCategoriaFrete().getNome() + " - " +
                    frete.getCategoriaFrete().getDescricao());

            System.out.println("Veículo: " + frete.getVeiculo().getNumeroPlaca());

            System.out.println("Cliente: " + frete.getCliente().getNome() + " | CPF: " + frete.getCliente().getCpf());
            System.out.println("-----------------------------------------------------");
        }
    }
}
