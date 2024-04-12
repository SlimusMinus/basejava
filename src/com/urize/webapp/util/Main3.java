package com.urize.webapp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество директорий
        int n = scanner.nextInt();
        scanner.nextLine(); // Пропускаем перевод строки

        // Создаем список директорий
        List<String> directories = new ArrayList<>();

        // Считываем директории и добавляем их в список
        for (int i = 0; i < n; i++) {
            String directory = scanner.nextLine();
            directories.add(directory);
        }

        // Сортируем список директорий
        Collections.sort(directories);

        // Выводим отсортированный список с отступами
        for (String directory : directories) {
            int depth = countDepth(directory);
            String indent = " ".repeat(depth);
            String[] str = directory.split("/");
            System.out.println(indent + str[str.length-1]);
        }

        scanner.close();
    }

    // Метод для подсчета глубины вложенности директории
    private static int countDepth(String directory) {
        int depth = 0;
        for (char c : directory.toCharArray()) {
            if (c == '/') {
                depth++;
            }
        }
        return depth;
    }
}
