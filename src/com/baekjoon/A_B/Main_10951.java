package com.baekjoon.A_B;

import java.util.Scanner;

public class Main_10951 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {

			String line = sc.nextLine();
			String[] nums = line.split(" ");

			if (Integer.parseInt(nums[0]) <= 0 || Integer.parseInt(nums[1]) >= 10) {
				System.out.print("error");
			} else {
				System.out.println(Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]));
			}
		}
		sc.close();
	}
}
