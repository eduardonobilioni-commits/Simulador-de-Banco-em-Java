# Funcionalidades e usos de lógica — simulador-banco-java

Este documento descreve as funcionalidades implementadas nas classes do pacote org.nobilioni.banco e os usos de lógica observados no código.

## Visão geral das classes

- Cliente
  - Métodos: getters e setters simples (encapsulamento de dados).
  - Uso de lógica: nenhuma validação; setters simplesmente atribuem valores.

- Conta (abstract)
  - Atributos: Cliente titular; protected double saldo; String agencia; long conta; String senha; ArrayList<String> historico.
  - Construtor: inicializa titular, senha (configurada para "0000" no código), saldo = 0.0, histórico vazio, agência e número de conta padrão.
  - Métodos principais:
    - mostrarSaldo(): imprime saldo formatado.
    - depositar(double valor): valida valor > 0; atualiza saldo; adiciona entrada no histórico; mensagens de sucesso/erro.
    - sacar(): sobrecarga sem argumentos que chama sacar(0.0).
    - sacar(double valor): verifica saldo insuficiente (if valor > saldo) e, se possível, subtrai e registra no histórico.
    - transferir(double valor, Conta contaDestino): lógica de transferência com checagem de saldo, atualização de histórico e chamada a depositar() no destino.
    - mostrarExtrato(): imprime histórico (ou mensagem caso vazio) e saldo final.
    - mostrarMenu(): imprime opções do menu.
  - Uso de lógica:
    - Validação de entrada (depósito > 0).
    - Controle de fluxo com if/else para verificar saldo antes de sacar/transferir.
    - Uso de ArrayList para registrar histórico (estrutura de dados simples).
    - Formatação de saída com System.out.printf.
  - Observações/bugs potenciais encontrados:
    - O construtor ignora a senha passada por parâmetro e seta "0000" por padrão — comportamento inesperado.
    - O método transferir contém uma construção suspeita: "else if (valor < this.saldo) System.out.println(...); { ... }" Há uma chave solta ({) logo em seguida que faz o bloco ser sempre executado, o que pode permitir transferências mesmo quando não há saldo suficiente. Isso parece ser um bug lógico (parênteses/chaves mal posicionadas).

- ContaCorrente (extends Conta)
  - Atributos: limite (double) — limite especial para conta corrente, ex: 500.0.
  - Métodos:
    - mostrarSaldo(): sobrescreve para exibir saldo real, limite e total disponível (saldo + limite).
    - sacar(double valor): sobrescreve para permitir uso do saldo + limite; atualiza histórico e imprime mensagens.
  - Uso de lógica:
    - Herança e polimorfismo: sobrescrita de métodos da classe base com comportamento diferente.
    - Cálculo de limite disponível: soma de saldo e limite para autorização de saques.

- ContaPoupanca (extends Conta)
  - Atributos: taxaRendimento (double) — taxa fixa usada no método aplicarRendimento().
  - Métodos:
    - aplicarRendimento(): calcula rendimento = saldo * taxaRendimento e chama depositar(rendimento) para aplicar o rendimento ao saldo.
    - sacar(double valor): sobrescreve para proibir uso de limite (saca apenas se saldo >= valor), caso contrário imprime erro.
  - Uso de lógica:
    - Comportamento específico para poupança (sem limite).
    - Reuso de métodos da classe-mãe (depositar) para aplicar rendimento.
  - Observações/bugs potenciais:
    - A classe define um método privado getSaldo() que retorna 0 no código atual, e os métodos usam this.getSaldo() — isso impede qualquer operação correta de saque/aplicação de rendimento. Possível stub deixado por engano.

- SimuladorDeBanco (contém main)
  - Fluxo principal:
    - Usa Scanner para entrada do usuário (console).
    - Cria um Cliente e define nome padrão.
    - Pergunta ao usuário o tipo de conta (1 = ContaCorrente, 2 = ContaPoupanca) e instancia a conta apropriada (polimorfismo: variável do tipo Conta referenciando subclasse).
    - Loop do-while apresentando menu e lendo opção via scanner.
    - switch(opcao) para tratar ações: mostrar saldo, sacar, depositar, mostrar extrato, sair, aplicar rendimento (opção 6), default para opção inválida.
    - Para operações sensíveis (sacar, depositar, extrato) pede a senha e compara com conta.getSenha() (cheque via equals).
    - Para saque e depósito lê valores com scanner.nextDouble().
    - O loop termina quando opcao == 6 (observação: código apresenta essa condição — normalmente sair seria opção 5; o laço termina ao escolher 6).
  - Uso de lógica:
    - Entrada/saída via Scanner.
    - Estruturas de controle: if/else, switch, do-while.
    - Uso de instanceof para checar se a conta é ContaPoupanca antes de chamar aplicarRendimento().
    - Comparação de strings para validação de senha (equals).

## Padrões e conceitos usados

- Orientação a objetos:
  - Encapsulamento: atributos privados/protected com getters/setters.
  - Herança: Conta é classe base abstrata; ContaCorrente e ContaPoupanca estendem Conta.
  - Polimorfismo: variável do tipo Conta referenciando subclasses; métodos sobrescritos (mostrarSaldo, sacar).

- Validações e tratamento simples de erro:
  - Verificação de valor de depósito (> 0).
  - Verificação de saldo antes de saque/transferência (embora transferir contenha bug de execução sempre verdadeira).
  - Verificação de senha antes de operações sensíveis (comparação de string).

- Estruturas de controle:
  - if / else: validações e mensagens de erro.
  - switch: menu de operações.
  - do / while: repetição do menu até condição de saída.

- Estruturas de dados:
  - ArrayList<String> para armazenar histórico de transações.

- I/O e formatação:
  - Scanner para entrada via console.
  - System.out.println e System.out.printf para saída formatada.

## Problemas e sugestões de melhoria

1. Construtor de Conta ignora a senha passada e seta "0000" — ajustar para usar o valor passado como parâmetro.
2. transferir(...) possui chaves/parênteses mal posicionadas, o que faz o bloco que realiza a transferência ser executado mesmo quando não há saldo suficiente. Corrigir a condição e as chaves para respeitar a lógica esperada.
3. ContaPoupanca tem um getSaldo() privado que retorna 0 — isso impede a funcionalidade de saque e de aplicar rendimento. Remover esse stub ou usar o saldo protegido da classe-mãe (this.saldo) ou adicionar um getter correto.
4. No main, o laço termina quando opcao == 6, embora 5 seja apresentado como "Sair" — revisar fluxo e opções do menu para consistência.
5. Melhorar validações em Cliente (por exemplo, validar CPF, email) se necessário.

## Conclusão

O projeto demonstra conceitos fundamentais de orientação a objetos (herança, polimorfismo, encapsulamento) e usa estruturas de controle básicas (if/else, switch, loops) para implementar um simulador bancário simples. Existem alguns problemas de implementação que afetam a lógica (senha fixa, transferir com chaves erradas, método getSaldo retornando 0) que devem ser corrigidos para o sistema funcionar corretamente.
