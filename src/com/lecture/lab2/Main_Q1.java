package com.lecture.lab2;
import java.util.Scanner;

public class Main_Q1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int minIdx = findMinIdx(arr);
        arr[minIdx]++;

        System.out.println(encodeArr(arr, minIdx));
    }

    public static int findMinIdx(int[] arr) {
        int min = arr[0];
        int idx = 0;

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
                idx = i;
            }
        }

        return idx;
    }

    public static int encodeArr(int[] arr, int minIdx) {
        int total = 1;

        for (int i = 0; i < arr.length; i++)
            total *= arr[i];

        return total;
    }

}
