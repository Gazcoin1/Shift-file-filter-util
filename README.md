# Утилита фильтрации содержимого файлов

---
## Версии Java и системы сборки Gradle.

- Версия Java: 23.0.2
- Версия Gradle: 8.10

## Используемые библиотеки

- [JCommander](https://jcommander.org/) 2.0

```groovy
dependencies {
    implementation 'org.jcommander:jcommander:2.0'
}
```

Библиотека используется для определения и считывания аргументов командной строки.

- [Lombok](https://projectlombok.org/) 1.18.30

```groovy
dependencies {
    implementation 'org.projectlombok:lombok:1.18.30'
    annotationProcessor 'org.projectlombok:lombok:1.18.30'
}
```

Библиотека используется для создания шаблонного кода при помощи аннотаций.

## Сборка в JAR файл

Для сборки проекта в JAR файл используется плагин [Shadow](https://gradleup.com/shadow/)

```groovy
plugins {
    id 'com.gradleup.shadow' version '8.3.0'
}
```

---

## Запуск проекта

### Клонирование и сборка

1. Для начала необходимо скачать проект или клонировать, использовав команду: 
```shell
git clone https://github.com/Gazcoin1/Shift-file-filter-util.git
```

2. Для сборки проекта в JAR файл необходимо в корне проекта выполнить одну из следующих команд:
```shell
./gradlew build
```
```shell
gradlew build
```
После выполнения команды появится файл util.jar, который расположен в директории build/libs/util.jar в корне проекта.

### Запуск

**Перед запуском вы можете остаться в корне проекта, тогда не обязательно будет указывать полные пути до файлов. Если же вы будете запускать утилиту из командной строки компьютера (cmd) или из windows PowerShell, то необходимо будет указывать полные пути до jar файла и до входных файлов и директории с выходными файлами. Далее будет описана работа программы и указаны команды для запуска из корня проекта.**

- Для запуска утилиты используйте следующую команду:
```shell
java -jar build/libs/util.jar [опции] [входные файлы через пробел]
```

### Опции

В программе доступны следующие опции:

- `-s`: вывод краткой статистики по каждому файлу в консоль. Выводится только количество записанных элементов в файл
- `-f`: вывод полной статистики. Выводятся количество записанных элементов в файл. Для чисел выводится минимальное и максимальное число в выходном файле, сумма всех чисел и их среднее в выходном файле. Для строк, помимо количества элементов в выходном файле, выводится минимальная длина строки и максимальная в выходном файле.
- `-a`: режим дополнения элементов в существующем файле. Без этой опции элементы в файлах будут перезаписываться.
- `-o [выходной путь]`: указывает путь для выходных файлов. Если будет указан несуществующая директория, то она будет создана и файлы будут записаны в нее. Если будет указан неполный путь, выходные файлы будут созданы в указанной или созданной директории в корне проекта. Неполный путь директории может начинаться как просто с названия директории, так и со знака "/". Если опция не будет указана, то файлы будут созданы в корне проекта.
- `-p [префикс к названиям файлов]`: указывает префикс к названиям файлов. Если опция не будет указана, то файлы будут создаваться с названиями типов данных, расположенных в них (integer.txt, float.txt, string.txt)

**Если путь содержит знак пробела (пример: .../dir user/213), то необходимо указывать путь в двойных кавычках для избежания ошибок при парсинге аргументов командной строки**

## Примеры работы программы
1. **Указан вывод полной статистики, выходные файлы будут созданы в директории newPath, к названию будет добавлен префикс example-, будут считаны файлы input1.txt и input2.txt**
```
java -jar build/libs/util.jar -f -o newPath -p example- input1.txt input2.txt
```

**Входные данные:**
- input1.txt
```text
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
100500
```
- input2.txt
```text
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

**Результат работы:**
- example-float.txt
```text
3.1415
-0.001
1.528535047E-25
```
- example-integer.txt
```text
45
100500
1234567890123456789
```
- example-string.txt
```text
Lorem ipsum dolor sit amet
Пример
consectetur adipiscing
тестовое задание
Нормальная форма числа с плавающей запятой
Long
```
- Статистика:
```text
Statistics:

example-string.txt
Number of values recorded: 6
Minimum value: 0.0
Maximum value: 0.0
Sum: 0.0
Average: 0.0

example-integer.txt
Number of values recorded: 3
Minimum value: 45.0
Maximum value: 1.2345678901234568E18
Sum: 1.2345678901235574E18
Average: 4.115226300411858E17

example-float.txt
Number of values recorded: 3
Minimum value: -0.001
Maximum value: 3.1415
Sum: 3.1405000000000003
Average: 1.0468333333333335
```

2. **Указан вывод короткой статистики, данные будут дополнены к существующим выходным файлам выходные файлы будут созданы в директории newPath, к названию будет добавлен префикс example-, будут считаны файлы input1.txt и input2.txt**
```
java -jar build/libs/util.jar -s -a -o newPath -p example- input1.txt input2.txt
```

**Входные данные:**
- input1.txt
```text
11111111111
abcdefg
1.1
inter12
0
```
- input2.txt
```text
Привет, мир!
1234567890
```

**Результат работы:**
- example-float.txt
```text
3.1415
-0.001
1.528535047E-25
1.1
```
- example-integer.txt
```text
45
100500
1234567890123456789
11111111111
0
1234567890
```
- example-string.txt
```text
Lorem ipsum dolor sit amet
Пример
consectetur adipiscing
тестовое задание
Нормальная форма числа с плавающей запятой
Long
abcdefg
inter12
Привет, мир!
```
- Статистика:
```text
Statistics:

example-string.txt
Number of values recorded: 3

example-integer.txt
Number of values recorded: 3

example-float.txt
Number of values recorded: 1
```

3. **Указаны только файлы input1.txt и input2.txt**
```
java -jar build/libs/util.jar input1.txt input2.txt
```

**Входные данные:**
- input1.txt
```text
11111111111
abcdefg
1.1
inter12
0
```
- input2.txt
```text
Привет, мир!
1234567890
```

**Результат работы:**
- float.txt
```text
1.1
```
- integer.txt
```text
11111111111
0
1234567890
```
- string.txt
```text
abcdefg
inter12
Привет, мир!
```
- Статистика:
```text
Statistics:

float.txt
Number of values recorded: 1

string.txt
Number of values recorded: 3

integer.txt
Number of values recorded: 3
```