package br.com.desafionttdata.models;

import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Conta> contas;
    private List<Movimentacao> movimentacoes;

    private Cliente(Builder builder) {
        this.nome = builder.nome;
        this.cpf = builder.cpf;
        this.email = builder.email;
        this.telefone = builder.telefone;
        this.contas = builder.contas;
        this.movimentacoes = builder.movimentacoes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String nome;
        private String cpf;
        private String email;
        private String telefone;
        private List<Conta> contas;
        private List<Movimentacao> movimentacoes;

        private Builder() {}

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder contas(List<Conta> contas) {
            this.contas = contas;
            return this;
        }

        public Builder movimentacoes(List<Movimentacao> movimentacoes) {
            this.movimentacoes = movimentacoes;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }


    public static Cliente copy(Cliente cliente) {
        return Cliente.builder()
                    .nome(cliente.getNome())
                    .cpf(cliente.getCpf())
                    .email(cliente.getEmail())
                    .telefone(cliente.getTelefone())
                .build();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
