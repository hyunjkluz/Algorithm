package com.lecture.lab2;
import java.util.Scanner;

public class Main_Q3 {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    String word = sc.next();
	    String palindrome = makePalindrome(word, findSameWordIdx(word));

        System.out.println(palindrome);
	}

    public static int findSameWordIdx (String s) {
        for (int i = 0; i < s.length() - 1; i++)
            if (s.charAt(i) == s.charAt(s.length() - 1))
                return i;
        return s.length() - 1;
    }

    public static String[] stringToArray(String word) {
        String[] wordArr = new String[word.length()];

        for (int i = 0; i < wordArr.length; i++)
            wordArr[i] = String.valueOf(word.charAt(i));

        return wordArr;
	}

	public static String makePalindrome(String s, int idx) {
	    for (int i = idx - 1; i >= 0; i--)
	        s += s.charAt(i);

	    return s;
    }
}
