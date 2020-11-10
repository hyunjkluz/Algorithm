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
 * @CretaedAt : Nov 5, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/12869
 */
public class Main_12869 {
	public static int[][] CASES = new int[][] { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 9, 3 },
			{ 1, 3, 9 } };
	// S, V, C를 파괴하기 위한 최소 공격의 수 저장
	public static int[][][] dp = new int[61][61][61];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] SCV = new int[3];

		for (int i = 0; i < N; i++) {
			SCV[i] = arr[i];
		}

		System.out.println(solution(N, SCV));

	}

	public static int solution(int N, int[] SCV) {
		// 배열의 길이가 1이면 계산할 필요 없음
		if (N == 1) {
			return (SCV[0] / 9) + 1;
		}

		// dp 배열 초기화
		for (int i = 0; i < 61; i++) {
			for (int j = 0; j < 61; j++) {
				Arrays.fill(dp[i][j], -1);
			}

		}

		dp[0][0][0] = 0;

		return DP(SCV[0], SCV[1], SCV[2]);

	}

	public static int DP(int s, int c, int v) {

		// 배열에 대입할거라 음수 있으면 안됨
		s = s < 0 ? 0 : s;
		c = c < 0 ? 0 : c;
		v = v < 0 ? 0 : v;


		// 이미 계산이 된 체력 히스토리이면 메모된 값 리턴
		if (dp[s][c][v] != -1) {
			return dp[s][c][v];
		}

		// 경우의 수 중에서 한번에 처리할 수 있는 체력들이면 1 리턴
		if (checkOneTime(s, c, v)) {
			return dp[s][c][v] = 1;
		}

		// 해당 s, v, c 체력으로 만들기 위해 나올 수 있는 모든 경우의 수 + 1 중에서 제일 작은 값 대입
		dp[s][c][v] = Integer.MAX_VALUE;
		for (int i = 0; i < CASES.length; i++) {
			dp[s][c][v] = Math.min(dp[s][c][v], DP(s - CASES[i][0], c - CASES[i][1], v - CASES[i][2]));
		}

		return dp[s][c][v] += 1;
	}

	public static boolean checkOneTime(int s, int c, int v) {
		for (int i = 0; i < CASES.length; i++) {
			if (s <= CASES[i][0] && c <= CASES[i][1] && v <= CASES[i][2]) {
				return true;
			}
		}

		return false;
	}

}
