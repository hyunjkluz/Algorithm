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
 * @CretaedAt : Nov 16, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14238
 */
public class Main_14238 {
	// A 개수 / B 개수 / C 개수 / 이전 문자 / 이전 이전 문자 : 해당 조건에 출근 기록이 가능한 것인지
	static int[][][][][] DP = new int[51][51][51][3][3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] abc = new int[3]; // ABC 각각의 개수

		for (int i = 0; i < S.length(); i++) {
			abc[S.charAt(i) - 'A']++;
		}

		clear();

		System.out.println(dp(abc[0], abc[1], abc[2], 0, 0) == 1 ? getLog(abc[0], abc[1], abc[2], 0, 0) : -1);
	}

	public static void clear() {
		// DP 초기화
		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= 50; j++) {
				for (int k = 0; k <= 50; k++) {
					for (int l = 0; l < 3; l++) {
						Arrays.fill(DP[i][j][k][l], -1);
					}
				}
			}
		}
	}

	public static int dp(int a, int b, int c, int prev1, int prev2) {
		if (a + b + c == 0) {
			return DP[a][b][c][prev1][prev2] = 1;
		}

		if (DP[a][b][c][prev1][prev2] != -1) {
			return DP[a][b][c][prev1][prev2];
		}

		// 해당 문자를 쓸 수 있고 + 이전의 값이 만들어지는게 가능하면
		if (a > 0 && dp(a - 1, b, c, 0, prev1) == 1) {
			return DP[a][b][c][prev1][prev2] = 1;
		}

		// + B는 다음날 쉬어야해서 이전 문자가 B면 안됨
		if (b > 0 && prev1 != 1 && dp(a, b - 1, c, 1, prev1) == 1) {
			return DP[a][b][c][prev1][prev2] = 1;
		}

		// + C는 다음날과 다다음날 쉬어야해서 이전과 이이전의 문자가 C면 안됨
		if (c > 0 && prev1 != 2 && prev2 != 2 && dp(a, b, c - 1, 2, prev1) == 1) {
			return DP[a][b][c][prev1][prev2] = 1;
		}

		// 다 안될 때
		return DP[a][b][c][prev1][prev2] = 0;
	}

	public static String getLog(int a, int b, int c, int prev1, int prev2) {
		// abc를 모두 소진
		if (a + b + c == 0) {
			return "";
		}

		// 아직 사용 가능한 a가 있고 + a를 사용한 결과가 가능한 경우라면
		if (a > 0 && dp(a - 1, b, c, 0, prev1) == 1) {
			// A를 출력하자
			return "A" + getLog(a - 1, b, c, 0, prev1);
		}
		if (b > 0 && prev1 != 1 && dp(a, b - 1, c, 1, prev1) == 1) {
			return "B" + getLog(a, b - 1, c, 1, prev1);
		}
		if (c > 0 && prev1 != 2 && prev2 != 2 && dp(a, b, c - 1, 2, prev1) == 1) {
			return "C" + getLog(a, b, c - 1, 2, prev1);
		}

		return "";
	}
}
