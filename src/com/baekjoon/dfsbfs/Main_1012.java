/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 12, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1012
 */
public class Main_1012 {
	public static int M, N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();

		int T = sc.nextInt();
		while (T-- > 0) {
			M = sc.nextInt();
			N = sc.nextInt();
			int K = sc.nextInt();

			int[][] cabbages = new int[N][M];

			while (K-- > 0) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				cabbages[y][x] = 1;
			}

			sb.append(solution(cabbages) + "\n");
		}
		System.out.println(sb.toString());
	}

	public static int solution(int[][] cabbages) {
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cabbages[i][j] == 1) {
					answer++;
					seed(cabbages, i, j);
				}
			}
		}

		return answer;
	}

	public static void seed(int[][] cabbages, int x, int y) {
		if (cabbages[x][y] == 0) {
			return;
		}

		cabbages[x][y] = 0;

		if (x != 0) {
			seed(cabbages, x - 1, y);
		}
		if (x != N - 1) {
			seed(cabbages, x + 1, y);
		}

		if (y != 0) {
			seed(cabbages, x, y - 1);
		}
		if (y != M - 1) {
			seed(cabbages, x, y + 1);
		}

	}

}
