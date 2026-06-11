package controller;

import model.Cadastravel;
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
            Logger.log("Jogador cadastrado: " + nome + " | #" + numeroCamisa);
            System.out.println("Jogador cadastrado: " + novo.exibirInfo());

        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao cadastrar jogador: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarJogadores() {
        if (jogadores.isEmpty()) { System.out.println("Nenhum jogador cadastrado."); return; }
        System.out.println("\n=== JOGADORES CADASTRADOS ===");
        for (Cadastravel c : jogadores)
            System.out.println("  " + c.exibirInfo());
    }

    public Jogador buscarJogador(String nome) {
        for (Jogador j : jogadores)
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        return null;
    }

    public void editarJogador(String nome, String novaPosicao, int novaCamisa) {
        try {
            Jogador j = buscarJogador(nome);
            if (j == null) throw new IllegalArgumentException("Jogador não encontrado: " + nome);
            if (novaPosicao != null && !novaPosicao.trim().isEmpty()) j.setPosicao(novaPosicao.trim());
            if (novaCamisa > 0 && novaCamisa <= 99) j.setNumeroCamisa(novaCamisa);
            ArquivoUtil.salvarJogadores(jogadores);
            Logger.log("Jogador editado: " + nome);
            System.out.println("Jogador atualizado: " + j.exibirInfo());
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao editar jogador: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public boolean removerJogador(String nome) {
        try {
            Jogador j = buscarJogador(nome);
            if (j == null) throw new IllegalArgumentException("Jogador não encontrado: " + nome);
            jogadores.remove(j);
            ArquivoUtil.salvarJogadores(jogadores);
            Logger.log("Jogador removido: " + nome);
            System.out.println("Jogador removido com sucesso.");
            return true;
        } catch (IllegalArgumentException e) {
            Logger.log("ERRO ao remover jogador: " + e.getMessage());
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public List<Jogador> getJogadores() { return jogadores; }
}