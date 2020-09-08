package com.baekjoon.sutdy;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_9012 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();

        while (times-- > 0) {
            String s = sc.next();
            if (checkYps(s))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public static boolean checkYps(String s) {
        String[] array_word = s.split("");
        ArrayList<String> vps = new ArrayList<String>();

        if(array_word[0].equals(")"))
            return false;

        for (int i = 0; i < array_word.length; i++) {
            if (array_word[i].equals("("))
                vps.add(array_word[i]);
            else {
                if (vps.isEmpty())
                    return false;
                vps.remove(vps.size() - 1);
            }

        }

        if (vps.isEmpty())
            return true;
        else
            return false;
    }
}
