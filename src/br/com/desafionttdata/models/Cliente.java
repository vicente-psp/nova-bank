package br.com.desafionttdata.models;

import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Conta> contas;
    private List<Movimentacao> movimentacoes;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public boolean isValidoNome() {
        return !nome.isBlank() && !nome.trim().isEmpty() && !nome.startsWith("\"") && nome.length() > 1;
    }

    public boolean isValidoCPF() {
        return !cpf.isBlank() && !cpf.trim().isEmpty() && !cpf.startsWith("\"") && cpf.length() == 11;
    }

    @Override
    public String toString() {
        return "nome: " + nome + ", cpf: " +cpf;
    }
}
