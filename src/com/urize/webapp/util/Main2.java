package com.urize.webapp.util;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем размеры матрицы
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Создаем матрицу
        int[][] matrix = new int[n][m];

        // Считываем матрицу
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Выводим повернутую матрицу
        int[][] rotatedMatrix = rotateMatrix(matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(rotatedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }

        // Метод для поворота матрицы на 90 градусов по часовой стрелке
    private static int[][] rotateMatrix(int[][] matrix) {
        int [][] retArr = new int[matrix[0].length][matrix.length];
        int retArrI = 0;
        int retArrJ = matrix.length - 1;
        for (int [] srI : matrix){
            for (int srJ : srI)
                retArr[retArrI++][retArrJ] = srJ;
            retArrI = 0;
            retArrJ--;
        }
        return retArr;
    }
}
