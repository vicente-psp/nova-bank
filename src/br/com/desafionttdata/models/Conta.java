package br.com.desafionttdata.models;

import br.com.desafionttdata.utils.UtilsFormat;

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
        return UtilsFormat.valorFormatado(saldo);
    }

    @Override
    public String toString() {
        return "Conta de número: " + numero + " | saldo: " + UtilsFormat.valorFormatado(saldo) + " | cliente: " + cliente.toString();
    }
}
