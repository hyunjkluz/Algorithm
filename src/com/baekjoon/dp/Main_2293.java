/**
 * 
 */
package com.baekjoon.dp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 14, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2293
 */
public class Main_2293 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(solution(k, arr));
	}

	public static int solution(int k, int[] coins) {
		int[] dp = new int[k + 1];

		dp[0] = 1; // 동전 자체 1개를 쓰는 경우

		// 동전의 개수 만큼
		for (int i = 0; i < coins.length; i++) {
			// 동전의 목표값 까지
			for (int j = coins[i]; j <= k; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}

		return dp[k];
	}

}
