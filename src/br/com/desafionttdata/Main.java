package br.com.desafionttdata;

import br.com.desafionttdata.services.ClienteService;
import br.com.desafionttdata.services.ContaService;
import br.com.desafionttdata.services.MenuService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao NOVA BANK!");
        ClienteService clienteService = ClienteService.getClienteService();
        ContaService contaService = ContaService.getContaService();
        MenuService menuService = new MenuService(clienteService, contaService);
        menuService.iniciar();
    }
}