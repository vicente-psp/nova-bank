package br.com.desafionttdata.services;

import br.com.desafionttdata.enums.Operacao;
import br.com.desafionttdata.enums.Tipo;
import br.com.desafionttdata.interfaces.IService;
import br.com.desafionttdata.models.Cliente;
import br.com.desafionttdata.models.Conta;
import br.com.desafionttdata.models.Historico;
import br.com.desafionttdata.utils.UtilsFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContaService implements IService<Conta> {
    List<Conta> contas = new ArrayList<>();
    List<Historico> historicos = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public ContaService() {
        contas.add(new Conta(1010, new Cliente("jose", "11111111111")));
        contas.add(new Conta(1011, new Cliente("jose", "11111111111")));
    }

    @Override
    public void cadastrar(Conta conta) {
        contas.add(conta);
    }

    @Override
    public void listar() {
        System.out.println("Listando as contas");
        contas.forEach(obj -> System.out.println(obj.toString()));
    }

    public Conta buscarByNumero(int numeroConta) throws Exception {
        Conta conta = contas.stream().filter(obj -> obj.getNumero() == numeroConta).findFirst().orElse(null);
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
        historicos.add(new Historico(UtilsFormat.valorFormatado(valor), Operacao.TRANFERENCIA, contaDebitada, contaCreditada));
    }

    public void depositar(long valor, Conta contaCreditada) throws Exception {
        realizarDeposito(valor, contaCreditada);
        historicos.add(new Historico(UtilsFormat.valorFormatado(valor), Operacao.DEPOSITO, null, contaCreditada));
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
        historicos.add(new Historico(UtilsFormat.valorFormatado(valor), Operacao.SAQUE, contaDebitada, null));
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
}
