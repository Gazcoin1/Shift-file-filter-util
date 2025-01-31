package org.example.file;

import org.example.args.Args;
import org.example.statistics.StatisticCollector;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Класс предназначен для чтения строк из входных файлов.
 */
public class InputFileReader {
    private final OutputFileWriter outputFileWriter;
    private final List<String> fileNames;

    /**
     * Конструктор класса {@code InputFileReader}, задающий зависимости и задающий список файлов для чтения.
     *
     * @param args аргументы командной строки
     */
    public InputFileReader(Args args) {
        this.outputFileWriter = new OutputFileWriter(args.getOutputDir(), args.getPrefix(), args.isAppend());
        this.fileNames = args.getFiles();
    }

    /**
     * Метод считывает все файлы из списка файлов.
     */
    public void readFiles() {
        for (String fileName : fileNames) {
            Path path = Paths.get(fileName);
            try {
                BufferedReader reader = Files.newBufferedReader(path);
                readFileLine(reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        outputFileWriter.closeWriters();
    }

    /**
     * Метод считывает файл построчно и передает строку методу для записи ее в выходной файл.
     *
     * @param reader экземпляр класса {@code BufferedReader} со считываемым файлом.
     */
    public void readFileLine(BufferedReader reader) {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                outputFileWriter.writeFile(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading and writing file: " + e.getMessage());
        }
    }

    /**
     * Метод возвращает статистику по всем типам данных.
     *
     * @return мапа со статистикой по всем типам данных
     */
    public Map<String, StatisticCollector> getStatistic() {
        return outputFileWriter.getStatistic();
    }
}
