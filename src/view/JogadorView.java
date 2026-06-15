package view;

import controller.JogadorController;
import model.Jogador;

import java.util.List;
import java.util.Scanner;

public class JogadorView {

    private JogadorController controller;
    private Scanner scanner;

    public JogadorView(JogadorController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner    = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       GESTÃO DE JOGADORES    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Cadastrar Jogador        ║");
            System.out.println("║  2. Listar Jogadores         ║");
            System.out.println("║  3. Buscar Jogador           ║");
            System.out.println("║  4. Editar Jogador           ║");
            System.out.println("║  5. Remover Jogador          ║");
            System.out.println("║  0. Voltar                   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> editar();
                case 5 -> remover();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine().trim();
        int idade = lerInteiro("Idade: ");
        System.out.print("Posição (ex: Atacante, Goleiro): ");
        String posicao = scanner.nextLine().trim();
        int camisa = lerInteiro("Número da camisa (1-99): ");
        try {
            Jogador j = controller.cadastrarJogador(nome, idade, posicao, camisa);
            System.out.println("Jogador cadastrado: " + j.exibirInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listar() {
        List<Jogador> jogadores = controller.getJogadores();
        if (jogadores.isEmpty()) { System.out.println("Nenhum jogador cadastrado."); return; }
        System.out.println("\n=== JOGADORES CADASTRADOS ===");
        for (Jogador j : jogadores)
            System.out.println("  " + j.exibirInfo());
    }

    private void buscar() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine().trim();
        Jogador jogador = controller.buscarJogador(nome);
        if (jogador != null) System.out.println("Encontrado: " + jogador.exibirInfo());
        else System.out.println("Jogador não encontrado.");
    }

    private void editar() {
        System.out.print("Nome do jogador a editar: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Nova posição (Enter para manter): ");
        String posicao = scanner.nextLine().trim();
        int camisa = lerInteiro("Nova camisa (0 para manter): ");
        try {
            Jogador j = controller.editarJogador(nome, posicao.isEmpty() ? null : posicao, camisa);
            System.out.println("Jogador atualizado: " + j.exibirInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void remover() {
        System.out.print("Nome do jogador a remover: ");
        String nome = scanner.nextLine().trim();
        try {
            controller.removerJogador(nome);
            System.out.println("Jogador removido com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}
