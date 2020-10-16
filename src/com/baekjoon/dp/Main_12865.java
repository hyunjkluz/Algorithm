/**
 * 
 */
package com.baekjoon.dp;

import java.util.Scanner;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 16, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/12865
 */
public class Main_12865 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}

		System.out.println(solution(N, K, W, V));
	}

	public static int solution(int n, int maxWeight, int[] w, int[] v) {
		int[][] knapsack = new int[w.length][maxWeight + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= maxWeight; j++) {
				knapsack[i][j] = knapsack[i - 1][j];

				if (j - w[i] >= 0) {
					knapsack[i][j] = Math.max(knapsack[i - 1][j], knapsack[i - 1][j - w[i]] + v[i]);
				}
			}
		}
		return knapsack[n][maxWeight];
	}

}
