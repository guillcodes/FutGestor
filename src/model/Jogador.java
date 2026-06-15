package model;

import java.io.Serializable;

public class Jogador extends Pessoa implements Cadastravel, Serializable {
    private static final long serialVersionUID = 1L;

    private String posicao;
    private int numeroCamisa;
    private int golsMarcados;

    public Jogador(String nome, int idade, String posicao, int numeroCamisa) {
        super(nome, idade);
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
        this.golsMarcados = 0;
    }

    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }
    public int getNumeroCamisa() { return numeroCamisa; }
    public void setNumeroCamisa(int numeroCamisa) { this.numeroCamisa = numeroCamisa; }
    public int getGolsMarcados() { return golsMarcados; }
    public void setGolsMarcados(int golsMarcados) { this.golsMarcados = golsMarcados; }

    @Override
    public String exibirInfo() {
        return String.format("Jogador: %s | Camisa #%d | %s | Gols: %d",
                getNome(), numeroCamisa, posicao, golsMarcados);
    }

    @Override
    public boolean validarDados() {
        if (getNome() == null || getNome().trim().isEmpty()) return false;
        if (getIdade() <= 0 || getIdade() > 100) return false;
        if (posicao == null || posicao.trim().isEmpty()) return false;
        if (numeroCamisa <= 0 || numeroCamisa > 99) return false;
        return true;
    }

    @Override
    public String toString() { return exibirInfo(); }
}