package br.com.desafionttdata.services;

import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;
import br.com.desafionttdata.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService implements IService<Cliente> {
    static List<Cliente> clientes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    private static ClienteService clienteService;

    private ClienteService() { }

    public static ClienteService getClienteService() {
        if (clienteService == null) {
            clienteService = new ClienteService();
        }
        return clienteService;
    }

    public void listar() {
        System.out.println("Listando os clientes...");
        if (clientes.isEmpty()) {
            System.out.println("Ainda não possui clientes cadastradas");
        } else {
            clientes.forEach(obj -> System.out.println(obj.toString()));
        }
    }

    public Cliente buscarPorCPF(String cpf) throws Exception {
        Cliente cliente = getClienteByCpf(cpf);
        if (cliente == null) {
            throw new Exception("Cliente não encontrado");
        }
        return cliente;
    }

    public void atualizarCliente() throws Exception {
        System.out.println("Atualizando um cliente");
        System.out.println("Informe o cpf");
        String cpf = sc.nextLine();
        Cliente cliente = getClienteByCpf(cpf); // x0D1sds
        if (cliente != null) {
            System.out.println("Informe o nome");
            String nome = sc.nextLine();

            cliente.setNome(nome);
            if (!cliente.isValidoNome()) {
                throw new Exception("Nome Inválido");
            } else {
//                sc.nextLine(); // para parar com o pulo na hora de capturar o valor do terminal
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

    public void cadastrar() throws Exception {
        System.out.println("Cadastrando um cliente");
        System.out.println("Informe o nome");
        String nome = sc.nextLine();
        if (!Utils.isValidoTexto(nome)) {
            throw new Exception("Nome Inválido");
        }
        System.out.println("Informe o cpf");
        String cpf = sc.nextLine();
        if (!Utils.isValidoCPF(cpf)) {
            throw new Exception("CPF Inválido");
        }

        Cliente clienteDuplicado = getClienteByCpf(cpf);
        if (clienteDuplicado == null) {
//            sc.nextLine(); // para parar com o pulo na hora de capturar o valor do terminal
            System.out.println("Informe o email");
            String email = sc.nextLine();
            System.out.println("Informe o telefone");
            String telefone = sc.nextLine();
            Cliente cliente = Cliente
                        .builder()
                        .nome(nome)
                        .cpf(cpf)
                        .email(email)
                        .telefone(telefone)
                    .build();
            clientes.add(cliente);
            System.out.println("Cliente cadastrado com sucesso");
        } else {
            throw new Exception("Cliente já cadastrado");
        }
    }

    public Cliente getClienteByCpf(String cpf) {
        return clientes.stream().filter(obj -> obj.getCpf().equals(cpf)).findFirst().map(Cliente::copy).orElse(null);
    }
}
