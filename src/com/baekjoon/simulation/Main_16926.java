/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 30, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/16926
 */
public class Main_16926 {
	public static int N, M;
	public static int[][] point = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] arr = new int[NM[0]][NM[1]];
		N = NM[0];
		M = NM[1];

		for (int i = 0; i < NM[0]; i++) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		StringBuilder sb = new StringBuilder();

		for (int[] a : solution(NM[2], arr)) {
			Arrays.stream(a).forEach(x -> sb.append(x + " "));
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public static int[][] solution(int R, int[][] arr) {
		int layers = Math.min(N, M) / 2;

		// 껍질 1, 껍질 2 , ...
		for (int l = 0; l < layers; l++) {
			int x0 = l;
			int x1 = N - 1 - l;
			int y0 = l;
			int y1 = M - 1 - l;

			// 회전
			int r = R % ((N - 2 * l) * (M - 2 * l));

			while (r-- > 0) {
				int tmp = arr[x0][x0];
				for (int i = y0; i < y1; i++)
					arr[x0][i] = arr[x0][i + 1];
				for (int i = x0; i < x1; i++)
					arr[i][y1] = arr[i + 1][y1];
				for (int i = y1; i > y0; i--)
					arr[x1][i] = arr[x1][i - 1];
				for (int i = x1; i > x0; i--)
					arr[i][y0] = arr[i - 1][y0];
				arr[x0 + 1][y0] = tmp;
			}

		}

		return arr;
	}

	public static int[][] solution2(int R, int[][] arr) {
		int layers = Math.min(N, M) / 2;

		while (R-- > 0) {
			for (int i = 0; i < layers; i++) {
				int dir = 0;
				int sx = i;
				int sy = i;
				int value = arr[sx][sy];
				while (dir < 4) {
					int nx = sx + point[dir][0];
					int ny = sy + point[dir][1];

					if (nx >= i && ny >= i && nx < N - i && ny < -i) {
						arr[sx][sy] = arr[nx][ny];

						sx = nx;
						sy = ny;
					} else {
						dir++;
					}
				}
				arr[i + 1][i] = value;
			}
		}

		return arr;
	}

}
