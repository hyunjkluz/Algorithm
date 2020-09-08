package com.lecture.lab1;

import java.util.Scanner;

public class Q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int cnt = 1;

        while (true) {
            int sum = a + reverse(a);

            if (cnt >= 1000) {
                System.out.println("NaN");
            } else {
                if (isPalindrome(sum)) {
                    System.out.println(cnt + " " + sum);
                } else {
                    a = sum;
                    continue;
                }
            }
            break;
        }
    }

    public static int reverse(int n) {
        int reverseN = 0;

        while (n != 0) {
            reverseN = reverseN * 10  + n % 10;
            n /= 10;
        }

        return reverseN;
    }

    public static int changeToArray(int n, int[] arr) {
        int size = 0;

        while (n != 0) {
            arr[size++] = n % 10;
            n /= 10;
        }
        return size;
    }

    public static boolean isPalindrome (int n) {
        int[] nArray = new int[10000];
        int size = changeToArray(n, nArray);

        for (int i = 0; i < size; i++) {
            if (nArray[i] != nArray[size - 1 - i])
                return false;
        }

        return true;
    }

}
