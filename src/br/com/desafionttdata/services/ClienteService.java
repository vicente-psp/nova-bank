package br.com.desafionttdata.services;

import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService implements IService<Cliente> {
    static List<Cliente> clientes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ClienteService() {
        Cliente cliente = new Cliente();
        cliente.setNome("José");
        cliente.setCpf("11111111111");
        clientes.add(cliente);
//        clientes.add(new Cliente("José", "11111111111"));
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

    public void cadastrar() throws Exception {
        System.out.println("Cadastrando um cliente");
        System.out.println("Informe o nome");
        String nome = sc.next();
        System.out.println("Informe o cpf");
        String cpf = sc.next();
        Cliente clienteDuplicado = getClienteByCpf(cpf);
        if (clienteDuplicado == null) {
            Cliente cliente = new Cliente(nome, cpf);
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
        return clientes.stream().filter(obj -> obj.getCpf().equals(cpf)).findFirst().orElse(null);
    }
}
