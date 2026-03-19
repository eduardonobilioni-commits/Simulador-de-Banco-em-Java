# 🏦 Simulador de Banco Nobilioni v2.0

Um sistema de simulação bancária robusto desenvolvido em **Java 25**, explorando as últimas evoluções da JVM e boas práticas de Programação Orientada a Objetos.

## 🚀 Funcionalidades

- **Persistência de Estado:** Saldo armazenado em `saldo_banco.txt` com suporte a `Locale.US` para garantir portabilidade entre sistemas (Ubuntu/Windows).
- **Log de Transações:** Histórico de extrato persistente com modo de escrita incremental (Append).
- **Polimorfismo Avançado:** Gestão de diferentes tipos de conta (Corrente/Poupança) utilizando herança e classes abstratas.
- **Segurança:** Sistema de autenticação por senha para todas as operações financeiras.

## 🛠️ Stack Tecnológica

- **Linguagem:** Java 25 (LTS ou Current)
- **Recursos:** I/O Stream, Enhanced Switch Case, e Modern File Handling.
- **Arquitetura:** MVC simplificado (Model para as contas, Controller no Simulador e View via Console).

## ⚙️ Como Executar

1. Compile o projeto: `javac org/nobilioni/banco/*.java`
2. Inicie o simulador: `java org.nobilioni.banco.SimuladorDeBanco`

---
*Desenvolvido como estudo de caso para persistência de dados e arquitetura de objetos.*