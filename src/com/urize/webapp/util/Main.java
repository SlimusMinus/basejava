package com.urize.webapp.util;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество оценок
        int n = scanner.nextInt();

        // Считываем оценки в массив
        int[] grades = new int[n];
        for (int i = 0; i < n; i++) {
            grades[i] = scanner.nextInt();
        }

        int start = 0;

        int[] result = new int[7];
        for (int i = 0; i < grades.length; i++) {
            if (start > 6) {
                break;
            }
            if (grades[i] != 2 && grades[i] != 3) {
                result[start] = grades[i];
                start++;
            } else {
                start = 0;
                result = new int[7];
            }
        }
        int countFIve = -1;
        if (result[6] != 0) {
            countFIve = (int) Arrays.stream(result).filter(num -> num == 5).count();
        }
        System.out.println(countFIve);
        scanner.close();
    }
}
