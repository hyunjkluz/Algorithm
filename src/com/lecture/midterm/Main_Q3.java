package com.lecture.midterm;

public class Main_Q3 {
    public static void main(String[] agrs) {

    }

    public static void mergeSort(int[][] A, int left, int right) {
        if (left > right) {
            int m1 = left + (right - left) / 3;
            int m2 = left + 2*(right - left) / 3;

            mergeSort(A, left, m1);
            mergeSort(A, m1 + 1, m2);
            mergeSort(A, m2 + 1, right);

            mergeSort(A, left, right);
        }
    }

    public static void merge(int[] A, int left, int mid, int right) {

    }

    public static int min(int a, int b, int c) {
        if (a >= b) {
            if (c > b)
                return b;
            else
                return c;
        } else {
            if (c > a)
                return a;
            else
                return c;
        }
    }
}
