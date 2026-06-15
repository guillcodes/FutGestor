package controller;

import model.Jogador;
import util.ArquivoUtil;
import util.Logger;

import java.util.List;

public class JogadorController {

    private List<Jogador> jogadores;

    public JogadorController() {
        this.jogadores = ArquivoUtil.carregarJogadores();
        Logger.log("JogadorController iniciado. " + jogadores.size() + " jogador(es) carregado(s).");
    }

    public Jogador cadastrarJogador(String nome, int idade, String posicao, int numeroCamisa) {
        Jogador novo = new Jogador(nome, idade, posicao, numeroCamisa);

        if (!novo.validarDados())
            throw new IllegalArgumentException("Dados inválidos. Verifique nome, idade (1-100), posição e camisa (1-99).");

        for (Jogador j : jogadores)
            if (j.getNumeroCamisa() == numeroCamisa)
                throw new IllegalArgumentException("Já existe jogador com a camisa #" + numeroCamisa);

        jogadores.add(novo);
        ArquivoUtil.salvarJogadores(jogadores);
        Logger.log("Jogador cadastrado: " + nome + " | #" + numeroCamisa);
        return novo;
    }

    public Jogador buscarJogador(String nome) {
        for (Jogador j : jogadores)
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        return null;
    }

    public Jogador editarJogador(String nome, String novaPosicao, int novaCamisa) {
        Jogador j = buscarJogador(nome);
        if (j == null) throw new IllegalArgumentException("Jogador não encontrado: " + nome);
        if (novaPosicao != null && !novaPosicao.trim().isEmpty()) j.setPosicao(novaPosicao.trim());
        if (novaCamisa > 0 && novaCamisa <= 99) j.setNumeroCamisa(novaCamisa);
        ArquivoUtil.salvarJogadores(jogadores);
        Logger.log("Jogador editado: " + nome);
        return j;
    }

    public void removerJogador(String nome) {
        Jogador j = buscarJogador(nome);
        if (j == null) throw new IllegalArgumentException("Jogador não encontrado: " + nome);
        jogadores.remove(j);
        ArquivoUtil.salvarJogadores(jogadores);
        Logger.log("Jogador removido: " + nome);
    }

    public List<Jogador> getJogadores() { return jogadores; }
}
