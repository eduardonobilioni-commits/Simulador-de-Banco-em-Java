package org.nobilioni.banco;

import java.util.ArrayList;

public abstract class Conta {

    Cliente titular;
    protected double saldo;
    protected String agencia;
    protected long conta;
    protected String senha;
    protected ArrayList<String> historico;
    protected ArrayList<Cliente> clientes;

    public Conta(Cliente titular, String senha) {
        this.titular = titular;
        this.senha = "0000";
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        this.agencia = String.valueOf(1);
        this.conta = 12345L;
    }

    public void mostrarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("***VALOR INVÁLIDO PARA DEPÓSITO***");
            return;
        }
        saldo += valor;
        historico.add("Depósito: + R$ " + valor);
        System.out.println("Depósito realizado com sucesso");
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    protected void sacar() {
        sacar(0.0);
    }

    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("***SALDO INSUFICIENTE***");
        } else {
            saldo -= valor;
            historico.add("Saque: - R$ " + valor);
            System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
            System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        }
    }

    public void transferir(double valor, Conta contaDestino) {

        if (valor > this.saldo) {
            System.out.println("*** TRANSFERÊNCIA CANCELADA: SALDO INSUFICIENTE ***");
        } else if (valor < this.saldo)
            System.out.println("Saldo insuficiente");
        {

            this.saldo -= valor;
            this.historico.add("Transferência enviada para " + contaDestino.titular.getNome() + ": - R$ " + valor);

            contaDestino.depositar(valor);

            System.out.println("Transferência realizada com sucesso!");
        }
    }

    public void mostrarExtrato() {
        System.out.println("=== EXTRATO ===");
        System.out.println("Titular: " + titular);

        if (historico.isEmpty()) {
            System.out.println("NENHUMA MOVIMENTAÇÃO");
        } else {
            for (String item : historico) {
                System.out.println(item);
            }
        }
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        System.out.println("===============");
    }

    public void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Saldo");
        System.out.println("2 - Sacar");
        System.out.println("3 - Depositar");
        System.out.println("4 - Extrato");
        System.out.println("5 - Sair");
    }

    public long getConta() {
        return conta;
    }

    public void setConta(long conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public abstract void transferir(double valor);
}
