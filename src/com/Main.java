package com.urize;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество оценок
        int n = scanner.nextInt();

        // Считываем оценки за каждый день
        int[] grades = new int[n];
        for (int i = 0; i < n; i++) {
            grades[i] = scanner.nextInt();
        }

        int maxFives = findMaxFives(grades);
        System.out.println(maxFives);

        scanner.close();
    }

    // Метод для поиска максимального количества пятерок в отрезке без двоек и троек
    private static int findMaxFives(int[] grades) {
        int maxFives = -1;
        int countFives = 0;
        int left = 0;

        // Проходим по массиву оценок
        for (int right = 0; right < grades.length; right++) {
            // Если текущая оценка равна 5, увеличиваем счетчик пятерок
            if (grades[right] == 5) {
                countFives++;
            }

            // Если текущая оценка равна 2 или 3, сдвигаем левую границу отрезка
            while (grades[right] == 2 || grades[right] == 3) {
                // Уменьшаем счетчик пятерок, если левая граница отрезка содержит 5
                if (grades[left] == 5) {
                    countFives--;
                }
                left++;
            }

            // Обновляем максимальное количество пятерок
            maxFives = Math.max(maxFives, countFives);
        }

        // Если не найдено ни одной пятерки, возвращаем -1
        if (maxFives == 0) {
            return -1;
        }

        return maxFives;
    }
}
