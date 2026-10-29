package br.com.desafionttdata;

import br.com.desafionttdata.models.Cliente;
import br.com.desafionttdata.models.Conta;
import br.com.desafionttdata.services.ClienteService;
import br.com.desafionttdata.services.ContaService;
import br.com.desafionttdata.utils.UtilsFormat;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ClienteService clienteService = ClienteService.getClienteService();
    static ContaService contaService = new ContaService();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        if (true) {
            int menu = -1;

            do {
                printMenu();
                menu = UtilsFormat.intValue(sc);
                switch (menu) {
                    case 0:
                        System.out.println("== FINALIZANDO O NOVA BANK ==");
                        break;
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        clienteService.listar();
                        break;
                    case 3:
                        buscarCliente();
                        break;
                    case 4:
                        cadastrarConta();
                        break;
                    case 5:
                        contaService.listar();
                        break;
                    case 6:
                        consultarSaldo();
                        break;
                    case 7:
                        depositar();
                        break;
                    case 8:
                        sacar();
                        break;
                    case 9:
                        transferir();
                        break;
                    case 10:
                        imprimirHistorico();
                        break;
                    default:
                        System.out.println("OPÇÃO INVÁLIDA");
                }
            } while (menu != 0);
        }
    }

    private static void imprimirHistorico() {
        try {
            System.out.println("Informe qual o número da conta");
            int numero = sc.nextInt();
            Conta conta = contaService.buscarByNumero(numero);

            contaService.imprimirHistorico(conta);
        } catch (Exception e) {
            System.out.println("imprimirHistorico: " + e.getMessage());
        }
    }

    private static void transferir() {
        try {
            System.out.println("Informe qual o valor a ser transferido");
            long centavos = sc.nextLong();

            System.out.println("Informe qual o número da conta a ser debitada");
            int numero = sc.nextInt();
            Conta contaOrigem = contaService.buscarByNumero(numero);

            System.out.println("Informe qual o número da conta a ser creditada");
            numero = sc.nextInt();
            Conta contaDestino = contaService.buscarByNumero(numero);
            contaService.transferir(centavos, contaOrigem, contaDestino);
            System.out.println("Transferência concluída com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sacar() {
        try {
            System.out.println("Informe qual o número da conta");
            int numero = sc.nextInt();
            Conta conta = contaService.buscarByNumero(numero);
            System.out.println("Informe qual o valor a ser sacado em centavos");
            long centavos = sc.nextLong();
            contaService.sacar(centavos, conta);
            System.out.println("Saque realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void depositar() {
        try {
            System.out.println("Informe qual o número da conta");
            int numero = sc.nextInt();
            Conta conta = contaService.buscarByNumero(numero);
            System.out.println("Informe qual o valor a ser depositado em centavos");
            long centavos = sc.nextLong();
            contaService.depositar(centavos, conta);
            System.out.println("Depósito realizado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarSaldo() {
        System.out.println("Informe qual o número da conta");
        int numero = sc.nextInt();
        try {
            Conta conta = contaService.buscarByNumero(numero);
            System.out.println("Saldo da conta de número: " + conta.getNumero() + " é: " + conta.saldoFormatado());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarCliente() {
        System.out.println("Buscar cliente por CPF");
        String cpf = sc.next();
        clienteService.buscarByCPF(cpf);
    }

    private static void cadastrarCliente() {
        try {
            clienteService.cadastrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastrarConta() {
        try {
            contaService.cadastrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("========== NOVABANK ==========");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Buscar cliente por CPF");
        System.out.println("4 - Criar conta");
        System.out.println("5 - Listar contas");
        System.out.println("6 - Consultar saldo");
        System.out.println("7 - Realizar depósito");
        System.out.println("8 - Realizar saque");
        System.out.println("9 - Realizar transferência");
        System.out.println("10 - Consultar histórico");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção:");
    }
}