package org.nobilioni.banco;

import java.util.Scanner;

public class SimuladorDeBanco {
    public static void main(String[] args) {
        // 1. CARREGAR O SALDO DO ARQUIVO ANTES DE TUDO
        double saldoRecuperado = GerenciadorArquivos.lerSaldo();

        Scanner scanner = new Scanner(System.in);
        Cliente titular = new Cliente();
        titular.setNome("Eduardo");

        // Conta de destino fixa para testes de transferência
        Conta contaDestino = new ContaPoupanca(new Cliente("Sistema Banco"), "0000");

        System.out.println("--- BEM-VINDO AO BANCO NOBILIONI ---");
        System.out.println("1 - Abrir Conta Corrente");
        System.out.println("2 - Abrir Conta Poupança");
        System.out.print("Escolha o tipo de conta: ");
        int tipo = scanner.nextInt();

        Conta conta; // Variável polimórfica

        // 2. CRIAR A CONTA APENAS UMA VEZ (O 'NEW' É AQUI)
        if (tipo == 1) {
            conta = new ContaCorrente(titular, "1234");
            System.out.println("=> Conta Corrente aberta.");
        } else {
            conta = new ContaPoupanca(titular, "1234");
            System.out.println("=> Conta Poupança aberta.");
        }

        // 3. INJETAR O SALDO RECUPERADO (SÓ AQUI E UMA VEZ!)
        if (saldoRecuperado > 0) {
            conta.setSaldoInicial(saldoRecuperado);
            System.out.println("=> Sistema: Saldo de R$ " + saldoRecuperado + " recuperado do arquivo.");
        }

        int opcao;
        String senhaDigitada;

        do {
            System.out.println("\n--- MENU DE OPERAÇÕES ---");
            System.out.println("1-Saldo | 2-Sacar | 3-Depositar | 4-Extrato | 5-Rendimento | 6-Transferir | 7-SAIR");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Senha: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.mostrarSaldo();
                    } else {
                        System.out.println("Senha incorreta!");
                    }
                    break;

                case 2:
                    System.out.print("Valor do saque: R$ ");
                    double valorSaque = scanner.nextDouble();
                    System.out.print("Senha: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.sacar(valorSaque);
                        // SALVAR NO DISCO APÓS A OPERAÇÃO
                        GerenciadorArquivos.salvarSaldo(conta.getSaldo());
                        GerenciadorArquivos.salvarNoExtrato("SAQUE: - R$ " + valorSaque);
                    }
                    break;

                case 3:
                    System.out.print("Valor do depósito: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    System.out.print("Senha: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.depositar(valorDeposito);
                        // SALVAR NO DISCO APÓS A OPERAÇÃO
                        GerenciadorArquivos.salvarSaldo(conta.getSaldo());
                        GerenciadorArquivos.salvarNoExtrato("DEPÓSITO: + R$ " + valorDeposito);
                    }
                    break;

                case 4:
                    System.out.print("Senha para extrato: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.mostrarExtrato();
                    } else {
                        System.out.println("Senha incorreta!");
                    }
                    break;

                case 5:
                    if (conta instanceof ContaPoupanca) {
                        ((ContaPoupanca) conta).aplicarRendimento();
                        GerenciadorArquivos.salvarSaldo(conta.getSaldo());
                        GerenciadorArquivos.salvarNoExtrato("RENDIMENTO APLICADO");
                    } else {
                        System.out.println("Opção exclusiva para Conta Poupança.");
                    }
                    break;

                case 6:
                    System.out.print("Valor da transferência: R$ ");
                    double vTransf = scanner.nextDouble();
                    System.out.print("Senha: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.transferir(vTransf, contaDestino);
                        GerenciadorArquivos.salvarSaldo(conta.getSaldo());
                        GerenciadorArquivos.salvarNoExtrato("TRANSFERÊNCIA ENVIADA: - R$ " + vTransf);
                    }
                    break;

                case 7:
                    System.out.println("Saindo... Saldo final salvo.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}