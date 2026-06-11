package controller;

import model.Cadastravel;
import model.Tecnico;
import model.Time;
import util.ArquivoUtil;
import util.Logger;

import java.util.List;

public class TimeController {

    private List<Time> times;
    private List<Tecnico> tecnicos;
    private JogadorController jogadorController;

    public TimeController(JogadorController jogadorController) {
        this.times             = ArquivoUtil.carregarTimes();
        this.tecnicos          = ArquivoUtil.carregarTecnicos();
        this.jogadorController = jogadorController;
        Logger.log("TimeController iniciado. " + times.size() + " time(s) carregado(s).");
    }

    public void cadastrarTime(String nome, String cidade) {
        try {
            if (nome == null || nome.trim().isEmpty())
                throw new IllegalArgumentException("Nome do time não pode ser vazio.");
            if (cidade == null || cidade.trim().isEmpty())
                throw new IllegalArgumentException("Cidade não pode ser vazia.");
            for (Time t : times)
                if (t.getNome().equalsIgnoreCase(nome))
                    throw new IllegalArgumentException("Já existe um time com o nome: " + nome);
            Time novoTime = new Time(nome.trim(), cidade.trim());
            times.add(novoTime);
            ArquivoUtil.salvarTimes(times);
            Logger.log("Time cadastrado: " + nome + " / " + cidade);
            System.out.println("Time cadastrado: " + novoTime);
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao cadastrar time: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTimes() {
        if (times.isEmpty()) { System.out.println("Nenhum time cadastrado."); return; }
        System.out.println("\n=== TIMES CADASTRADOS ===");
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
            System.out.println("Time removido com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao remover time: " + e.getMessage());
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
            Logger.log("ERRO ao editar time: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
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
            Logger.log("ERRO ao cadastrar técnico: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTecnicos() {
        if (tecnicos.isEmpty()) { System.out.println("Nenhum técnico cadastrado."); return; }
        System.out.println("\n=== TÉCNICOS CADASTRADOS ===");
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
            System.out.println("Técnico removido com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao remover técnico: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void associarJogadorAoTime(String nomeJogador, String nomeTime) {
        try {
            var jogador = jogadorController.buscarJogador(nomeJogador);
            Time time   = buscarTime(nomeTime);
            if (jogador == null) throw new IllegalArgumentException("Jogador não encontrado: " + nomeJogador);
            if (time == null)    throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
            time.adicionarJogador(jogador);
            ArquivoUtil.salvarTimes(times);
            Logger.log("Jogador " + nomeJogador + " associado ao time " + nomeTime);
            System.out.println("Jogador " + nomeJogador + " adicionado ao time " + nomeTime + ".");
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao associar jogador: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void associarTecnicoAoTime(String nomeTecnico, String nomeTime) {
        try {
            Tecnico tecnico = buscarTecnico(nomeTecnico);
            Time time       = buscarTime(nomeTime);
            if (tecnico == null) throw new IllegalArgumentException("Técnico não encontrado: " + nomeTecnico);
            if (time == null)    throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
            time.setTecnico(tecnico);
            ArquivoUtil.salvarTimes(times);
            Logger.log("Técnico " + nomeTecnico + " associado ao time " + nomeTime);
            System.out.println("Técnico " + nomeTecnico + " comanda o time " + nomeTime + ".");
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao associar técnico: " + e.getMessage());
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

    public List<Time> getTimes()       { return times; }
    public List<Tecnico> getTecnicos() { return tecnicos; }
}