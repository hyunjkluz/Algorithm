package com.baekjoon.sutdy;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_10845 {
    public static ArrayList<Integer> list = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();

        while (times-- > 0) {
            String order = sc.next();

            if (order.equals("push")) {
                int n = sc.nextInt();
                list.add(n);
            } else if (order.equals("pop")) {
                if (list.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(list.remove(0));
            } else if (order.equals("size")) {
                System.out.println(list.size());
            } else if (order.equals("empty")) {
                if (list.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            } else if (order.equals("front")) {
                if (list.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(list.get(0));
            } else if (order.equals("back")) {
                if (list.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(list.get(list.size() - 1));
            }
        }
    }
}
