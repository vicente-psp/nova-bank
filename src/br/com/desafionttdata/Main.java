package br.com.desafionttdata;

import br.com.desafionttdata.services.MenuService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao NOVA BANK!");
        MenuService menuService = new MenuService();
        menuService.iniciar();
    }
}