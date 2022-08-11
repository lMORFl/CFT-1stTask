Тестовое задание ШИФТ ЦФТ
Сортировка слиянием, реализованная согласно заданию.
Требования к программе:
Java 17
Maven 3.8.1
Сборка:
В директории с исходниками выполнить: mvn build. После этого появится файл MergeSort.jar.

Параметры программы задаются при запуске через аргументы командной строки, по порядку:
режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
тип данных (-s или -i), обязательный;
имя выходного файла, обязательное;
остальные параметры - имена входных файлов, не менее одного.
Примеры запуска из командной строки:
java -jar MergeSort.jar -a -i out.txt in.txt (для целых чисел по возрастанию)
java -jar MergeSort.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)
java -jar MergeSort.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию)