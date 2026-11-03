package br.com.desafionttdata.services;

import br.com.desafionttdata.models.Cliente;
import br.com.desafionttdata.models.Conta;
import br.com.desafionttdata.utils.Utils;

import java.util.Scanner;

public class MenuService {
    ClienteService clienteService = ClienteService.getClienteService();
    ContaService contaService = ContaService.getContaService();
    Scanner sc = new Scanner(System.in);
    int menu = -1;

    public void iniciar() {
        do {
            imprimirMenu();
            menu = Utils.intValue(sc);
            try {
                switch (menu) {
                    case 0:
                        finalizar();
                        break;
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        buscarClientePorCPF();
                        break;
                    case 4:
                        cadastrarConta();
                        break;
                    case 5:
                        listarContas();
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
                    case 11:
                        atualizarCliente();
                        break;
                    case 12:
                        bloquearDesbloquearConta();
                        break;
                    case 13:
                        exportarMovimentacao();
                        break;
                    default:
                        System.out.println("OPÇÃO INVÁLIDA");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (menu != 0);
    }

    public void finalizar() {
        System.out.println("Os cadastros e saldos serão perdidos ao encerrar!");
        System.out.println("Deseja mesmo encerrar a aplicação? Responda [S] para encerrar?");
        String encerrar = sc.nextLine();
        if (!encerrar.equalsIgnoreCase("S")) {
            menu = -1;
        } else {
            System.out.println("== FINALIZANDO O NOVA BANK ==");
        }
    }

    private void exportarMovimentacao() throws Exception {
        System.out.println("Informe qual o número da conta deseja exportar a movimentacao");
        int numero = Utils.intValue(sc);

        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        contaService.exportarMovimentacao(conta);
    }

    private void bloquearDesbloquearConta() throws Exception {
        System.out.println("Informe qual o número da conta deseja bloquear/desbloquear");
        int numero = Utils.intValue(sc);
        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        System.out.println("Digite 1 para bloquear, ou qualquer outro número para desbloquear");
        int bloqueio = Utils.intValue(sc);
        contaService.bloquearDesbloquearConta(conta, bloqueio);
    }

    private void imprimirHistorico() throws Exception {
        System.out.println("Informe qual o número da conta");
        int numero = Utils.intValue(sc);

        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        contaService.imprimirHistorico(conta);
    }

    private void transferir() throws Exception {
        System.out.println("Informe qual o valor a ser transferido");
        long centavos = Utils.longValue(sc);

        System.out.println("Informe qual o número da conta a ser debitada");
        int numero = Utils.intValue(sc);
        Conta contaOrigem = contaService.buscarPorNumero(numero);
        if (contaOrigem == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        System.out.println("Informe qual o número da conta a ser creditada");
        numero = Utils.intValue(sc);
        Conta contaDestino = contaService.buscarPorNumero(numero);
        if (contaDestino == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        contaService.transferir(centavos, contaOrigem, contaDestino);
        System.out.println("Transferência concluída com sucesso!");
    }

    private void sacar() throws Exception {
        System.out.println("Informe qual o número da conta");
        int numero = Utils.intValue(sc);
        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        System.out.println("Informe qual o valor a ser sacado em centavos");
        long centavos = Utils.longValue(sc);
        contaService.sacar(centavos, conta);
        System.out.println("Saque realizado com sucesso!");
    }

    private void depositar() throws Exception {
        System.out.println("Informe qual o número da conta");
        int numero = Utils.intValue(sc);
        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }

        System.out.println("Informe qual o valor a ser depositado em centavos");
        long centavos = Utils.longValue(sc);
        contaService.depositar(centavos, conta);
        System.out.println("Depósito realizado com sucesso!");
    }

    private void consultarSaldo() throws Exception {
        System.out.println("Informe qual o número da conta");
        int numero = Utils.intValue(sc);
        Conta conta = contaService.buscarPorNumero(numero);
        if (conta == null) {
            throw new Exception("Conta " + numero + " não foi localizada.");
        }
        System.out.println("Saldo da conta de número: " + conta.getNumero() + " é: " + conta.saldoFormatado());
    }

    private void listarContas() {
        contaService.listar();
    }

    private void buscarClientePorCPF() throws Exception {
        System.out.println("Buscar cliente por CPF");
        String cpf = sc.nextLine();
        Cliente cliente = clienteService.buscarPorCPF(cpf);
        System.out.println("Cliente: " + cliente.toString());
    }

    private void cadastrarCliente() throws Exception {
        clienteService.cadastrar();
    }

    private void listarClientes() {
        clienteService.listar();
    }

    private void atualizarCliente() throws Exception {
        clienteService.atualizarCliente();
    }

    private void cadastrarConta() throws Exception {
        contaService.cadastrar();
    }

    private void imprimirMenu() {
        System.out.println();
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
        System.out.println("11 - Atualizar cliente");
        System.out.println("12 - Bloquear ou desbloquear uma conta");
        System.out.println("13 - Exportar movimentação");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção:");
    }
}
