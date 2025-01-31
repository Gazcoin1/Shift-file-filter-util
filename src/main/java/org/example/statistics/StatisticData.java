package org.example.statistics;

import lombok.Getter;

/**
 * Класс для хранения и подсчета статистики по файлам.
 */
@Getter
public class StatisticData {
    private int count;
    private double min;
    private double max;
    private double sum;
    private int maxLength;
    private int minLength;

    /**
     * Метод для добавления строки в статистику и подсчета статистики с учетом добавленной строки.
     *
     * @param value строка, добавляемая в учет статистики
     */
    public void addInStringStatistic(String value) {
        if (count == 0) {
            count++;
            maxLength = value.length();
            minLength = value.length();
        } else {
            count++;
            minLength = Math.min(minLength, value.length());
            maxLength = Math.max(maxLength, value.length());
        }
    }

    /**
     * Метод для добавления числа в статистику и подсчета статистики с учетом добавленного числа.
     *
     * @param value число, добавляемое в учет статистики
     */
    public void addInNumericalStatistic(double value) {
        if (count == 0) {
            min = value;
            max = value;
            sum = value;
            count++;
        } else {
            sum += value;
            count++;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
    }

    /**
     * Метод считает среднее значение для integer или для float в зависимости от наличия типа данных в файлах.
     *
     * @return среднее значение если есть соответствующий тип данных или 0 для отсутствующего типа данных
     */
    public double getAverage() {
        return count == 0 ? 0 : sum / count;
    }

}
