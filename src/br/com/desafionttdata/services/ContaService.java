package br.com.desafionttdata.services;

import br.com.desafionttdata.enums.Operacao;
import br.com.desafionttdata.enums.Tipo;
import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;
import br.com.desafionttdata.models.Conta;
import br.com.desafionttdata.models.Historico;
import br.com.desafionttdata.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ContaService implements IService<Conta> {
    static ClienteService clienteService = ClienteService.getClienteService();

    List<Conta> contas = new ArrayList<>();
    List<Historico> historicos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ContaService() {
        Cliente cliente = Cliente.builder().nome("José").cpf("11111111111").build();
        contas.add(new Conta(1010, cliente));
    }

    @Override
    public void cadastrar() throws Exception {
        System.out.println("Cadastrando uma nova conta");
        System.out.println("Informe o número da conta");
        int numero = sc.nextInt();
        System.out.println("Informe o cpf do cliente");
        String cpf = sc.next();
        Cliente cliente = clienteService.getClienteByCpf(cpf);
        if (cliente == null) {
            throw new Exception("Cliente não localizado");
        }
        Conta conta = new Conta(numero, cliente);
        contas.add(conta);
        System.out.println("Conta cadastrada com sucesso!");
    }

    @Override
    public void listar() {
        System.out.println("Listando as contas");
        contas.forEach(obj -> System.out.println(obj.toString()));
    }

    public Conta buscarByNumero(int numeroConta) throws Exception {
        Conta conta = contas.stream().filter(obj -> obj.getNumero() == numeroConta).findFirst().map(Conta::copy).orElse(null);
        if (conta == null) {
            throw new Exception("Conta " + numeroConta + " não foi localizada.");
        }
        return conta;
    }

    public void transferir(long valor, Conta contaDebitada, Conta contaCreditada) throws Exception {
        if (contaDebitada.getNumero() == contaCreditada.getNumero()) {
            throw new Exception("Conta debitada deve ser diferente da conta creditada.");
        }
        realizarSaque(valor, contaDebitada);
        realizarDeposito(valor, contaCreditada);
        historicos.add(new Historico(Utils.valorFormatado(valor), Operacao.TRANFERENCIA, contaDebitada, contaCreditada));
    }

    public void depositar(long valor, Conta contaCreditada) throws Exception {
        realizarDeposito(valor, contaCreditada);
        historicos.add(new Historico(Utils.valorFormatado(valor), Operacao.DEPOSITO, null, contaCreditada));
    }

    private void realizarDeposito(long valor, Conta conta) throws Exception {
        if (valor <= 0) {
            throw new Exception("Valor deve ser maior que 0");
        }
        long novoSaldo = conta.getSaldo() + valor;
        conta.setSaldo(novoSaldo);
    }

    public void sacar(long valor, Conta contaDebitada) throws Exception {
        realizarSaque(valor, contaDebitada);
        historicos.add(new Historico(Utils.valorFormatado(valor), Operacao.SAQUE, contaDebitada, null));
    }

    private void realizarSaque(long valor, Conta contaDebitada) throws Exception {
        if (valor <= 0) {
            throw new Exception("Valor deve ser maior que 0");
        }
        long novoSaldo = contaDebitada.getSaldo() - valor;
        if (novoSaldo < 0) {
            throw new Exception("Não existe saldo suficiente para este saque.");
        }
        contaDebitada.setSaldo(novoSaldo);
    }

    public void imprimirHistorico(Conta conta) {
        List<Historico> list = historicos
                .stream()
                .filter(obj -> {
                    return achouConta(conta, obj);
                })
                .map(obj -> {
                    if (obj.getContaDebitada() != null) {
                        obj.setTipo(Tipo.DEBITO);
                    } else {
                        obj.setTipo(Tipo.CREDITO);
                    }
                    return obj;
                }).collect(Collectors.toList());
        list.forEach(obj -> System.out.println(obj.toString()));
    }

    private boolean achouConta(Conta conta, Historico obj) {
        return (obj.getContaCreditada() != null && obj.getContaCreditada().getNumero() == conta.getNumero())
                || (obj.getContaDebitada() != null && obj.getContaDebitada().getNumero() == conta.getNumero());
    }

    public void bloquearDesbloquearConta(Conta conta, int bloqueio) {
        contas.forEach(obj -> {
            if (obj.getNumero() == conta.getNumero()) {
                obj.setBloqueada(bloqueio == 1);
            }
        });
    }
}
