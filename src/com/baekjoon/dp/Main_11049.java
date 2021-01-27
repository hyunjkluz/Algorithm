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
 * @CretaedAt : Jan 26, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/11049 행렬 곱셈 순서
 * @참고 링크 : https://yeeybook.tistory.com/127
 */
public class Main_11049 {
	static int N;
	static int[][] sizes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		sizes = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			sizes[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int answer = 0;

		if (N == 1) {
			answer = 0;
		} else if (N == 2) {
			answer = sizes[1][0] * sizes[1][1] * sizes[2][1];
		} else {
			answer = multiplication2();
		}

		System.out.println(answer);
	}

	// 틀렸습니다.
	public static int multiplication1() {
		// N번째 까지 계산하였을 때의 최솟값
		int[] dp = new int[N + 1];

		dp[1] = 0;
		dp[2] = sizes[1][0] * sizes[1][1] * sizes[2][1];

		for (int i = 3; i < dp.length; i++) {
			int case1 = dp[i - 1] + sizes[1][0] * sizes[i][0] * sizes[i][1];
			int case2 = dp[i - 2] + sizes[i - 1][0] * sizes[i][0] * sizes[i][1]
					+ sizes[1][0] * sizes[i - 1][0] * sizes[i][1];

			dp[i] = Math.min(case1, case2);
		}

		return dp[N];
	}

	public static int multiplication2() {
		// i번째 ~ j번째 행렬까지의 곱의 최소 연산 횟수
		int[][] dp = new int[N + 1][N + 1];

		// 옆에 붙어있는 행렬(인접행렬)들에 대한 최소 연산 횟수 저장(초기화)
		for (int i = 1; i < N; i++) {
			dp[i][i + 1] = sizes[i][0] * sizes[i][1] * sizes[i + 1][1];
		}

		for (int g = 2; g <= N; g++) { // 묶어서 계산할 그룹의 크기
			for (int i = 1; i <= N - g; i++) { // 시작
				int j = i + g;
				for (int k = i; k < j; k++) {
					// 그룹1 dp + 그룹2 dp + 그룹1과 그룹2의 곱셈 연산 값
					int res = dp[i][k] + dp[k + 1][j] + sizes[i][0] * sizes[k][1] * sizes[j][1];

					if (dp[i][j] == 0) {
						dp[i][j] = res;
					} else {
						dp[i][j] = Math.min(dp[i][j], res);
					}
				}
			}
		}

		return dp[1][N];
	}

}
