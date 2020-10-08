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
 * @문제 링크 : https://www.acmicpc.net/problem/9461
 */
public class Main_9641 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			sb.append(solution(Integer.parseInt(br.readLine())) + "\n");
		}
		System.out.println(sb.toString());
	}

	public static long solution(int n) {
		if (n == 0)
			return 0;
		if (n <= 2)
			return 1;

		long[] P = new long[n + 1];
		P[0] = 0;
		P[1] = 1;
		P[2] = 1;

		for (int i = 3; i < P.length; i++) {
			P[i] = P[i - 2] + P[i - 3];
		}

		return P[n];
	}

}
