package model;

import java.io.Serializable;

public class Partida implements Serializable {
    private static final long serialVersionUID = 1L;

    private Time timeCasa;
    private Time timeVisitante;
    private int golsCasa;
    private int golsVisitante;

    public Partida(Time timeCasa, Time timeVisitante,
                   int golsCasa, int golsVisitante) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.golsCasa = golsCasa;
        this.golsVisitante = golsVisitante;
    }

    public Time getTimeCasa() {
        return timeCasa;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public int getGolsCasa() {
        return golsCasa;
    }

    public void setGolsCasa(int golsCasa) {
        this.golsCasa = golsCasa;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    @Override
    public String toString() {
        return timeCasa.getNome() + " "
                + golsCasa + " x "
                + golsVisitante + " "
                + timeVisitante.getNome();
    }
}