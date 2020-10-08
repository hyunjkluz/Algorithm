/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2579
 */
public class Main_2579 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N];

		for (int i = 0; i < N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(solution(N, stairs));
	}

	public static int solution(int N, int[] stairs) {
		if (N == 0) {
			return 0;
		} else if (N == 1) {
			return stairs[0];
		} else if (N == 2) {
			return stairs[0] + stairs[1];
		} else if (N == 3) {
			return Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
		}

		int[] steps = new int[N];

		steps[0] = stairs[0];
		steps[1] = stairs[0] + stairs[1];
		steps[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);

		for (int i = 3; i < N; i++) {
			steps[i] = Math.max(steps[i - 3] + stairs[i - 1] + stairs[i], steps[i - 2] + stairs[i]);
		}

		return steps[N - 1];
	}

}
