package org.nobilioni.banco;

import java.util.ArrayList;

public abstract class Conta {

    protected Cliente titular;
    protected double saldo;
    protected String agencia;
    protected long conta;
    protected String senha;
    protected ArrayList<String> historico;

    public Conta(Cliente titular, String senha) {
        this.titular = titular;
        this.senha = String.valueOf(1234); // Agora usa a senha que você passar, não mais fixa "0000"
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        this.agencia = "1";
        this.conta = 12345L;
    }

    // METODO ESSENCIAL: Carrega o saldo do arquivo sem sujar o extrato
    public void setSaldoInicial(double saldoRecuperado) {
        this.saldo = saldoRecuperado;
    }

    public void mostrarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("***VALOR INVÁLIDO PARA DEPÓSITO***");
            return;
        }
        this.saldo += valor;
        this.historico.add("Depósito: + R$ " + valor);
        System.out.println("Depósito realizado com sucesso");
    }

    public void sacar(double valor) {
    }

    public abstract void transferir(double valor, Conta contaDestino);

    public void mostrarExtrato() {
        System.out.println("=== EXTRATO ===");
        System.out.println("Titular: " + (titular != null ? titular.getNome() : "Não informado"));

        if (historico.isEmpty()) {
            System.out.println("NENHUMA MOVIMENTAÇÃO");
        } else {
            for (String item : historico) {
                System.out.println(item);
            }
        }
        System.out.printf("Saldo final no extrato: R$ %.2f%n", saldo);
        System.out.println("===============");
    }

    // Getters e Setters
    public String getSenha() { return senha; }

    public abstract void transferir(double valor);

    public double getSaldo() { return saldo; }
    public void setSenha(String senha) { this.senha = senha; }
}