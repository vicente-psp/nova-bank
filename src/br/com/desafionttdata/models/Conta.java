package br.com.desafionttdata.models;

import br.com.desafionttdata.utils.Utils;

public class Conta {
    private int numero;
    private long saldo = 0; // 1750 = 17,50
    private Cliente cliente;
    private boolean bloqueada;

    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
    }

    public static Conta copy(Conta conta) {
        Conta novaConta = new Conta(conta.getNumero(), conta.getCliente());
        novaConta.setSaldo(conta.getSaldo());
        novaConta.setBloqueada(conta.isBloqueada());
        return novaConta;
    }

    public int getNumero() {
        return numero;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public String saldoFormatado() {
        return Utils.valorFormatado(saldo);
    }

    @Override
    public String toString() {
        String contaFormatada = "Conta de número: " + numero + " | saldo: " + Utils.valorFormatado(saldo);
        if (bloqueada) {
            contaFormatada += " | bloqueada";
        }
        return contaFormatada + " | cliente: " + cliente.toString();
    }
}
