package org.nobilioni.banco;

public class ContaCorrente extends Conta {
    private double limite = 500.0;

    public ContaCorrente(Cliente titular, String senha) {
        super(titular, senha);
    }

    @Override
    public void mostrarSaldo() {
        System.out.println("--- SALDO CONTA CORRENTE ---");
        System.out.printf("Saldo Real: R$ %.2f%n", this.saldo);
        System.out.printf("Limite Especial: R$ %.2f%n", this.limite);
        System.out.printf("Total Disponível: R$ %.2f%n", (this.saldo + this.limite));
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= (this.saldo + this.limite)) {
            this.saldo -= valor;
            this.historico.add("Saque (CC): - R$ " + valor);
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("*** SALDO E LIMITE INSUFICIENTES ***");
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= (this.saldo + this.limite)) {
            this.saldo -= valor;
            contaDestino.depositar(valor);
            this.historico.add("Transferência enviada: - R$ " + valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso!");
        } else {
            System.out.println("*** ERRO: Saldo/Limite insuficiente ***");
        }
    }

    @Override
    public void transferir(double valor) {

    }
}