package controller;

import model.Jogador;
import model.Partida;
import model.TabelaClassificacao;
import model.Time;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CampeonatoController {

    private PartidaController partidaController;
    private TimeController timeController;

    public CampeonatoController(PartidaController partidaController,
                                TimeController timeController) {

        this.partidaController = partidaController;
        this.timeController = timeController;
    }

    public List<TabelaClassificacao> gerarClassificacao() {

        List<TabelaClassificacao> tabela = new ArrayList<>();

        for (Time time : timeController.getTimes()) {
            tabela.add(new TabelaClassificacao(time));
        }

        for (Partida partida : partidaController.getPartidas()) {

            TabelaClassificacao casa =
                    buscarClassificacao(tabela, partida.getTimeCasa());

            TabelaClassificacao visitante =
                    buscarClassificacao(tabela, partida.getTimeVisitante());

            if (casa == null || visitante == null) {
                continue;
            }

            casa.adicionarGols(partida.getGolsCasa(), partida.getGolsVisitante());
            visitante.adicionarGols(partida.getGolsVisitante(), partida.getGolsCasa());

            if (partida.getGolsCasa() > partida.getGolsVisitante()) {
                casa.adicionarVitoria();
                visitante.adicionarDerrota();
            }
            else if (partida.getGolsCasa() < partida.getGolsVisitante()) {
                visitante.adicionarVitoria();
                casa.adicionarDerrota();
            }
            else {
                casa.adicionarEmpate();
                visitante.adicionarEmpate();
            }
        }

        tabela.sort(
                Comparator.comparingInt(TabelaClassificacao::getPontos)
                        .thenComparingInt(TabelaClassificacao::getSaldoGols)
                        .thenComparingInt(TabelaClassificacao::getGolsPro)
                        .reversed()
        );

        return tabela;
    }

    private TabelaClassificacao buscarClassificacao(
            List<TabelaClassificacao> tabela,
            Time time) {

        for (TabelaClassificacao c : tabela) {
            if (c.getTime().equals(time)) {
                return c;
            }
        }

        return null;
    }

    public List<Jogador> gerarArtilheiros() {

        List<Jogador> jogadores = new ArrayList<>();

        for (Time t : timeController.getTimes()) {
            for (Jogador j : t.getJogadores()) {
                if (j.getGolsMarcados() > 0) {
                    jogadores.add(j);
                }
            }
        }

        jogadores.sort(
                Comparator.comparingInt(Jogador::getGolsMarcados)
                        .reversed()
        );

        return jogadores;
    }
}
