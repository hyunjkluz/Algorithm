/**
 * 
 */
package com.baekjoon.dp;

import java.util.Scanner;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 27, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2748
 */
public class Main_2748 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		System.out.println(solution(n));
	}

	public static long solution(int n) {
		long[] memo = new long[n + 1];

		memo[0] = 0;
		memo[1] = 1;

//		return fibo2(memo, n);
		return fibo2_2(memo, n);
	}

	public static long fibo2(long[] memo, int n) {
		if (n < 2) {
			return memo[n];
		}

		if (memo[n] == 0) {
			memo[n] = fibo2(memo, n - 2) + fibo2(memo, n - 1);
		}

		return memo[n];
	}

	public static long fibo2_2(long[] memo, int n) {
		for (int i = 2; i < memo.length; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}

		return memo[n];
	}

}
