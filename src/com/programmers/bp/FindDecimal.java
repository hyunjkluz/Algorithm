/**
 * 
 */
package com.programmers.bp;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 11, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42839
 */
public class FindDecimal {
	private static HashSet<Integer> decimalSet = new HashSet<Integer>();

	public static void main(String[] agrs) {
		String numbers = "17";

		System.out.println("Result : " + solution(numbers));
	}

	public static int solution(String numbers) {
		String[] nums = numbers.split("");
		boolean[] visited = new boolean[nums.length];
		Arrays.fill(visited, false);

		for (int i = 1; i <= nums.length; i++) {
			permutation(i, nums, new String[i], 0, visited);
		}

		return decimalSet.size();
	}

	public static void permutation(int r, String[] numArr, String[] temp, int current, boolean[] visited) {
		if (r == current) {
			String number = "";
			for (String s : temp) {
				number += s;
			}

			int intNum = Integer.parseInt(number);
			if (isDecimal(Integer.parseInt(number))) {
				decimalSet.add(intNum);
			}
		} else {
			for (int i = 0; i < numArr.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					temp[current] = numArr[i];
					permutation(r, numArr, temp, current + 1, visited);
					visited[i] = false;
				}
			}
		}
	}

	public static boolean isDecimal(int number) {
		if (number <= 1) {
			return false;
		}

		if (number == 2) {
			return true;
		}

		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

}
