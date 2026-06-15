package util;

import model.Jogador;
import model.Tecnico;
import model.Time;
import model.Partida;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String ARQUIVO_TIMES     = "times.dat";
    private static final String ARQUIVO_JOGADORES = "jogadores.dat";
    private static final String ARQUIVO_TECNICOS  = "tecnicos.dat";
    private static final String TXT_TIMES         = "times.txt";
    private static final String TXT_JOGADORES     = "jogadores.txt";
    private static final String TXT_TECNICOS      = "tecnicos.txt";
    private static final String ARQUIVO_PARTIDAS = "partidas.dat";
    private static final String TXT_PARTIDAS = "partidas.txt";

    public static void salvarTimes(List<Time> times) {
        salvarObjeto(times, ARQUIVO_TIMES);
        exportarTimesTxt(times);
        Logger.log("Times salvos: " + times.size() + " registro(s).");
    }

    @SuppressWarnings("unchecked")
    public static List<Time> carregarTimes() {
        List<Time> lista = (List<Time>) carregarObjeto(ARQUIVO_TIMES);
        return lista != null ? lista : new ArrayList<>();
    }

    public static void salvarJogadores(List<Jogador> jogadores) {
        salvarObjeto(jogadores, ARQUIVO_JOGADORES);
        exportarJogadoresTxt(jogadores);
        Logger.log("Jogadores salvos: " + jogadores.size() + " registro(s).");
    }

    @SuppressWarnings("unchecked")
    public static List<Jogador> carregarJogadores() {
        List<Jogador> lista = (List<Jogador>) carregarObjeto(ARQUIVO_JOGADORES);
        return lista != null ? lista : new ArrayList<>();
    }

    public static void salvarTecnicos(List<Tecnico> tecnicos) {
        salvarObjeto(tecnicos, ARQUIVO_TECNICOS);
        exportarTecnicosTxt(tecnicos);
        Logger.log("Técnicos salvos: " + tecnicos.size() + " registro(s).");
    }

    @SuppressWarnings("unchecked")
    public static List<Tecnico> carregarTecnicos() {
        List<Tecnico> lista = (List<Tecnico>) carregarObjeto(ARQUIVO_TECNICOS);
        return lista != null ? lista : new ArrayList<>();
    }

    public static void exportarTimesTxt(List<Time> times) {
        try (FileWriter fw = new FileWriter(TXT_TIMES)) {
            fw.write("=== TIMES ===\n");
            for (Time t : times) {
                fw.write("Nome: " + t.getNome() + "\n");
                fw.write("Cidade: " + t.getCidade() + "\n");
                if (t.getTecnico() != null) fw.write("Técnico: " + t.getTecnico().getNome() + "\n");
                fw.write("Jogadores: " + t.getJogadores().size() + "\n");
                fw.write("---\n");
            }
        } catch (IOException e) {
            Logger.log("ERRO ao exportar times.txt: " + e.getMessage());
        }
    }

    public static void exportarJogadoresTxt(List<Jogador> jogadores) {
        try (FileWriter fw = new FileWriter(TXT_JOGADORES)) {
            fw.write("=== JOGADORES ===\n");
            for (Jogador j : jogadores) fw.write(j.exibirInfo() + "\n");
        } catch (IOException e) {
            Logger.log("ERRO ao exportar jogadores.txt: " + e.getMessage());
        }
    }

    public static void exportarTecnicosTxt(List<Tecnico> tecnicos) {
        try (FileWriter fw = new FileWriter(TXT_TECNICOS)) {
            fw.write("=== TÉCNICOS ===\n");
            for (Tecnico t : tecnicos) fw.write(t.exibirInfo() + "\n");
        } catch (IOException e) {
            Logger.log("ERRO ao exportar tecnicos.txt: " + e.getMessage());
        }
    }

    private static void salvarObjeto(Object objeto, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            Logger.log("ERRO ao salvar " + nomeArquivo + ": " + e.getMessage());
        }
    }

    private static Object carregarObjeto(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Logger.log("ERRO ao carregar " + nomeArquivo + ": " + e.getMessage());
            return null;
        }
    }

    public static void salvarPartidas(List<Partida> partidas) {
        salvarObjeto(partidas, ARQUIVO_PARTIDAS);
        exportarPartidasTxt(partidas);
        Logger.log("Partidas salvas: " + partidas.size() + " registro(s).");
    }

    @SuppressWarnings("unchecked")
    public static List<Partida> carregarPartidas() {
        List<Partida> lista = (List<Partida>) carregarObjeto(ARQUIVO_PARTIDAS);
        return lista != null ? lista : new ArrayList<>();
    }

    public static void exportarPartidasTxt(List<Partida> partidas) {
        try (FileWriter fw = new FileWriter(TXT_PARTIDAS)) {
            fw.write("=== PARTIDAS ===\n");

            for (Partida p : partidas) {
                fw.write(p.toString() + "\n");
            }

        } catch (IOException e) {
            Logger.log("ERRO ao exportar partidas.txt: " + e.getMessage());
        }
    }
}