package org.nobilioni.banco;

public class ContaPoupanca extends Conta {

    private double taxaRendimento = 0.005; // 0.5% ao mês, por exemplo

    public ContaPoupanca(Cliente titular, String senha) {
        super(titular, senha);
    }

    // Metodo exclusivo da Poupança
    public void aplicarRendimento() {
        double rendimento = this.getSaldo() * taxaRendimento;
        depositar(rendimento); // Reutiliza o metodo de depósito da mãe!
        System.out.printf("Rendimento de R$ %.2f aplicado!%n", rendimento);
    }

    @Override
    public void sacar(double valor) {
       if (valor <= this.getSaldo()) {
            super.sacar(valor);
       } else {
            System.out.println("*** ERRO: Poupança não possui limite extra ***");
        }
    }
    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            contaDestino.depositar(valor);

            this.historico.add("Transferência enviada: - R$ " + valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("*** ERRO: Poupança não permite transferência acima do saldo real ***");
        }
    }
    @Override
    public void transferir(double valor) {

    }

    @Override
    public double getSaldo() {
        return 0;
    }
}