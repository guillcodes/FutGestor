package view;

import controller.TimeController;
import util.Logger;

import java.util.Scanner;

/**
 * View do padrão MVC — interface com o usuário via console.
 * Chama o TimeController para todas as operações.
 */
public class Menu {

    private TimeController controller;
    private Scanner scanner;

    public Menu() {
        this.controller = new TimeController();
        this.scanner    = new Scanner(System.in);
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     SISTEMA DE CAMPEONATO    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Cadastrar Time           ║");
            System.out.println("║  2. Listar Times             ║");
            System.out.println("║  3. Cadastrar Jogador        ║");
            System.out.println("║  4. Listar Jogadores         ║");
            System.out.println("║  5. Cadastrar Técnico        ║");
            System.out.println("║  6. Listar Técnicos          ║");
            System.out.println("║  7. Adicionar Jogador a Time ║");
            System.out.println("║  8. Definir Técnico do Time  ║");
            System.out.println("║  9. Ver Elenco do Time       ║");
            System.out.println("║  0. Sair                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarTime();
                case 2 -> controller.listarTimes();
                case 3 -> cadastrarJogador();
                case 4 -> controller.listarJogadores();
                case 5 -> cadastrarTecnico();
                case 6 -> controller.listarTecnicos();
                case 7 -> associarJogador();
                case 8 -> associarTecnico();
                case 9 -> verElenco();
                case 0 -> {
                    Logger.log("Sistema encerrado pelo usuário.");
                    System.out.println("Até logo!");
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private void cadastrarTime() {
        System.out.print("Nome do time: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine().trim();
        controller.cadastrarTime(nome, cidade);
    }

    private void cadastrarJogador() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine().trim();
        int idade = lerInteiro("Idade: ");
        System.out.print("Posição (ex: Atacante, Goleiro): ");
        String posicao = scanner.nextLine().trim();
        int camisa = lerInteiro("Número da camisa: ");
        controller.cadastrarJogador(nome, idade, posicao, camisa);
    }

    private void cadastrarTecnico() {
        System.out.print("Nome do técnico: ");
        String nome = scanner.nextLine().trim();
        int idade = lerInteiro("Idade: ");
        System.out.print("Esquema tático (ex: 4-3-3): ");
        String esquema = scanner.nextLine().trim();
        int exp = lerInteiro("Anos de experiência: ");
        controller.cadastrarTecnico(nome, idade, esquema, exp);
    }

    private void associarJogador() {
        System.out.print("Nome do jogador: ");
        String jogador = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        controller.associarJogadorAoTime(jogador, time);
    }

    private void associarTecnico() {
        System.out.print("Nome do técnico: ");
        String tecnico = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        controller.associarTecnicoAoTime(tecnico, time);
    }

    private void verElenco() {
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        controller.listarJogadoresDoTime(time);
    }

    /** Lê um inteiro com tratamento de exceção. */
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
