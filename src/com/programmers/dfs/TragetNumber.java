/**
 * 
 */
package com.programmers.dfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 14, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/43165
 */
public class TragetNumber {
	public static Integer cnt = 0;

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;

		System.out.println("Result : " + solution(numbers, target));
	}

	public static int solution(int[] numbers, int target) {
		dfs(numbers, target, 0);
		return cnt;
	}

	public static void dfs(int[] numbers, int target, int node) {
		if (node == numbers.length) {
			Integer sum = 0;
			for (Integer num : numbers)
				sum += num;
			if (sum == target)
				cnt++;
		} else {
			numbers[node] *= 1;
			dfs(numbers, target, node + 1);

			numbers[node] *= -1;
			dfs(numbers, target, node + 1);
		}
	}

	public static int dfs2(int[] numbers, int n, int sum, int target) {
		if (n == numbers.length) {
			if (sum == target) {
				return 1;
			}
			return 0;
		}
		return dfs2(numbers, n + 1, sum + numbers[n], target) + dfs2(numbers, n + 1, sum - numbers[n], target);
	}

}
