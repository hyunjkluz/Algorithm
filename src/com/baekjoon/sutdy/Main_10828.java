package com.baekjoon.sutdy;

import java.util.Scanner;

public class Main_10828 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //ArrayList<Integer> statk = new ArrayList<Integer>();

        int len = sc.nextInt();
        int[] stack = new int[len];
        int sLen = 0;

        for (int i = 0; i < len; i++) {
            String order = sc.next();

            if (order.equals("push")) {
                int num = sc.nextInt();
                sLen++;
                for (int j = sLen - 1; j >= 0; j--) {
                    stack[j + 1] = stack[j];
                }
                stack[0] = num;
            } else if (order.equals("pop")) {
                if (sLen != 0) {
                    System.out.println(stack[0]);

                    for (int j = 0; j < sLen; j++)
                        stack[j] = stack[j + 1];
                    sLen--;
                } else {
                    System.out.println("-1");
                }
            } else if (order.equals("size")) {
                System.out.println(sLen);
            } else if (order.equals("empty")) {
                if (sLen != 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            } else if (order.equals("top")) {
                if (sLen != 0) {
                    System.out.println(stack[0]);
                } else {
                    System.out.println("-1");
                }
            } else {

            }
        }


    }
}
