package com.lecture.lab3;
import java.util.Scanner;

public class Main_Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int size = 1;
        int[] items = {1, -1};

        while(true) {
            int[] bucket = new int[size];
            if (pick(items, bucket, size, num))
                break;
            size++;
        }
    }

    public static boolean pick(int[] items, int[] bucket, int pickNum, int num) {
        boolean ret = false;

        if (pickNum == 0) {
            if (totalSum(bucket) == num) {
                printBucket(bucket);
                return true;    //한번이라도 화면에 출력된게 있으면 true 반환해서 끝
            }
            return false;
        }

        int lastIdx = bucket.length - pickNum - 1;

        for (int i = 0; i < items.length; i++) {
            bucket[lastIdx + 1] = items[i];
            //ret = solution(items, bucket, k - 1) || ret;    // || 둘이 반대로 하면 하나만 출력이 됨 : 앞에마 진실이면 뒤에는 아예 확인하지도 않기 때문에
            ret = ret || pick(items, bucket, pickNum - 1, num);
        }

        return ret;
    }

    public static int totalSum(int[] bucket) {
        int sum = 0;
        for(int i = 0; i < bucket.length; i++) {
            sum += (i + 1) * bucket[i];
        }

        return sum;
    }

    public static void printBucket(int[] bucket) {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 1) {
                System.out.print("+" + (i + 1) * bucket[i]);
            } else {
                System.out.print((i + 1) * bucket[i]);
            }
        }
        System.out.println();
        return;
    }
}
