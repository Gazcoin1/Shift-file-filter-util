package org.example.file;

import java.io.File;
import java.io.IOException;

/**
 * Класс для создания выходных файлов и директорий, в которых они должны находиться.
 */
public class FileCreator {

    /**
     * Метод для создания выходных файлов.
     *
     * @param outputDirectory строка с выходной директорией
     * @param fileName строка с названием файла
     */
    public void createFile(String outputDirectory, String fileName) {

        createDirectory(outputDirectory);

        File file = new File(outputDirectory, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Could not create file " + fileName);
        }
    }

    /**
     * Метод создает директорию для выходных файлов, если ее не существует.
     *
     * @param outputDirectory строка с путем директории.
     */
    private void createDirectory(String outputDirectory) {
        File file = new File(outputDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
