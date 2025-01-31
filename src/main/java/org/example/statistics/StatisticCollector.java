package org.example.statistics;

import lombok.Getter;

/**
 * Класс для сбора статистики.
 */
@Getter
public class StatisticCollector {
    private final StatisticData statisticData = new StatisticData();

    /**
     * Метод собирает статистику по входным данным, разделяя их на числа и строки.
     *
     * @param value строка с числом в виде строки или самой строкой
     */
    public void addInStatistic(String value) {
        try {
            double numericalValue = Double.parseDouble(value);
            statisticData.addInNumericalStatistic(numericalValue);
        } catch (NumberFormatException e) {
            statisticData.addInStringStatistic(value);
        }
    }
}
