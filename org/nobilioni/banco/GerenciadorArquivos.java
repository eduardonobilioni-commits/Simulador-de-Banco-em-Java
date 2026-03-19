package org.nobilioni.banco;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class GerenciadorArquivos {

    private static final String ARQUIVO_SALDO = "saldo_banco.txt";
    private static final String ARQUIVO_EXTRATO = "extrato_banco.txt";

    /**
     * SALVAR SALDO: Sobrescreve o arquivo com o valor mais atual.
     */
    public static void salvarSaldo(double saldo) {
        // O try-with-resources garante que o arquivo seja fechado e salvo no disco
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_SALDO))) {
            // Usamos printf com Locale.US para garantir que salve "100.50" e não "100,50"
            writer.printf(Locale.US, "%.2f", saldo);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Erro crítico ao salvar saldo: " + e.getMessage());
        }
    }

    /**
     * LER SALDO: Busca o valor no arquivo. Se não existir ou der erro, retorna 0.
     */
    public static double lerSaldo() {
        File arquivo = new File(ARQUIVO_SALDO);

        if (!arquivo.exists()) {
            return 0.0;
        }

        try (Scanner scanner = new Scanner(arquivo)) {
            // ESSENCIAL: Faz o Scanner esperar um ponto (.) em vez de vírgula (,)
            scanner.useLocale(Locale.US);

            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado, iniciando com saldo zero.");
        } catch (Exception e) {
            System.err.println("Erro ao processar leitura do saldo: " + e.getMessage());
        }
        return 0.0;
    }

    /**
     * SALVAR NO EXTRATO: Adiciona uma linha ao final do arquivo (Modo Append).
     */
    public static void salvarNoExtrato(String transacao) {
        // O parâmetro 'true' no FileWriter é o que impede de apagar o histórico anterior
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_EXTRATO, true))) {
            writer.println(transacao);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Erro ao gravar no extrato: " + e.getMessage());
        }
    }
}