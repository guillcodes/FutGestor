package model;

public class Jogador extends Pessoa {
    private static final long serialVersionUID = 1L;

    private String posicao;
    private int numeroCamisa;
    private int golsMarcados;

    // O construtor usa o super() para passar o nome e idade para a classe mãe (Pessoa)
    public Jogador(String nome, int idade, String posicao, int numeroCamisa) {
        super(nome, idade);
        this.posicao = posicao;
        this.numeroCamisa = numeroCamisa;
        this.golsMarcados = 0; // To do jogador começa o campeonato com 0 gols
    }

    // Getters e Setters
    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }

    public int getNumeroCamisa() { return numeroCamisa; }
    public void setNumeroCamisa(int numeroCamisa) { this.numeroCamisa = numeroCamisa; }

    public int getGolsMarcados() { return golsMarcados; }
    public void setGolsMarcados(int golsMarcados) { this.golsMarcados = golsMarcados; }

    // O método toString ajuda a exibir o jogador de forma elegante no console depois
    @Override
    public String toString() {
        return String.format("Jogador: %s (#%d) | Posição: %s | Gols: %d",
                getNome(), numeroCamisa, posicao, golsMarcados);
    }
}