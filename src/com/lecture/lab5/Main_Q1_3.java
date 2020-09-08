package com.lecture.lab5;

import java.util.Random;

public class Main_Q1_3 {
    //Merge Sort
    public static void main(String[] args) {
        Random r = new Random();
        int len = 6400000;

        int[] arr = new int[len];
        for(int j = 0; j < arr.length; j++)
            arr[j] = r.nextInt(len + 1);

        long start = System.currentTimeMillis();

        mergeSort(arr, 0, arr.length - 1);

        long end = System.currentTimeMillis();

        System.out.println( "���� �ð� : " + ( end - start ) + "ms");

    }

    public static void mergeSort(int[] a, int i, int i1)
    {
        int[] tmp = new int[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }


    private static void mergeSort(int [ ] a, int [ ] tmp, int left, int right)
    {
        if( left < right )
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }


    private static void merge(int[ ] a, int[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left] <= a[right])
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
}
