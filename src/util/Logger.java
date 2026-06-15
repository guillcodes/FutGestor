package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String ARQUIVO_LOG = "log.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void log(String mensagem) {
        String linha = "[" + LocalDateTime.now().format(FORMATO) + "] " + mensagem;
        System.out.println("LOG: " + linha);
        try (FileWriter fw = new FileWriter(ARQUIVO_LOG, true)) {
            fw.write(linha + "\n");
        } catch (IOException e) {
            System.out.println("Falha ao gravar log: " + e.getMessage());
        }
    }
}