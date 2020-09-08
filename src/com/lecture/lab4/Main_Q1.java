package com.lecture.lab4;

import java.util.Scanner;

public class Main_Q1 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //int n = sc.nextInt();
        int[] items = {0, 1, 2, 3, 4, 5, 6, 7};
        int item = sc.nextInt();
        int[] bucket = new int[4];
        //comb(items, bucket, 4);
        comb2(item, bucket, bucket.length);
    }

    public static void perm(int[] items, int[] bucket, int pickNum) {  //permutation : ����
        int lastIdx = bucket.length - pickNum - 1;
        if (pickNum == 0) {
            printBucket(bucket);
        }

        for (int i = 0; i < items.length; i++) {
            int flag = 0;
            for (int j = 0; j <= lastIdx; j++) {
                if (bucket[j] == items[i])
                    flag = 1;
            }

            if (flag == 1) continue;

            bucket[lastIdx + 1] = items[i];
            perm(items, bucket, pickNum -1);
        }
    }

    public static void perm2(int item, int[] bucket, int pickNum) {  //permutation : ����
        int lastIdx = bucket.length - pickNum - 1;
        if (pickNum == 0) {
            printBucket(bucket);
        }

        for (int i = 0; i < item; i++) {
            int flag = 0;
            for (int j = 0; j <= lastIdx; j++) {
                if (bucket[j] == i)
                    flag = 1;
            }

            if (flag == 1) continue;

            bucket[lastIdx + 1] = i;
            perm2(item, bucket, pickNum -1);
        }
    }

    public static void comb(int[] items, int[] bucket, int pickNum) {  //combination : ����
        int lastIdx = bucket.length - pickNum - 1;

        if (pickNum == 0) {
            printBucket(bucket);
            return;
        }

        for (int i = 0; i < items.length; i++) {
            if (bucket.length == pickNum) {
                bucket[0] = items[i];
                comb(items, bucket, pickNum - 1);
            } //else if (bucket[lastIdx] <= items[i]) {     //�ߺ�����
            else if (bucket[lastIdx] < items[i]) {
                bucket[lastIdx + 1] = items[i];
                comb(items, bucket, pickNum - 1);
            }
        }
    }

    public static void comb2(int items, int[] bucket, int pickNum) {
        int lastIdx = bucket.length - pickNum - 1;
        int smallest = 0;

        if (pickNum == 0) {
            printBucket(bucket);
            return;
        }

        if (pickNum == bucket.length)
            smallest = 0;
        else
            smallest = bucket[lastIdx] + 1;     //�ߺ����� : smallest = bucket[lastIdx]

        for (int i = smallest; i < items; i++) {
            bucket[lastIdx + 1] = i;
            comb2(items, bucket, pickNum - 1);
        }
    }

    public static void printBucket(int[] bucket) {
        for (int i = 0; i < bucket.length; i++)
            System.out.print(bucket[i] + "\t");
        System.out.println();
        return;
    }
}
