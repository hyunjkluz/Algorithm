package com.lecture.lab5;

import java.util.Random;

public class Main_Q1_2 {

    public static int partition(int arr[], int left, int right)
    {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;

            while (arr[j] > pivot)
                j--;

            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }



    public static void quicksort(int arr[], int left, int right) {
        int index = partition(arr, left, right);

        if (left < index - 1)
            quicksort(arr, left, index - 1);
        if (index < right)
            quicksort(arr, index, right);
    }

    public static boolean isSorted(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i] > arr[i + 1])
                return false;
        return true;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for(int i = 0; i < 100; i++) {
            //System.out.println(i);
            Random r = new Random();
            int len = r.nextInt(999) + 1;
            int[] arr = new int[len];
            for(int j = 0; j < len; j++)
                arr[j] = r.nextInt(300);
            quicksort(arr, 0, arr.length - 1);
            assert isSorted(arr);
        }
        long end = System.currentTimeMillis();

        System.out.println( "���� �ð� : " + ( end - start )/1000.0 );
    }
}
