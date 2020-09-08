package com.lecture.lab1;


import java.util.Scanner;

public class Q4 {
    static String path = "";
    static int flagU, flagD;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        flagU = sc.nextInt();
        flagD = sc.nextInt();

        if(flagU < flagD){
            fantasia(0, 1, 1, 1);
            path = "L" + path;
        }else{
            fantasia(1, 1, 1, 0);
            path = "R" + path;
        }

        System.out.println(path);


    }

    public static boolean fantasia(int leftU, int leftD, int rightU, int rightD) {
        int midU = leftU + rightU;
        int midD = leftD + rightD;

        if ((midD == flagD) && (midU == flagU)) {
            return true;
        }

        if (fantasia(leftU, leftD, midU, midD)) {
            path += "L" + path;
            return true;
        }

        if (fantasia(midU, midD, rightU, rightD)) {
            path += "R" + path;
            return true;
        }

        return false;

    }
}
