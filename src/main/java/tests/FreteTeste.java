package tests;

import model.*;
import repository.*;
import service.FreteService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class FreteTeste {
    public static void main(String[] args) {
        FreteService freteService = new FreteService();
        CidadeRepository cidadeRepository = new CidadeRepository();
        CategoriaFreteRepository categoriaFreteRepository = new CategoriaFreteRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        FilialRepository filialRepository = new FilialRepository();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
        TipoVeiculoRepository tipoVeiculoRepository = new TipoVeiculoRepository();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        DistanciaRepository distanciaRepository = new DistanciaRepository();

        System.out.println("=== TESTE: REGISTRAR FRETE ===");
        testarRegistrarFrete(freteService, cidadeRepository, categoriaFreteRepository, clienteRepository,
                filialRepository, funcionarioRepository, tipoVeiculoRepository, veiculoRepository, distanciaRepository);

        System.out.println("=== TESTE: CALCULAR VALOR FRETE ===");
        testarCalcularValorFrete(freteService, cidadeRepository, categoriaFreteRepository, distanciaRepository);

        System.out.println("=== TESTE: BUSCAR FRETE POR ID ===");
        testarBuscarFretePorId(freteService);
    }

    private static void testarRegistrarFrete(FreteService freteService,
                                             CidadeRepository cidadeRepository,
                                             CategoriaFreteRepository categoriaFreteRepository,
                                             ClienteRepository clienteRepository,
                                             FilialRepository filialRepository,
                                             FuncionarioRepository funcionarioRepository,
                                             TipoVeiculoRepository tipoVeiculoRepository,
                                             VeiculoRepository veiculoRepository,
                                             DistanciaRepository distanciaRepository) {
        // Buscar ou criar Cidade de origem
        Cidade cidadeOrigem = cidadeRepository.findById(1).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("São Paulo");
            novaCidade.setUf("SP");
            novaCidade.setEstado("São Paulo");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        // Buscar ou criar Cidade de destino
        Cidade cidadeDestino = cidadeRepository.findById(2).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("Rio de Janeiro");
            novaCidade.setUf("RJ");
            novaCidade.setEstado("Rio de Janeiro");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        // Buscar ou criar CategoriaFrete
        CategoriaFrete categoriaFrete = categoriaFreteRepository.findById(1).orElseGet(() -> {
            CategoriaFrete novaCategoria = new CategoriaFrete();
            novaCategoria.setNome("SEDEX");
            novaCategoria.setDescricao("Entrega Super Rápida");
            novaCategoria.setPercentualAdicional(30.0F);
            categoriaFreteRepository.save(novaCategoria);
            return novaCategoria;
        });

        // Criar Cliente
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

        // Buscar ou criar Filial
        Filial filial = filialRepository.findById(1).orElseGet(() -> {
            Filial novaFilial = new Filial();
            novaFilial.setNome("Filial São Paulo");
            novaFilial.setEndereco("Rua XPTO, 123");
            novaFilial.setTelefone("1199999999");
            filialRepository.save(novaFilial);
            return novaFilial;
        });

        // Criar Funcionario
        Funcionario funcionario = funcionarioRepository.findById(1).orElseGet(() -> {
            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.setNome("Carlos Silva");
            novoFuncionario.setCpf("987.654.321-00");
            novoFuncionario.setTelefone("11977776666");
            novoFuncionario.setMatricula(321);
            novoFuncionario.setFilial(filial);
            funcionarioRepository.save(novoFuncionario);
            return novoFuncionario;
        });

        // Criar Dependente (associado ao funcionário)
        Dependente dependente = new Dependente();
        dependente.setNome("Pedro");
        dependente.setDataNascimento(LocalDate.of(2015, 6, 10));
        dependente.setFuncionario(funcionario);

        // Buscar ou criar TipoVeiculo
        TipoVeiculo tipoVeiculo = tipoVeiculoRepository.findById(1).orElseGet(() -> {
            TipoVeiculo novoTipo = new TipoVeiculo();
            novoTipo.setDescricao("Caminhão");
            novoTipo.setPesoMaximo(10000.0F);
            tipoVeiculoRepository.save(novoTipo);
            return novoTipo;
        });

        // Buscar ou criar Veiculo
        Veiculo veiculo = veiculoRepository.findById(1).orElseGet(() -> {
            Veiculo novoVeiculo = new Veiculo();
            novoVeiculo.setNumeroPlaca("ABC-1234");
            novoVeiculo.setFilial(filial);
            novoVeiculo.setTipoVeiculo(tipoVeiculo);
            veiculoRepository.save(novoVeiculo);
            return novoVeiculo;
        });

        // Buscar ou criar Distancia entre as cidades
        Distancia distancia = distanciaRepository.findByCidades(cidadeOrigem, cidadeDestino)
                .orElseGet(() -> {
                    Distancia novaDistancia = new Distancia();
                    novaDistancia.setCidadeOrigem(cidadeOrigem);
                    novaDistancia.setCidadeDestino(cidadeDestino);
                    novaDistancia.setQuilometros(431); // Exemplo de distância
                    distanciaRepository.save(novaDistancia);
                    return novaDistancia;
                });

        // Criando o Frete
        Frete frete = new Frete();
        frete.setCidadeOrigem(cidadeOrigem);
        frete.setCidadeDestino(cidadeDestino);
        frete.setCategoriaFrete(categoriaFrete);
        frete.setCliente(cliente);
        frete.setVeiculo(veiculo);
        frete.setCodigo(1);
        frete.setNumeroNotaFiscal(1001);

        // Criando Itens do Frete
        ItemFrete item1 = new ItemFrete();
        item1.setDescricao("Caixa de Eletrônicos");
        item1.setPeso(50.0F);
        item1.setFrete(frete);

        ItemFrete item2 = new ItemFrete();
        item2.setDescricao("Móveis");
        item2.setPeso(120.0F);
        item2.setFrete(frete);

        // Registrando o frete
        freteService.registrarFrete(frete);
        System.out.println("Frete registrado com sucesso! Valor do frete: R$" + frete.getValorKmRodado());
    }

    private static void testarCalcularValorFrete(FreteService freteService,
                                                 CidadeRepository cidadeRepository,
                                                 CategoriaFreteRepository categoriaFreteRepository,
                                                 DistanciaRepository distanciaRepository) {
        // Buscar ou criar Cidade de origem
        Cidade cidadeOrigem = cidadeRepository.findById(1).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("São Paulo");
            novaCidade.setUf("SP");
            novaCidade.setEstado("São Paulo");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        // Buscar ou criar Cidade de destino
        Cidade cidadeDestino = cidadeRepository.findById(2).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome("Rio de Janeiro");
            novaCidade.setUf("RJ");
            novaCidade.setEstado("Rio de Janeiro");
            cidadeRepository.save(novaCidade);
            return novaCidade;
        });

        // Buscar ou criar CategoriaFrete
        CategoriaFrete categoriaFrete = categoriaFreteRepository.findById(1).orElseGet(() -> {
            CategoriaFrete novaCategoria = new CategoriaFrete();
            novaCategoria.setNome("SEDEX");
            novaCategoria.setDescricao("Entrega Super Rápida");
            novaCategoria.setPercentualAdicional(30.0F);
            categoriaFreteRepository.save(novaCategoria);
            return novaCategoria;
        });

        // Garantir que a distância entre as cidades esteja cadastrada
        distanciaRepository.findByCidades(cidadeOrigem, cidadeDestino)
                .orElseGet(() -> {
                    Distancia novaDistancia = new Distancia();
                    novaDistancia.setCidadeOrigem(cidadeOrigem);
                    novaDistancia.setCidadeDestino(cidadeDestino);
                    novaDistancia.setQuilometros(431);
                    distanciaRepository.save(novaDistancia);
                    return novaDistancia;
                });

        // Calcular o valor do frete
        BigDecimal valorCalculado = freteService.calcularValorFrete(cidadeOrigem, cidadeDestino, categoriaFrete);
        System.out.println("Valor calculado do frete: R$" + valorCalculado);
    }

    private static void testarBuscarFretePorId(FreteService freteService) {
        // Testar busca com ID válido
        try {
            Optional<Frete> optionalFrete = freteService.buscarFretePorId(1);
            if (optionalFrete.isPresent()) {
                Frete frete = optionalFrete.get();
                System.out.println("Frete encontrado: Código: " + frete.getCodigo() +
                        ", Nota Fiscal: " + frete.getNumeroNotaFiscal());
            } else {
                System.out.println("Frete com ID 1 não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao buscar frete: " + e.getMessage());
        }

        // Testar busca com ID inválido
        try {
            freteService.buscarFretePorId(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Teste de ID inválido passou: " + e.getMessage());
        }
    }
}
