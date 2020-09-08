package com.baekjoon.sutdy;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2504 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

    }

    public static void check(String s) {
        String[] array_words = s.split("");
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> calc = new ArrayList<String>();
        String sss= "";

        for (int i = 0; i < array_words.length; i++) {
            if (array_words[i].equals("(") || array_words.equals("[")) {
                list.add(array_words[i]);
            } else if (array_words[i].equals(")")) {
                if (calc.size() == 0) {
                    calc.add("2");
                }
                if (isInteger(calc.get(calc.size() - 1))) {
                    
                }

            } else if (array_words[i].equals("]")) {

            }
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
