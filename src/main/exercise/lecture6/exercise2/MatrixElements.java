package com.homework.exercise.lecture6.exercise2;

public class MatrixElements {
    public static void main (String[] Args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }
        }

        System.out.println();

        for (int[] row : matrix) {
            for (int col = 0; col < row.length; col++) {
                System.out.printf("%d ", row[col]);
            }
        }
    }
}
