package br.com.desafionttdata.models;

import br.com.desafionttdata.utils.Utils;

public class Conta {
    private int numero;
    private long saldo = 0; // 1750 = 17,50
    private Cliente cliente;

    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
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

    public String saldoFormatado() {
        return Utils.valorFormatado(saldo);
    }

    @Override
    public String toString() {
        return "Conta de número: " + numero + " | saldo: " + Utils.valorFormatado(saldo) + " | cliente: " + cliente.toString();
    }
}
