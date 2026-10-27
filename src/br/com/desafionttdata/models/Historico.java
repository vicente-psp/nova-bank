package br.com.desafionttdata.models;

import br.com.desafionttdata.enums.Operacao;
import br.com.desafionttdata.enums.Tipo;

// 1010

public class Historico {
//    valor | conta | credito | deposito
// valor | credito | transferencia | contaOrigem | contaDestino
    private String valor;
    private Tipo tipo;
    private Operacao operacao;
    private Conta contaDebitada; // 1011 - Débito
    private Conta contaCreditada; // 1010 - Crédito

    public Historico(String valor, Operacao operacao, Conta contaDebitada, Conta contaCreditada) {
        this.valor = valor;
//        this.tipo = tipo;
        this.operacao = operacao;
        this.contaDebitada = contaDebitada;
        this.contaCreditada = contaCreditada;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public Conta getContaCreditada() {
        return contaCreditada;
    }

    public void setContaCreditada(Conta contaCreditada) {
        this.contaCreditada = contaCreditada;
    }

    public Conta getContaDebitada() {
        return contaDebitada;
    }

    public void setContaDebitada(Conta contaDebitada) {
        this.contaDebitada = contaDebitada;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Valor: " + valor);
        if (tipo != null) {
            builder.append(" | tipo: " + tipo);
        }
        builder.append(" | operação: " + operacao);
        if (contaCreditada != null) {
            builder.append(" | conta de número: " + contaCreditada.getNumero());
        } else {
            builder.append(" | conta de número: " + contaDebitada.getNumero());
        }
        return builder.toString();
    }
}
