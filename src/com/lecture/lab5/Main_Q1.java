package com.lecture.lab5;


import java.util.Random;

public class Main_Q1 {
    //Dual Pivot Quick Sort

    //�Ǻ��� �ΰ��� �ְ� quick sort ����
    //sub-array�� ������ insertion sort�� �����ϰ�, ũ�� quick sort�� ����ض�
    //dual pivot quick sort + insertion sort >- java array.sort()
    //http://gwpark.tistory.com/1743

//    public static void quicksort(int[] arr) {
//        quicksort(arr, 0, arr.length - 1);
//    }

    private static void quicksort(int[] arr, int lo, int hi) {
        if (hi <= lo)
            return;

        if (arr[hi] < arr[lo])
            swap(arr, lo, hi);

        int lt = lo + 1;
        int gt = hi - 1;
        int i  = lo + 1;

        while (i <= gt) {
            if (arr[i] < arr[lo])
                swap(arr, lt++, i++);
            else if (arr[i] > arr[hi])
                swap(arr, i, gt--);
            else
                i++;
        }

        swap(arr, lo, --lt);
        swap(arr, hi, ++gt);

        quicksort(arr, lo, lt - 1);
        quicksort(arr, lt + 1, gt - 1);
        quicksort(arr, gt+1, hi);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        Random r = new Random();
        int len = 10000;

        int[] arr = new int[len];
        for(int j = 0; j < arr.length; j++)
            arr[j] = r.nextInt(len + 1);

        long start = System.currentTimeMillis();

        quicksort(arr, 0, arr.length - 1);

        long end = System.currentTimeMillis();

        System.out.println( "���� �ð� : " + ( end - start ) + "ms");

    }

}
