package com.urize.webapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Java4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем размер матрицы и направление поворота
        int n = scanner.nextInt();
        char direction = scanner.next().charAt(0);

        // Считываем матрицу
        long[][] matrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextLong();
            }
        }

        // Определяем последовательность операций для поворота матрицы
        List<String> operations = rotateMatrix(matrix, direction);

        // Выводим количество операций и сами операции
        System.out.println(operations.size());
        for (String operation : operations) {
            System.out.println(operation);
        }

        scanner.close();
    }

    // Метод для поворота матрицы на 90 градусов
    private static List<String> rotateMatrix(long[][] matrix, char direction) {
        List<String> operations = new ArrayList<>();

        // Поворот матрицы по часовой стрелке
        if (direction == 'R') {
            transpose(matrix);
            reverseRows(matrix);
        }
        // Поворот матрицы против часовой стрелки
        else if (direction == 'L') {
            reverseRows(matrix);
            transpose(matrix);
        }

        return operations;
    }

    // Метод для транспонирования матрицы
    private static void transpose(long[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // Метод для обращения порядка строк в матрице
    private static void reverseRows(long[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            long[] temp = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] = temp;
        }
    }

}
