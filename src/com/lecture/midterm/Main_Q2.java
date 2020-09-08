package com.lecture.midterm;

import java.util.Random;

public class Main_Q2 {
    public static void main(String[] args) {
        int n = 4;
        int[][] A = make_matrix(n);
        int[][] B = make_matrix(n);
        int[][] C = new int[n][n];

        for(int i = 0; i < C.length; i++)
            for (int j = 0; j < C.length; j++)
                C[i][j] = 0;

        matrix_multiplication(A, B, C, n);
        System.out.println("------A------");
        print_matrix(A);
        System.out.println("------B------");
        print_matrix(B);
        System.out.println("------C------");
        print_matrix(C);
    }

    public static void matrix_multiplication(int[][] A, int[][] B, int[][] C, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] + B[k][j];
                }
            }
        }
    }

    public static int[][] make_matrix(int n) {
        int[][] A = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0 ; j < n; j++)
                A[i][j] = rand.nextInt(5) + 1;
        return A;
    }

    public static void print_matrix(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println();
        }
        return;
    }
}
