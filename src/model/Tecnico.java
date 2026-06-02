package model;

public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    private String esquemaTaticoFavorito;

    public Tecnico(String nome, int idade, String esquemaTaticoFavorito) {
        super(nome, idade);
        this.esquemaTaticoFavorito = esquemaTaticoFavorito;
    }

    // Getters e Setters
    public String getEsquemaTaticoFavorito() { return esquemaTaticoFavorito; }
    public void setEsquemaTaticoFavorito(String esquemaTaticoFavorito) {
        this.esquemaTaticoFavorito = esquemaTaticoFavorito;
    }

    @Override
    public String toString() {
        return String.format("Técnico: %s | Esquema Favorito: %s", getNome(), esquemaTaticoFavorito);
    }
}