package br.com.desafionttdata.services;

import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService implements IService<Cliente> {
    static List<Cliente> clientes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    private static ClienteService clienteService;

    private ClienteService() {
        Cliente cliente = Cliente.builder().nome("José").cpf("11111111111").email("email@teste").build();
        clientes.add(cliente);
    }

    public static ClienteService getClienteService() {
        if (clienteService == null) {
            clienteService = new ClienteService();
        }
        return clienteService;
    }

    public void listar() {
        System.out.println("Listando os clientes");
        clientes.forEach(obj -> System.out.println(obj.toString()));
    }

    public void buscarByCPF(String cpf) {
        Cliente cliente = getClienteByCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado");
        } else {
            cliente.setNome("Lincon");
            System.out.println("Cliente: " + cliente.toString());
        }
    }

    public void atualizarCliente() throws Exception {
        System.out.println("Atualizando um cliente");
        System.out.println("Informe o cpf");
        String cpf = sc.next();
        Cliente cliente = getClienteByCpf(cpf); // x0D1sds
        if (cliente != null) {
            System.out.println("Informe o nome");
            String nome = sc.next();

            cliente.setNome(nome);
            if (!cliente.isValidoNome()) {
                throw new Exception("Nome Inválido");
            } else {
                sc.nextLine(); // para parar com o pulo na hora de capturar o valor do terminal
                System.out.println("Informe o email");
                String email = sc.nextLine();
                cliente.setEmail(email);
                System.out.println("Informe o telefone");
                String telefone = sc.nextLine();
                cliente.setTelefone(telefone);
                clientes.forEach(obj -> {
                    if (obj.getCpf().equals(cliente.getCpf())) {
                        obj.setNome(cliente.getNome());
                        obj.setEmail(cliente.getEmail());
                        obj.setTelefone(cliente.getTelefone());
                    }
                });
                System.out.println("Cliente atualizado com sucesso");
            }
        } else {
            throw new Exception("Cliente não encontrado");
        }
    }

    // refatorar - corrigir a ordem de informar os dados, pois não faz sentido
    // o usuário informar todos os dados para no final o sistema barrar por
    // conta do primeiro dado informado
    public void cadastrar() throws Exception {
        System.out.println("Cadastrando um cliente");
        System.out.println("Informe o nome");
        String nome = sc.next();
        System.out.println("Informe o cpf");
        String cpf = sc.next();
        System.out.println("Informe o email"); // corrigir a obrigatoriedade de informar este dado, ver exemplo do atualizarCadastro
        String email = sc.next();
        System.out.println("Informe o telefone"); // corrigir a obrigatoriedade de informar este dado, ver exemplo do atualizarCadastro
        String telefone = sc.next();
        Cliente clienteDuplicado = getClienteByCpf(cpf);
        if (clienteDuplicado == null) {
            Cliente cliente = Cliente
                        .builder()
                        .nome(nome)
                        .cpf(cpf)
                        .email(email)
                        .telefone(telefone)
                    .build();
            if (!cliente.isValidoNome()) {
                throw new Exception("Nome Inválido");
            } else if (!cliente.isValidoCPF()) {
                throw new Exception("CPF Inválido");
            } else {
                clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso");
            }
        } else {
            throw new Exception("Cliente já cadastrado");
        }
    }

    public Cliente getClienteByCpf(String cpf) {
        return clientes.stream().filter(obj -> obj.getCpf().equals(cpf)).findFirst().map(Cliente::copy).orElse(null);
    }
}
