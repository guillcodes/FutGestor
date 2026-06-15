package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Time implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cidade;
    private List<Jogador> jogadores;
    private Tecnico tecnico;

    public Time(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
        this.jogadores = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public List<Jogador> getJogadores() { return jogadores; }
    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    public void adicionarJogador(Jogador jogador) { jogadores.add(jogador); }

    public boolean removerJogador(int numeroCamisa) {
        return jogadores.removeIf(j -> j.getNumeroCamisa() == numeroCamisa);
    }

    public Jogador buscarJogadorPorNome(String nome) {
        for (Jogador j : jogadores) {
            if (j.getNome().equalsIgnoreCase(nome)) return j;
        }
        return null;
    }

    public void listarJogadores() {
        if (jogadores.isEmpty()) { System.out.println("Nenhum jogador."); return; }
        for (Jogador j : jogadores) System.out.println("  " + j.exibirInfo());
    }

    public void listarJogadores(String posicao) {
        boolean encontrou = false;
        for (Jogador j : jogadores) {
            if (j.getPosicao().equalsIgnoreCase(posicao)) {
                System.out.println("  " + j.exibirInfo());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum jogador na posição: " + posicao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time outro = (Time) obj;
        return nome != null && nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome == null ? null : nome.toLowerCase());
    }

    @Override
    public String toString() {
        String nomeTecnico = (tecnico != null) ? tecnico.getNome() : "sem técnico";
        return String.format("Time: %s (%s) | Técnico: %s | %d jogador(es)",
                nome, cidade, nomeTecnico, jogadores.size());
    }
}