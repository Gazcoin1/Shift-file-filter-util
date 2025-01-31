package org.example.args;

import com.beust.jcommander.Parameter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, задающий аргументы командной строки и допустимые параметры и их начальные значения.
 */
@Getter
public class Args {
    @Parameter(names = "-s")
    private boolean shortStatistic = false;

    @Parameter(names = "-f")
    private boolean fullStatistic = false;

    @Parameter(names = "-a")
    private boolean append = false;

    @Parameter(names = "-o")
    private String outputDir = System.getProperty("user.dir");

    @Parameter(names = "-p")
    private String prefix = "";

    @Parameter
    private List<String> files = new ArrayList<>();
}
