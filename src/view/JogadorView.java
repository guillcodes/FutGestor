package view;

import controller.JogadorController;

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
                case 2 -> controller.listarJogadores();
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
        controller.cadastrarJogador(nome, idade, posicao, camisa);
    }

    private void buscar() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine().trim();
        var jogador = controller.buscarJogador(nome);
        if (jogador != null) System.out.println("Encontrado: " + jogador.exibirInfo());
        else System.out.println("Jogador não encontrado.");
    }

    private void editar() {
        System.out.print("Nome do jogador a editar: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Nova posição (Enter para manter): ");
        String posicao = scanner.nextLine().trim();
        int camisa = lerInteiro("Nova camisa (0 para manter): ");
        controller.editarJogador(nome, posicao.isEmpty() ? null : posicao, camisa);
    }

    private void remover() {
        System.out.print("Nome do jogador a remover: ");
        String nome = scanner.nextLine().trim();
        controller.removerJogador(nome);
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