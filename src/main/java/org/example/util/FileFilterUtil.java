package org.example.util;

import com.beust.jcommander.JCommander;
import org.example.args.Args;
import org.example.file.InputFileReader;
import org.example.statistics.StatisticPrinter;

/**
 * Основной класс утилиты, где выполняется парсинг аргументов командной строки, обработка файлов и вывод статистики
 */
public class FileFilterUtil {

    /**
     * Метод парсит аргументы командной строки, запускает обработку файлов и вывод статистики.
     *
     * @param args аргументы командной строки
     */
    public void start(String[] args) {
        Args arguments = new Args();

        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        InputFileReader reader = new InputFileReader(arguments);
        reader.readFiles();

        StatisticPrinter.printStatistic(reader.getStatistic(), arguments);
    }
}
