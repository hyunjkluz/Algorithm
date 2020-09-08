package com.baekjoon.sutdy;

import java.util.Scanner;

/**
 * Created hyunjk on 2018-11-27.
 * Github : https://github.com/hyunjkluz
 */
public class Main_1094 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] argv) {
        int x = sc.nextInt();
        int cnt = 0;
        int length = 64;
        int material = 0;

        while (x > 0) {
            if (length > x) {
                length= 2;
            } else {
                cnt++;
                x -= length;
            }
        }
        System.out.println(cnt);
    }
}
