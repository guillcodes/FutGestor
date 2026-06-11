package view;

import controller.JogadorController;
import controller.TimeController;
import util.Logger;

import java.util.Scanner;

public class Menu {

    private TimeController timeController;
    private JogadorController jogadorController;
    private JogadorView jogadorView;
    private Scanner scanner;

    public Menu() {
        this.scanner           = new Scanner(System.in);
        this.jogadorController = new JogadorController();
        this.timeController    = new TimeController(jogadorController);
        this.jogadorView       = new JogadorView(jogadorController, scanner);
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     SISTEMA DE CAMPEONATO    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Cadastrar Time           ║");
            System.out.println("║  2. Listar Times             ║");
            System.out.println("║  3. Editar Time              ║");
            System.out.println("║  4. Remover Time             ║");
            System.out.println("║  5. Cadastrar Técnico        ║");
            System.out.println("║  6. Listar Técnicos          ║");
            System.out.println("║  7. Remover Técnico          ║");
            System.out.println("║  8. Gestão de Jogadores ►    ║");
            System.out.println("║  9. Associar Jogador a Time  ║");
            System.out.println("║ 10. Definir Técnico do Time  ║");
            System.out.println("║ 11. Ver Elenco do Time       ║");
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
                case 1  -> cadastrarTime();
                case 2  -> timeController.listarTimes();
                case 3  -> editarTime();
                case 4  -> removerTime();
                case 5  -> cadastrarTecnico();
                case 6  -> timeController.listarTecnicos();
                case 7  -> removerTecnico();
                case 8  -> jogadorView.exibir();
                case 9  -> associarJogador();
                case 10 -> associarTecnico();
                case 11 -> verElenco();
                case 0  -> {
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
        timeController.cadastrarTime(nome, cidade);
    }

    private void editarTime() {
        System.out.print("Nome atual do time: ");
        String nomeAntigo = scanner.nextLine().trim();
        System.out.print("Novo nome (Enter para manter): ");
        String novoNome = scanner.nextLine().trim();
        System.out.print("Nova cidade (Enter para manter): ");
        String novaCidade = scanner.nextLine().trim();
        timeController.editarTime(nomeAntigo,
                novoNome.isEmpty() ? null : novoNome,
                novaCidade.isEmpty() ? null : novaCidade);
    }

    private void removerTime() {
        System.out.print("Nome do time a remover: ");
        timeController.removerTime(scanner.nextLine().trim());
    }

    private void cadastrarTecnico() {
        System.out.print("Nome do técnico: ");
        String nome = scanner.nextLine().trim();
        int idade = lerInteiro("Idade: ");
        System.out.print("Esquema tático (ex: 4-3-3): ");
        String esquema = scanner.nextLine().trim();
        int exp = lerInteiro("Anos de experiência: ");
        timeController.cadastrarTecnico(nome, idade, esquema, exp);
    }

    private void removerTecnico() {
        System.out.print("Nome do técnico a remover: ");
        timeController.removerTecnico(scanner.nextLine().trim());
    }

    private void associarJogador() {
        System.out.print("Nome do jogador: ");
        String jogador = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        timeController.associarJogadorAoTime(jogador, time);
    }

    private void associarTecnico() {
        System.out.print("Nome do técnico: ");
        String tecnico = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        timeController.associarTecnicoAoTime(tecnico, time);
    }

    private void verElenco() {
        System.out.print("Nome do time: ");
        timeController.listarJogadoresDoTime(scanner.nextLine().trim());
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