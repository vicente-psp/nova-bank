package br.com.desafionttdata.services;

import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService implements IService<Cliente> {
    List<Cliente> clientes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ClienteService() {
        clientes.add(new Cliente("José", "11111111111"));
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
            System.out.println("Cliente: " + cliente.toString());
        }
    }

    public void cadastrar(Cliente cliente) {
//        System.out.println("Cadastrando um cliente");
//        System.out.println("Informe o nome");
//        String nome = sc.next();
//        System.out.println("Informe o cpf");
//        String cpf = sc.next();
        Cliente clienteDuplicado = getClienteByCpf(cliente.getCpf());
        if (clienteDuplicado == null) {
//            cliente = new Cliente(nome, cpf);
            if (cliente.isValidoNome() && cliente.isValidoCPF()) {
                clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso");
            } else {
                System.out.println("Cliente já cadastrado");
            }
        }
    }

    public Cliente getClienteByCpf(String cpf) {
        return clientes.stream().filter(obj -> obj.getCpf().equals(cpf)).findFirst().orElse(null);
    }
}
