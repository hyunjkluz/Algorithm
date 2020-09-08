package com.baekjoon.A_B;

import java.util.Scanner;
public class Main_10950 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		if (t > 0) {
			for (int i = 0; i < t; i++) {
				int a =  sc.nextInt();
				int b = sc.nextInt();
				if (a <= 0 || b >= 10) {
					System.out.print("error");
				} else {
					System.out.println(a + b);
				}
			}
		}
		sc.close();
	}
	
	public static void sub() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		sc.nextLine();
		if (t > 0) {
			for (int i = 0; i < t; i++) {
				String line = sc.nextLine();
				String[] nums = line.split(" ");
				
				if (Integer.parseInt(nums[0]) <= 0 || Integer.parseInt(nums[1]) >= 10) {
					System.out.print("error");
				} else {
					System.out.println(Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]));
				}
			}
		}
		sc.close();
	}
}
