package com.baekjoon.sutdy;

import java.util.Scanner;

/**
 * Created hyunjk on 2018-11-27.
 * Github : https://github.com/hyunjkluz
 */
public class Main_2455 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int pastIn = 0;
        int maxP = 0;
        int in = 0, out = 0;

        for (int i = 0; i < 4; i++) {
            out = sc.nextInt();
            in = sc.nextInt();

            System.out.println("past" + pastIn);
            if ((pastIn - out + in) >= maxP)
                maxP = pastIn - out + in;

            pastIn = pastIn - out + in;

        }
        System.out.println(maxP);
    }
}
