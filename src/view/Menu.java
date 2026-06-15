package view;

import controller.JogadorController;
import controller.TimeController;
import controller.PartidaController;
import controller.CampeonatoController;
import model.Jogador;
import model.TabelaClassificacao;
import model.Tecnico;
import model.Time;
import util.Logger;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private TimeController timeController;
    private JogadorController jogadorController;
    private JogadorView jogadorView;
    private Scanner scanner;
    private PartidaController partidaController;
    private CampeonatoController campeonatoController;

    public Menu() {
        this.scanner           = new Scanner(System.in);
        this.jogadorController = new JogadorController();
        this.timeController    = new TimeController(jogadorController);
        this.jogadorView       = new JogadorView(jogadorController, scanner);
        this.partidaController =
                new PartidaController(timeController);

        this.campeonatoController =
                new CampeonatoController(
                        partidaController,
                        timeController
                );
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
            System.out.println("║ 12. Cadastrar Partida        ║");
            System.out.println("║ 13. Listar Partidas          ║");
            System.out.println("║ 14. Classificação            ║");
            System.out.println("║ 15. Artilheiros              ║");
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
                case 2  -> listarTimes();
                case 3  -> editarTime();
                case 4  -> removerTime();
                case 5  -> cadastrarTecnico();
                case 6  -> listarTecnicos();
                case 7  -> removerTecnico();
                case 8  -> jogadorView.exibir();
                case 9  -> associarJogador();
                case 10 -> associarTecnico();
                case 11 -> verElenco();
                case 12 -> cadastrarPartida();
                case 13 -> listarPartidas();
                case 14 -> mostrarClassificacao();
                case 15 -> mostrarArtilheiros();
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
        try {
            Time t = timeController.cadastrarTime(nome, cidade);
            System.out.println("Time cadastrado: " + t);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTimes() {
        List<Time> times = timeController.getTimes();
        if (times.isEmpty()) { System.out.println("Nenhum time cadastrado."); return; }
        System.out.println("\n=== TIMES CADASTRADOS ===");
        for (int i = 0; i < times.size(); i++)
            System.out.println((i + 1) + ". " + times.get(i));
    }

    private void editarTime() {
        System.out.print("Nome atual do time: ");
        String nomeAntigo = scanner.nextLine().trim();
        System.out.print("Novo nome (Enter para manter): ");
        String novoNome = scanner.nextLine().trim();
        System.out.print("Nova cidade (Enter para manter): ");
        String novaCidade = scanner.nextLine().trim();
        try {
            Time t = timeController.editarTime(nomeAntigo,
                    novoNome.isEmpty() ? null : novoNome,
                    novaCidade.isEmpty() ? null : novaCidade);
            System.out.println("Time atualizado: " + t);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerTime() {
        System.out.print("Nome do time a remover: ");
        String nome = scanner.nextLine().trim();
        try {
            timeController.removerTime(nome);
            System.out.println("Time removido com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarTecnico() {
        System.out.print("Nome do técnico: ");
        String nome = scanner.nextLine().trim();
        int idade = lerInteiro("Idade: ");
        System.out.print("Esquema tático (ex: 4-3-3): ");
        String esquema = scanner.nextLine().trim();
        int exp = lerInteiro("Anos de experiência: ");
        try {
            Tecnico t = timeController.cadastrarTecnico(nome, idade, esquema, exp);
            System.out.println("Técnico cadastrado: " + t.exibirInfo());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTecnicos() {
        List<Tecnico> tecnicos = timeController.getTecnicos();
        if (tecnicos.isEmpty()) { System.out.println("Nenhum técnico cadastrado."); return; }
        System.out.println("\n=== TÉCNICOS CADASTRADOS ===");
        for (Tecnico t : tecnicos) System.out.println("  " + t.exibirInfo());
    }

    private void removerTecnico() {
        System.out.print("Nome do técnico a remover: ");
        String nome = scanner.nextLine().trim();
        try {
            timeController.removerTecnico(nome);
            System.out.println("Técnico removido com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void associarJogador() {
        System.out.print("Nome do jogador: ");
        String jogador = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        try {
            timeController.associarJogadorAoTime(jogador, time);
            System.out.println("Jogador " + jogador + " adicionado ao time " + time + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void associarTecnico() {
        System.out.print("Nome do técnico: ");
        String tecnico = scanner.nextLine().trim();
        System.out.print("Nome do time: ");
        String time = scanner.nextLine().trim();
        try {
            timeController.associarTecnicoAoTime(tecnico, time);
            System.out.println("Técnico " + tecnico + " comanda o time " + time + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void verElenco() {
        System.out.print("Nome do time: ");
        String nome = scanner.nextLine().trim();
        try {
            Time time = timeController.getElenco(nome);
            System.out.println("\n=== ELENCO: " + time.getNome().toUpperCase() + " ===");
            if (time.getTecnico() != null)
                System.out.println("Técnico: " + time.getTecnico().exibirInfo());
            System.out.println("Jogadores:");
            if (time.getJogadores().isEmpty()) {
                System.out.println("  Nenhum jogador.");
            } else {
                for (Jogador j : time.getJogadores())
                    System.out.println("  " + j.exibirInfo());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cadastrarPartida() {
        System.out.print("Time da casa: ");
        String casa = scanner.nextLine().trim();
        System.out.print("Time visitante: ");
        String visitante = scanner.nextLine().trim();
        int golsCasa = lerInteiro("Gols da casa: ");
        int golsVisitante = lerInteiro("Gols do visitante: ");
        try {
            partidaController.cadastrarPartida(casa, visitante, golsCasa, golsVisitante);
            System.out.println("Partida registrada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPartidas() {
        var partidas = partidaController.getPartidas();
        if (partidas.isEmpty()) { System.out.println("Nenhuma partida cadastrada."); return; }
        System.out.println("\n=== PARTIDAS ===");
        for (int i = 0; i < partidas.size(); i++)
            System.out.println((i + 1) + ". " + partidas.get(i));
    }

    private void mostrarClassificacao() {
        List<TabelaClassificacao> tabela = campeonatoController.gerarClassificacao();
        if (tabela.isEmpty()) { System.out.println("Nenhum time cadastrado."); return; }
        System.out.println("\n=== CLASSIFICAÇÃO ===");
        int pos = 1;
        for (TabelaClassificacao c : tabela) {
            System.out.println(pos + "º - " + c);
            pos++;
        }
    }

    private void mostrarArtilheiros() {
        List<Jogador> artilheiros = campeonatoController.gerarArtilheiros();
        if (artilheiros.isEmpty()) { System.out.println("Nenhum gol marcado no campeonato."); return; }
        System.out.println("\n=== ARTILHEIROS ===");
        for (Jogador j : artilheiros)
            System.out.println(j.getNome() + " - " + j.getGolsMarcados() + " gol(s)");
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
