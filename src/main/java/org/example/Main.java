package org.example;

import org.example.util.FileFilterUtil;

/**
 * Основной класс, запускающий программу
 */
public class Main {

    /**
     * Метод запускает работу программы
     *
     * @param args аргументы командной строки в виде массива строк
     */
    public static void main(String[] args) {
        FileFilterUtil fileFilterUtil = new FileFilterUtil();
        fileFilterUtil.start(args);
    }
}