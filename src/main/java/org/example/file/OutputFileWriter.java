package org.example.file;

import org.example.statistics.StatisticCollector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для записи данных в различные файлы для типов данных float, string и целых чисел
 * и сбора статистики по каждому типу данных.
 */
public class OutputFileWriter {
    private final FileCreator fileCreator;
    private final String outputDirectory;
    private final String prefix;
    private final boolean append;
    private BufferedWriter intWriter = null;
    private BufferedWriter floatWriter = null;
    private BufferedWriter stringWriter = null;
    private final StatisticCollector intStatisticCollector = new StatisticCollector();
    private final StatisticCollector floatStatisticCollector = new StatisticCollector();
    private final StatisticCollector stringStatisticCollector = new StatisticCollector();

    /**
     * Конструктор класса {@code OutputFileWriter}, задающий путь для выходных файлов, префикс для них,
     * режим добавления данных или их перезаписывания в файлах, а также устанавливающий зависимость для класса
     * создания выходных файлов.
     *
     * @param outputDirectory директория для выходных файлов
     * @param prefix префикс к названию выходных файлов
     * @param append булево значение, устанавливающее режим записи ({@code true} задает режим дополнения к файлам
     *               {@code false} задает режим перезаписывания файлов)
     */
    public OutputFileWriter(String outputDirectory, String prefix, boolean append) {
        this.fileCreator = new FileCreator();

        if (outputDirectory.startsWith("/")) {
            this.outputDirectory = System.getProperty("user.dir") + outputDirectory;
        } else {
            this.outputDirectory = outputDirectory;
        }
        this.prefix = prefix;
        this.append = append;
    }

    /**
     * Метод определяет тип данных строки и записывает ее в соответствующий файл.
     *
     * @param line строка из считываемого файла
     */
    public void writeFile(String line) throws IOException {
        if (isInteger(line)) {
            if (intWriter == null) {
                fileCreator.createFile(outputDirectory, getOutputFileName("integer.txt"));
                intWriter = getWriter("integer.txt");
            }

            writeLine(intWriter, intStatisticCollector, line);
        } else if (isFloat(line)) {
            if (floatWriter == null) {
                fileCreator.createFile(outputDirectory, getOutputFileName("float.txt"));
                floatWriter = getWriter("float.txt");
            }

            writeLine(floatWriter, floatStatisticCollector, line);
        } else {
            if (stringWriter == null) {
                fileCreator.createFile(outputDirectory, getOutputFileName("string.txt"));
                stringWriter = getWriter("string.txt");
            }

            writeLine(stringWriter, stringStatisticCollector, line);
        }
    }

    /**
     * Метод для получения экземпляра класса {@code BufferedWriter} для записи строк в указанный файл.
     *
     * @param fileName файл, который необходимо записывать строки
     * @return экземпляр класса {@code BufferedWriter} для записи строк в указанный файл
     */
    private BufferedWriter getWriter(String fileName) throws IOException {
        return new BufferedWriter(
                new FileWriter(
                        new File(outputDirectory, getOutputFileName(fileName)),
                        StandardCharsets.UTF_8,
                        append
                )
        );
    }

    /**
     * Метод для записи строки в файл и добавления в статистику.
     *
     * @param writer экземпляр класса {@code BufferedWriter} для определенного типа данных.
     * @param statisticCollector экземпляр класса {@code StatisticCollector} для сбора статистики по определенному типу
     *                           данных
     * @param line строка из входного файла
     */
    private void writeLine(BufferedWriter writer,
                           StatisticCollector statisticCollector,
                           String line
    ) throws IOException {
        writer.write(line);
        writer.newLine();
        statisticCollector.addInStatistic(line);
    }

    /**
     * Метод возвращает имя файла с заданным префиксом
     *
     * @param fileName имя файла
     * @return строка с именем файла с добавлением префикса
     */
    private String getOutputFileName(String fileName) {
        return prefix + fileName;
    }

    /**
     * Метод для проверки строки на то, является ли она типом данных {@code float}
     *
     * @param line строка, считанная из входного файла
     * @return {@code true} если строка является {@code float}, {@code false} если нет
     */
    private boolean isFloat(String line) {
        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод для проверки строки на то, является ли она целым числом
     *
     * @param line строка, считанная из входного файла
     * @return {@code true} если строка является целым числом, {@code false} если нет
     */
    private boolean isInteger(String line) {
        try {
            new BigInteger(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод закрывает потоки у всех экземпляров класса {@code BufferedWriter} для завершения записи строк в файлы.
     */
    public void closeWriters() {
        try {
            if (intWriter != null) {
                intWriter.close();
            }
            if (floatWriter != null) {
                floatWriter.close();
            }
            if (stringWriter != null) {
                stringWriter.close();
            }
        } catch (IOException e) {
            System.err.println("BufferedWriter closing error: " + e.getMessage());
        }
    }

    /**
     * Метод создает мапу со статистикой для каждого типа данных, где ключом является название файла, а значением
     * экземпляр класса {@code StatisticCollector}
     *
     * @return мапа со статистикой для каждого типа данных
     */
    public Map<String, StatisticCollector> getStatistic() {
        Map<String, StatisticCollector> statistic = new HashMap<>();
        statistic.put(getOutputFileName("integer.txt"), intStatisticCollector);
        statistic.put(getOutputFileName("float.txt"), floatStatisticCollector);
        statistic.put(getOutputFileName("string.txt"), stringStatisticCollector);
        return statistic;
    }
}
