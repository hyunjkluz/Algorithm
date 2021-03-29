/**
 * 
 */
package com.programmers.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 9, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42746
 */
public class MaxNumber {
	public static void main(String[] args) {
		int[] numbers = { 3, 30, 34, 5, 9 };

		System.out.println("Result : " + solution(numbers));
	}

	public static String solution(int[] numbers) {
		String answer = "";
		String[] stringNums = new String[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			stringNums[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(stringNums, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				// 내림차순
				// return (s1 + s2).compareTo(s2 + s1);

				// 오름차순
				return (s2 + s1).compareTo(s1 + s2);
			}
		});

		if (stringNums[0].startsWith("0")) {
			return "0";
		}

		for (int i = 0; i < stringNums.length; i++) {
			answer += stringNums[i];
		}

		return answer;
	}
}
