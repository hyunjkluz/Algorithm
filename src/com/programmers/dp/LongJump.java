/**
 * 
 */
package com.programmers.dp;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 16, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/12914
 */
public class LongJump {

	public static void main(String[] args) {
		System.out.println(solution(4));

	}

	public static long solution(int n) {
		long[] dp = new long[n + 1];

		if (n == 1) {
			return 1 % 1234567;
		}
		if (n == 2) {
			return 2 % 1234567;
		}

		dp[1] = 1 % 1234567;
		dp[2] = 2 % 1234567;

		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
		}

		for (int i = 0; i < n; i++) {
			System.out.print(dp[i]);
		}
		return dp[n];
	}

}
