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
 * @CretaedAt : Oct 9, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2156
 */
public class Main_2156 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] wines = new int[N];

		for (int i = 0; i < N; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(solution(N, wines));

	}

	public static int solution(int N, int[] wines) {
		int[] drinks = new int[N];

		if (N == 0) {
			return 0;
		}
		if (N >= 1) {
			drinks[0] = wines[0];
		}
		if (N >= 2) {
			drinks[1] = wines[0] + wines[1];
		}
		if (N >= 3) {
			drinks[2] = Math.max(drinks[1], Math.max(drinks[0] + wines[2], wines[1] + wines[2]));
		}
		for (int i = 3; i < N; i++) {
			drinks[i] = Math.max(drinks[i - 1],
					Math.max(drinks[i - 2] + wines[i], drinks[i - 3] + wines[i - 1] + wines[i]));
			// oox, oxo, xoo
		}
		return drinks[N - 1];
	}

}
