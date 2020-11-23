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
 * @CretaedAt : Nov 21, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1915
 */
public class Main_1915 {
	static int N, M;
	static int MAX = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[NM[0]][NM[1]];

		N = NM[0];
		M = NM[1];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		solution2(map);
		System.out.println(MAX * MAX);
	}

	public static void solution2(int[][] map) {
		// 만들어질 수 있는 사각형의 가로 사이즈를 저장
		int[][] dp = new int[N][M];

		// 해당 좌표를 정사각형 오른쪽 아래 맨 끝 모서리라고 가정
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					dp[i][j] = 1;

					// x, y의 첫줄이 아닌 점이면 = 크기 2 이상인 직사각형 만들어질 수 있음
					// 테두리에 있는 점 제외하면 안됨 = 맨 끝의 점에서는 위로 사각형이 만들어질 수 있기 때문
					if (isIn(i, j)) {
						dp[i][j] += Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
					}

					MAX = Math.max(dp[i][j], MAX);
				}
			}
		}

	}

	public static boolean isIn(int x, int y) {
		if (1 <= x && x < N && 1 <= y && y < M) {
			return true;
		}
		return false;
	}

	/*
	 * public static void solution1(int[][] map) { for (int i = 0; i < N; i++) { for
	 * (int j = 0; j < M; j++) { if (map[i][j] == 1) { make(map, i, j); } } } }
	 * 
	 * public static void make(int[][] map, int x, int y) { int size = 1; int newX =
	 * x + 1; int newY = y + 1;
	 * 
	 * // 다음으로 만들어진 크기의 끝 점을 계산하여 추가되는 부분이 다 1인지 검사 while (isIn(newX, newY) &&
	 * map[newX][newY] == 1) { for (int i = x; i <= newX; i++) { if (map[i][newY] ==
	 * 0) { return; } }
	 * 
	 * for (int j = y; j <= newY; j++) { if (map[newX][j] == 0) { return; } }
	 * 
	 * size += 1; newX += 1; newY += 1;
	 * 
	 * }
	 * 
	 * MAX = Math.max(size, MAX);
	 * 
	 * }
	 */

}
