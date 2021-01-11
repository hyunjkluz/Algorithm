/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 11, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/16935
 */
public class Main_16935 {
	static int N, M, R;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = info[0];
		M = info[1];
		R = info[2];
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int[] ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int op : ops) {
			if (op == 1) {
				arr = op1(arr);
			} else if (op == 2) {
				arr = op2(arr);
			} else if (op == 3) {
				arr = op3(arr);
			} else if (op == 4) {
				arr = op4(arr);
			} else if (op == 5) {
				arr = op5(arr);
			} else {
				arr = op6(arr);
			}
		}

		print(arr);

	}

	public static int[][] op1(int[][] arr) {
		int[][] newArr = new int[N][M];

		// 상하 반전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[N - 1 - i][j];
			}
		}

		return newArr;
	}

	public static int[][] op2(int[][] arr) {
		int[][] newArr = new int[N][M];

		// 좌우 반전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[i][M - 1 - j];
			}
		}

		return newArr;
	}

	public static int[][] op3(int[][] arr) {
		// N과 M 값 바꿔주기
		int temp = M;
		M = N;
		N = temp;
		int[][] newArr = new int[N][M];

		// 오른쪽으로 90도
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[M - 1 - j][i];
			}
		}

		return newArr;

	}

	public static int[][] op4(int[][] arr) {
		int temp = M;
		M = N;
		N = temp;
		int[][] newArr = new int[N][M];

		// 왼쪽으로 90도
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = arr[j][N - 1 - i];
			}
		}

		return newArr;

	}

	public static int[][] op5(int[][] arr) {
		int[][] newArr = new int[N][M];
		int flagN = N / 2;
		int flagM = M / 2;

		// 1 -> 2
		for (int i = 0; i < flagN; i++) {
			for (int j = 0; j < flagM; j++) {
				newArr[i][flagM + j] = arr[i][j];
			}
		}

		// 2 -> 3
		for (int i = 0; i < flagN; i++) {
			for (int j = flagM; j < M; j++) {
				newArr[flagN + i][j] = arr[i][j];
			}
		}

		// 3 -> 4
		for (int i = flagN; i < N; i++) {
			for (int j = flagM; j < M; j++) {
				newArr[i][j - flagM] = arr[i][j];
			}
		}

		// 4 -> 1
		for (int i = flagN; i < N; i++) {
			for (int j = 0; j < flagM; j++) {
				newArr[i - flagN][j] = arr[i][j];
			}
		}

		return newArr;

	}

	public static int[][] op6(int[][] arr) {
		int[][] newArr = new int[N][M];
		int flagN = N / 2;
		int flagM = M / 2;

		// 1 -> 4
		for (int i = 0; i < flagN; i++) {
			for (int j = 0; j < flagM; j++) {
				newArr[flagN + i][j] = arr[i][j];
			}
		}

		// 2 -> 1
		for (int i = 0; i < flagN; i++) {
			for (int j = flagM; j < M; j++) {
				newArr[i][j - flagM] = arr[i][j];
			}
		}

		// 3 -> 2
		for (int i = flagN; i < N; i++) {
			for (int j = flagM; j < M; j++) {
				newArr[i - flagN][j] = arr[i][j];
			}
		}

		// 4 -> 3
		for (int i = flagN; i < N; i++) {
			for (int j = 0; j < flagM; j++) {
				newArr[i][flagM + j] = arr[i][j];
			}
		}

		return newArr;

	}

	public static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}

			System.out.println();
		}
		System.out.println();
	}
}
