package controller;

import model.Cadastravel;
import model.Jogador;
import model.Tecnico;
import model.Time;
import util.ArquivoUtil;
import util.Logger;

import java.util.ArrayList;
import java.util.List;

public class TimeController {

    private List<Time> times;
    private List<Jogador> jogadores;
    private List<Tecnico> tecnicos;

    public TimeController() {
        this.times     = ArquivoUtil.carregarTimes();
        this.jogadores = ArquivoUtil.carregarJogadores();
        this.tecnicos  = ArquivoUtil.carregarTecnicos();
        Logger.log("Sistema iniciado. " + times.size() + " time(s) carregado(s).");
    }

    public void cadastrarTime(String nome, String cidade) {
        try {
            if (nome == null || nome.trim().isEmpty())
                throw new IllegalArgumentException("Nome do time não pode ser vazio.");
            if (cidade == null || cidade.trim().isEmpty())
                throw new IllegalArgumentException("Cidade não pode ser vazia.");
            for (Time t : times) {
                if (t.getNome().equalsIgnoreCase(nome))
                    throw new IllegalArgumentException("Já existe um time com o nome: " + nome);
            }
            Time novoTime = new Time(nome.trim(), cidade.trim());
            times.add(novoTime);
            ArquivoUtil.salvarTimes(times);
            Logger.log("Time cadastrado: " + nome);
            System.out.println("Time cadastrado: " + novoTime);
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTimes() {
        if (times.isEmpty()) { System.out.println("Nenhum time cadastrado."); return; }
        System.out.println("\n=== TIMES ===");
        for (int i = 0; i < times.size(); i++)
            System.out.println((i + 1) + ". " + times.get(i));
    }

    public Time buscarTime(String nome) {
        for (Time t : times)
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        return null;
    }

    public boolean removerTime(String nome) {
        try {
            Time t = buscarTime(nome);
            if (t == null) throw new IllegalArgumentException("Time não encontrado: " + nome);
            times.remove(t);
            ArquivoUtil.salvarTimes(times);
            Logger.log("Time removido: " + nome);
            System.out.println("Time removido.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void editarTime(String nomeAntigo, String novoNome, String novaCidade) {
        try {
            Time t = buscarTime(nomeAntigo);
            if (t == null) throw new IllegalArgumentException("Time não encontrado: " + nomeAntigo);
            if (novoNome != null && !novoNome.trim().isEmpty()) t.setNome(novoNome.trim());
            if (novaCidade != null && !novaCidade.trim().isEmpty()) t.setCidade(novaCidade.trim());
            ArquivoUtil.salvarTimes(times);
            Logger.log("Time editado: " + nomeAntigo + " → " + t.getNome());
            System.out.println("Time atualizado: " + t);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cadastrarJogador(String nome, int idade, String posicao, int numeroCamisa) {
        try {
            Jogador novo = new Jogador(nome, idade, posicao, numeroCamisa);
            if (!novo.validarDados())
                throw new IllegalArgumentException("Dados inválidos. Verifique nome, idade (1-100), posição e camisa (1-99).");
            int i = 0;
            while (i < jogadores.size()) {
                if (jogadores.get(i).getNumeroCamisa() == numeroCamisa)
                    throw new IllegalArgumentException("Já existe jogador com a camisa #" + numeroCamisa);
                i++;
            }
            jogadores.add(novo);
            ArquivoUtil.salvarJogadores(jogadores);
            Logger.log("Jogador cadastrado: " + nome);
            System.out.println("Jogador cadastrado: " + novo.exibirInfo());
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarJogadores() {
        if (jogadores.isEmpty()) { System.out.println("Nenhum jogador cadastrado."); return; }
        System.out.println("\n=== JOGADORES ===");
        for (Cadastravel c : jogadores) System.out.println("  " + c.exibirInfo());
    }

    public Jogador buscarJogador(String nome) {
        for (Jogador j : jogadores)
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        return null;
    }

    public boolean removerJogador(String nome) {
        try {
            Jogador j = buscarJogador(nome);
            if (j == null) throw new IllegalArgumentException("Jogador não encontrado: " + nome);
            jogadores.remove(j);
            ArquivoUtil.salvarJogadores(jogadores);
            Logger.log("Jogador removido: " + nome);
            System.out.println("Jogador removido.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void cadastrarTecnico(String nome, int idade, String esquema, int experiencia) {
        try {
            Tecnico novo = new Tecnico(nome, idade, esquema, experiencia);
            if (!novo.validarDados())
                throw new IllegalArgumentException("Dados inválidos. Verifique nome, idade (1-100), esquema e experiência (>=0).");
            tecnicos.add(novo);
            ArquivoUtil.salvarTecnicos(tecnicos);
            Logger.log("Técnico cadastrado: " + nome);
            System.out.println("Técnico cadastrado: " + novo.exibirInfo());
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTecnicos() {
        if (tecnicos.isEmpty()) { System.out.println("Nenhum técnico cadastrado."); return; }
        System.out.println("\n=== TÉCNICOS ===");
        for (Cadastravel c : tecnicos) System.out.println("  " + c.exibirInfo());
    }

    public Tecnico buscarTecnico(String nome) {
        for (Tecnico t : tecnicos)
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        return null;
    }

    public boolean removerTecnico(String nome) {
        try {
            Tecnico t = buscarTecnico(nome);
            if (t == null) throw new IllegalArgumentException("Técnico não encontrado: " + nome);
            tecnicos.remove(t);
            ArquivoUtil.salvarTecnicos(tecnicos);
            Logger.log("Técnico removido: " + nome);
            System.out.println("Técnico removido.");
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void associarJogadorAoTime(String nomeJogador, String nomeTime) {
        try {
            Jogador jogador = buscarJogador(nomeJogador);
            Time time = buscarTime(nomeTime);
            if (jogador == null) throw new IllegalArgumentException("Jogador não encontrado: " + nomeJogador);
            if (time == null) throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
            time.adicionarJogador(jogador);
            ArquivoUtil.salvarTimes(times);
            Logger.log(nomeJogador + " associado ao time " + nomeTime);
            System.out.println(nomeJogador + " adicionado ao time " + nomeTime + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void associarTecnicoAoTime(String nomeTecnico, String nomeTime) {
        try {
            Tecnico tecnico = buscarTecnico(nomeTecnico);
            Time time = buscarTime(nomeTime);
            if (tecnico == null) throw new IllegalArgumentException("Técnico não encontrado: " + nomeTecnico);
            if (time == null) throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
            time.setTecnico(tecnico);
            ArquivoUtil.salvarTimes(times);
            Logger.log(nomeTecnico + " associado ao time " + nomeTime);
            System.out.println("Técnico " + nomeTecnico + " comanda o time " + nomeTime + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarJogadoresDoTime(String nomeTime) {
        try {
            Time time = buscarTime(nomeTime);
            if (time == null) throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
            System.out.println("\n=== ELENCO: " + time.getNome().toUpperCase() + " ===");
            if (time.getTecnico() != null) System.out.println("Técnico: " + time.getTecnico().exibirInfo());
            System.out.println("Jogadores:");
            time.listarJogadores();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Time> getTimes()        { return times; }
    public List<Jogador> getJogadores() { return jogadores; }
    public List<Tecnico> getTecnicos()  { return tecnicos; }
}