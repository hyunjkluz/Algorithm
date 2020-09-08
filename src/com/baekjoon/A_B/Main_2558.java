package com.baekjoon.A_B;

import java.util.Scanner;
public class Main_2558 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int a =  sc.nextInt();
		int b = sc.nextInt();
		if (a <= 0 || b >= 10) {
			System.out.print("error");
		} else {
			System.out.println(a + b);
		}
	}
}
