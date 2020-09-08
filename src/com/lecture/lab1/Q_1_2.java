package com.lecture.lab1;


import java.util.Scanner;

public class Q_1_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        int carry = 0;
        int a = sc.nextInt();
        int b = sc.nextInt();

        while ((a != 0) && (b != 0)) {
            int sum = a % 2 + b % 2 + carry;
            //System.out.println("a : " + (a%2) +  " , b : " + (b%2));

            if (sum >= 2) {
                carry = 1;
                cnt++;
            }

            a /= 2; b /= 2;
        }
        System.out.println(cnt);
    }
}
