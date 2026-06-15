package controller;

import model.Jogador;
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

    public Time cadastrarTime(String nome, String cidade) {
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
        return novoTime;
    }

    public Time buscarTime(String nome) {
        for (Time t : times)
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        return null;
    }

    public void removerTime(String nome) {
        Time t = buscarTime(nome);
        if (t == null) throw new IllegalArgumentException("Time não encontrado: " + nome);
        times.remove(t);
        ArquivoUtil.salvarTimes(times);
        Logger.log("Time removido: " + nome);
    }

    public Time editarTime(String nomeAntigo, String novoNome, String novaCidade) {
        Time t = buscarTime(nomeAntigo);
        if (t == null) throw new IllegalArgumentException("Time não encontrado: " + nomeAntigo);
        if (novoNome != null && !novoNome.trim().isEmpty()) t.setNome(novoNome.trim());
        if (novaCidade != null && !novaCidade.trim().isEmpty()) t.setCidade(novaCidade.trim());
        ArquivoUtil.salvarTimes(times);
        Logger.log("Time editado: " + nomeAntigo + " → " + t.getNome());
        return t;
    }

    public Tecnico cadastrarTecnico(String nome, int idade, String esquema, int experiencia) {
        Tecnico novo = new Tecnico(nome, idade, esquema, experiencia);
        if (!novo.validarDados())
            throw new IllegalArgumentException("Dados inválidos. Verifique nome, idade (1-100), esquema e experiência (>=0).");
        tecnicos.add(novo);
        ArquivoUtil.salvarTecnicos(tecnicos);
        Logger.log("Técnico cadastrado: " + nome);
        return novo;
    }

    public Tecnico buscarTecnico(String nome) {
        for (Tecnico t : tecnicos)
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        return null;
    }

    public void removerTecnico(String nome) {
        Tecnico t = buscarTecnico(nome);
        if (t == null) throw new IllegalArgumentException("Técnico não encontrado: " + nome);
        tecnicos.remove(t);
        ArquivoUtil.salvarTecnicos(tecnicos);
        Logger.log("Técnico removido: " + nome);
    }

    public void associarJogadorAoTime(String nomeJogador, String nomeTime) {
        Jogador jogador = jogadorController.buscarJogador(nomeJogador);
        Time time       = buscarTime(nomeTime);
        if (jogador == null) throw new IllegalArgumentException("Jogador não encontrado: " + nomeJogador);
        if (time == null)    throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
        time.adicionarJogador(jogador);
        ArquivoUtil.salvarTimes(times);
        Logger.log("Jogador " + nomeJogador + " associado ao time " + nomeTime);
    }

    public void associarTecnicoAoTime(String nomeTecnico, String nomeTime) {
        Tecnico tecnico = buscarTecnico(nomeTecnico);
        Time time       = buscarTime(nomeTime);
        if (tecnico == null) throw new IllegalArgumentException("Técnico não encontrado: " + nomeTecnico);
        if (time == null)    throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
        time.setTecnico(tecnico);
        ArquivoUtil.salvarTimes(times);
        Logger.log("Técnico " + nomeTecnico + " associado ao time " + nomeTime);
    }

    public Time getElenco(String nomeTime) {
        Time time = buscarTime(nomeTime);
        if (time == null) throw new IllegalArgumentException("Time não encontrado: " + nomeTime);
        return time;
    }

    public List<Time> getTimes()       { return times; }
    public List<Tecnico> getTecnicos() { return tecnicos; }

}
