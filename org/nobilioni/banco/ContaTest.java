package org.nobilioni.banco;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nobilioni.banco.Cliente;
import org.nobilioni.banco.Conta;
import org.nobilioni.banco.ContaCorrente;
import org.nobilioni.banco.ContaPoupanca;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class ContaTest {

    @Test
    void testeDepositoESaque() {
        // 1. Preparação
        Cliente titular = new Cliente();
        ContaCorrente cc = new ContaCorrente(titular, "1234");

        // 2. Ação: Precisa depositar para o saldo não ser zero!
        cc.depositar(500.0);
        cc.sacar(200.0);

        // 3. Verificação: 500 - 200 tem que ser 300
        assertEquals(300.0, cc.getSaldo(), "O saldo deveria ser 300 após o saque");
    }

    @Test
    void deveRealizarSaqueComLimiteEspecial() {
        // Arrange (Organiza o cenário)
        Cliente titular = new Cliente();
        titular.setNome("Eduardo");
        ContaCorrente cc = new ContaCorrente(titular, "1234");
        cc.depositar(100.0);

        // Act (Executa a ação)
        cc.sacar(200.0); // Ele tem 100 de saldo + 500 de limite

        // Assert (Verifica se o resultado é o esperado)
        // O saldo deve ser -100.0
        assertEquals(-100.0, cc.getSaldo(), "O saldo deveria ser negativo em 100");
    }

    @Test
    void naoDeveSacarAcimaDoLimite() {
        Cliente titular = new Cliente();
        ContaCorrente cc = new ContaCorrente(titular, "1234");

        // Tentando sacar 700 (mais que os 500 de limite padrão)
        cc.sacar(700.0);

        assertEquals(0.0, cc.getSaldo(), "O saldo não deveria ter mudado");
    }


    @Test
    void deveTransferirEntreContas() {
        // Preparando as contas
        Cliente edu = new Cliente();
        edu.setNome("Eduardo");
        ContaCorrente cc = new ContaCorrente(edu, "1234");
        cc.depositar(500.0);

        Cliente recebedor = new Cliente();
        recebedor.setNome("Recebedor");
        ContaPoupanca cp = new ContaPoupanca(recebedor, "9999");

        // Ação
        cc.transferir(100.0, cp);

        // Verificação
        assertEquals(400.0, cc.getSaldo(), "O saldo da CC deveria ser 400");
        assertEquals(100.0, cp.getSaldo(), "O saldo da CP deveria ser 100");
    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void mostrarSaldo() {
    }

    @org.junit.jupiter.api.Test
    void depositar() {
    }

    @org.junit.jupiter.api.Test
    void sacar() {
    }

    @org.junit.jupiter.api.Test
    void testSacar() {
    }

    @org.junit.jupiter.api.Test
    void transferir() {
    }

    @org.junit.jupiter.api.Test
    void mostrarExtrato() {
    }

    @org.junit.jupiter.api.Test
    void mostrarMenu() {
    }

    @org.junit.jupiter.api.Test
    void getConta() {
    }

    @org.junit.jupiter.api.Test
    void setConta() {
    }

    @org.junit.jupiter.api.Test
    void getAgencia() {
    }

    @org.junit.jupiter.api.Test
    void setAgencia() {
    }

    @org.junit.jupiter.api.Test
    void getSenha() {
    }

    @org.junit.jupiter.api.Test
    void setSenha() {
    }

    @org.junit.jupiter.api.Test
    void testTransferir() {
    }

    void main() {
    }
}

