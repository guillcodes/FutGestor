package controller;

import model.Partida;
import model.Time;
import util.ArquivoUtil;
import util.Logger;

import java.util.List;

public class PartidaController {

    private List<Partida> partidas;
    private TimeController timeController;

    public PartidaController(TimeController timeController) {
        this.timeController = timeController;
        this.partidas = ArquivoUtil.carregarPartidas();

        Logger.log("PartidaController iniciado. "
                + partidas.size()
                + " partida(s) carregada(s).");
    }

    public Partida cadastrarPartida(String timeCasa,
                                    String timeVisitante,
                                    int golsCasa,
                                    int golsVisitante) {

        Time casa = timeController.buscarTime(timeCasa);
        Time visitante = timeController.buscarTime(timeVisitante);

        if (casa == null)
            throw new IllegalArgumentException("Time não encontrado: " + timeCasa);

        if (visitante == null)
            throw new IllegalArgumentException("Time não encontrado: " + timeVisitante);

        if (casa.equals(visitante))
            throw new IllegalArgumentException("Um time não pode jogar contra ele mesmo.");

        if (golsCasa < 0 || golsVisitante < 0)
            throw new IllegalArgumentException("Gols não podem ser negativos.");

        Partida partida =
                new Partida(casa, visitante, golsCasa, golsVisitante);

        partidas.add(partida);
        ArquivoUtil.salvarPartidas(partidas);
        Logger.log("Partida cadastrada: " + partida);

        return partida;
    }

    public void removerPartida(int indice) {
        if (indice < 0 || indice >= partidas.size())
            throw new IllegalArgumentException("Índice inválido.");

        Partida removida = partidas.remove(indice);
        ArquivoUtil.salvarPartidas(partidas);
        Logger.log("Partida removida: " + removida);
    }

    public List<Partida> getPartidas() {
        return partidas;
    }
}
