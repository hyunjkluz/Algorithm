/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1463
 */
public class Main_1463 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(solution(N));
	}

	public static long solution(int N) {
		if (N < 2) {
			return 0;
		}
		if (N < 4) {
			return 1;
		}

		long[] dp = new long[N + 1];

		dp[0] = dp[1] = 0;
		dp[2] = dp[3] = 1;

		for (int i = 4; i < dp.length; i++) {
			long[] result = new long[3];
			Arrays.fill(result, (long) (Math.pow(10, 6) + 2));

			if (i % 3 == 0) {
				result[0] = 1 + dp[i / 3];
			}
			if (i % 2 == 0) {
				result[1] = 1 + dp[i / 2];
			}
			result[2] = 1 + dp[i - 1];

			dp[i] = Math.min(Math.min(result[0], result[1]), result[2]);

		}

		return dp[N];
	}

}
