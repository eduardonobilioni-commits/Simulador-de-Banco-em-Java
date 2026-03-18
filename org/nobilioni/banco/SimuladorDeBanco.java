import org.nobilioni.banco.*;
import org.nobilioni.banco.Conta;

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Cliente titular = new Cliente();
    titular.setNome("Eduardo");

    Cliente cliente2 = new Cliente();
    cliente2.setNome("Sistema Banco");
    Conta contaDestino = new ContaPoupanca(new Cliente("Recebedor"), "0000");

    System.out.println("--- BEM-VINDO AO BANCO NOBILIONI ---");
    System.out.println("1 - Abrir Conta Corrente");
    System.out.println("2 - Abrir Conta Poupança");
    System.out.print("Escolha o tipo de conta: ");
    int tipo = scanner.nextInt();

    Conta conta; // Declaramos a variável do tipo genérico (Polimorfismo!)

    if (tipo == 1) {
        conta = new ContaCorrente(titular, "1234");
        System.out.println("Conta Corrente criada com sucesso!");
    } else {
        conta = new ContaPoupanca(titular, "1234");
        System.out.println("Conta Poupança criada com sucesso!");
    }


        int opcao;
        String senhaDigitada;

        do {
            conta.mostrarMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    conta.mostrarSaldo();
                    break;

                case 2:
                    System.out.print("Digite o valor para sacar: R$ ");
                    double valorSaque = scanner.nextDouble();
                    System.out.print("Digite sua senha: ");
                    senhaDigitada = scanner.next();

                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.sacar(valorSaque);
                    } else {
                        System.out.println("*** SENHA INCORRETA ***");
                    }
                    break;

                case 3:
                    System.out.print("Digite o valor para depositar: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    System.out.print("Digite sua senha: ");
                    senhaDigitada = scanner.next();

                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.depositar(valorDeposito);
                    } else {
                        System.out.println("*** SENHA INCORRETA ***");
                    }
                    break;

                case 4:
                    System.out.print("Digite sua senha para ver o extrato: ");
                    senhaDigitada = scanner.next();
                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.mostrarExtrato();
                    } else {
                        System.out.println("*** SENHA INCORRETA ***");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando programa. Até logo!");
                    break;

                case 6:
                    if (conta instanceof ContaPoupanca) {
                        ((ContaPoupanca) conta).aplicarRendimento();
                    } else {
                        System.out.println("Erro: Esta opção só está disponível para Conta Poupança.");
                    }
                    break;

                case 7:
                    System.out.print("Digite o valor para transferência: R$ ");
                    double vTransf = scanner.nextDouble();
                    System.out.print("Digite sua senha: ");
                    senhaDigitada = scanner.next();

                    if (senhaDigitada.equals(conta.getSenha())) {
                        conta.transferir(vTransf, contaDestino);
                    } else {
                        System.out.println("Senha incorreta!");
                    }
                    break;

                default:
                    System.out.println("*** OPÇÃO INVÁLIDA ***");
            }
        } while (opcao != 7);

        scanner.close();
    }

