package com.urize.webapp.util;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем размеры леса
        int n = scanner.nextInt();

        // Создаем массив для представления леса
        char[][] forest = new char[n][3];

        // Считываем лес
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                forest[i][j] = scanner.next().charAt(0);
            }
        }

        // Находим максимальное количество грибов
        int maxMushrooms = getMaxMushrooms(forest);

        System.out.println(maxMushrooms);

        scanner.close();
    }

    // Метод для нахождения максимального количества грибов
    private static int getMaxMushrooms(char[][] forest) {
        int n = forest.length;
        int[][] dp = new int[n][3];

        // Инициализируем значения в последней строке динамического массива
        for (int j = 0; j < 3; j++) {
            dp[n - 1][j] = forest[n - 1][j] == 'C' ? 1 : 0;
        }

        // Заполняем массив dp снизу вверх
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (forest[i][j] == 'W') {
                    dp[i][j] = 0; // Кусты кусачие, нельзя проходить
                } else {
                    dp[i][j] = forest[i][j] == 'C' ? 1 : 0; // Если клетка содержит гриб, добавляем 1
                    int maxChildMushrooms = 0;
                    for (int k = -1; k <= 1; k++) {
                        if (j + k >= 0 && j + k < 3) {
                            maxChildMushrooms = Math.max(maxChildMushrooms, dp[i + 1][j + k]);
                        }
                    }
                    dp[i][j] += maxChildMushrooms;
                }
            }
        }

        // Находим максимальное количество грибов в первой строке
        int maxMushrooms = 0;
        for (int j = 0; j < 3; j++) {
            maxMushrooms = Math.max(maxMushrooms, dp[0][j]);
        }

        return maxMushrooms;
    }


}
