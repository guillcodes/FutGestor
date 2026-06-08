package model;

import java.io.Serializable;

public class Tecnico extends Pessoa implements Cadastravel, Serializable {
    private static final long serialVersionUID = 1L;

    private String esquemaTatico;
    private int anosExperiencia;

    public Tecnico(String nome, int idade, String esquemaTatico, int anosExperiencia) {
        super(nome, idade);
        this.esquemaTatico = esquemaTatico;
        this.anosExperiencia = anosExperiencia;
    }

    public String getEsquemaTatico() { return esquemaTatico; }
    public void setEsquemaTatico(String esquemaTatico) { this.esquemaTatico = esquemaTatico; }
    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    @Override
    public String exibirInfo() {
        return String.format("Técnico: %s | Esquema: %s | %d anos de experiência",
                getNome(), esquemaTatico, anosExperiencia);
    }

    @Override
    public boolean validarDados() {
        if (getNome() == null || getNome().trim().isEmpty()) return false;
        if (getIdade() <= 0 || getIdade() > 100) return false;
        if (esquemaTatico == null || esquemaTatico.trim().isEmpty()) return false;
        if (anosExperiencia < 0) return false;
        return true;
    }

    @Override
    public String toString() { return exibirInfo(); }
}