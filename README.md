# Simulador Banco Java

Simulador-banco-java é um projeto simples em Java que demonstra conceitos de orientação a objetos (herança, polimorfismo, encapsulamento) através de um simulador bancário de linha de comando.

Funcionalidades principais

- Criar Conta Corrente e Conta Poupança (via menu interativo)
- Depositar, sacar, transferir entre contas
- Extrato simples (histórico em memória)
- Aplicar rendimento em Conta Poupança

Arquitetura / Estrutura

- Código-fonte em `org/nobilioni/banco`
- Classe principal: `org.nobilioni.banco.SimuladorDeBanco` (main)
- Classes principais: `Cliente`, `Conta` (abstract), `ContaCorrente`, `ContaPoupanca`.

Como compilar e executar (linha de comando)

1. Compilar todos os fontes e colocar classes em `out/`:

   mkdir -p out && javac -d out $(find org -name "*.java")

2. Executar a aplicação:

   java -cp out org.nobilioni.banco.SimuladorDeBanco

Observação: executar via IDE (IntelliJ/IDEA) também funciona — importe como projeto Java simples.

Problemas conhecidos e sugestões

- O construtor de `Conta` seta a senha para "0000" ignorando o parâmetro — comportamento inesperado.
- Método `transferir(...)` contém chaves/parênteses mal posicionadas que podem permitir transferências indevidas. Rever a lógica de checagem de saldo.
- `ContaPoupanca` define um `getSaldo()` privado que retorna 0 — impede operações corretas. Ajustar para usar `this.saldo` ou implementar getter correto.
- No loop do `main`, a condição de saída/numeração das opções está inconsistente (opções 5/6/7). Revisar menu.

Contribuições

Pull requests são bem-vindos. Abra uma issue descrevendo a mudança antes de implementar alterações significativas.

Licença

Adicionar licença conforme preferência (ex: MIT). 

---
Arquivo gerado automaticamente com base na análise dos fontes do projeto.
