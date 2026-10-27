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
        boolean resp = !nome.isBlank() && !nome.trim().isEmpty() && !nome.startsWith("\"") && nome.length() > 1;
        if (!resp) {
            System.out.println("Nome inválido");
        }
        return resp;
    }

    public boolean isValidoCPF() {
        boolean resp = cpf.length() == 11;
        if (!resp) {
            System.out.println("CPF inválido");
        }
        return resp;
    }

    @Override
    public String toString() {
        return "nome: " + nome + ", cpf: " +cpf;
    }
}
