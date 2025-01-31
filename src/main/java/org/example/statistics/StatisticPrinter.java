package org.example.statistics;

import org.example.args.Args;
import java.util.Map;

/**
 * Класс предназначен для вывода статистики по файлам в консоль.
 */
public class StatisticPrinter {

    /**
     * Метод для вывода статистики по каждому типу данных в консоль.
     *
     * @param statistic мапа со статистикой по каждому типу данных
     * @param arguments значения аргументов командной строки
     */
    public static void printStatistic(Map<String, StatisticCollector> statistic, Args arguments) {
        if (arguments.isFullStatistic() && arguments.isShortStatistic()) {
            System.out.println("Incorrect statistics display parameters have been selected.");
        }
        System.out.println("Statistics:");

        for (Map.Entry<String, StatisticCollector> entry : statistic.entrySet()) {
            String dataType = entry.getKey();
            StatisticData statisticData = entry.getValue().getStatisticData();

            System.out.println('\n' + dataType);
            System.out.println("Number of values recorded: " + statisticData.getCount());

            if (arguments.isFullStatistic()) {
                if (dataType.equals("string.txt")) {
                    System.out.println("Minimum line length: " + statisticData.getMinLength());
                    System.out.println("Maximum line length: " + statisticData.getMaxLength());
                } else {
                    System.out.println("Minimum value: " + statisticData.getMin());
                    System.out.println("Maximum value: " + statisticData.getMax());
                    System.out.println("Sum: " + statisticData.getSum());
                    System.out.println("Average: " + statisticData.getAverage());
                }
            }
        }
    }
}
