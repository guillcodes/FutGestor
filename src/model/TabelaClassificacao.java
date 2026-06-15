package model;

import java.io.Serializable;

public class TabelaClassificacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private Time time;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int pontos;
    private int golsPro;
    private int golsContra;

    public TabelaClassificacao(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getPontos() {
        return pontos;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public int getSaldoGols() {
        return golsPro - golsContra;
    }

    public void adicionarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void adicionarEmpate() {
        empates++;
        pontos++;
    }

    public void adicionarDerrota() {
        derrotas++;
    }

    public void adicionarGols(int marcados, int sofridos) {
        golsPro += marcados;
        golsContra += sofridos;
    }

    @Override
    public String toString() {
        return String.format(
                "%s | %d pts | V:%d E:%d D:%d | SG:%d",
                time.getNome(),
                pontos,
                vitorias,
                empates,
                derrotas,
                getSaldoGols()
        );
    }
}
