/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 31, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14500
 */
public class Main_14500 {

	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	public static int N, M;
	public static int MAX = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = NM[0];
		M = NM[1];
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(arr));
	}

	public static int solution(int[][] arr) {
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				DFS(arr, visited, 0, 0, i, j);
				visited[i][j] = false;
				findAnotherShape(arr, i, j);
			}
		}

		return MAX;
	}

	public static void DFS(int[][] arr, boolean[][] visited, int depth, int sum, int x, int y) {
		if (depth == 4) {
			MAX = Math.max(MAX, sum);
			return;
		}

		for (int i = 0; i < dir.length; i++) {
			int newX = x + dir[i][0];
			int newY = y + dir[i][1];

			if (0 <= newX && newX < N && 0 <= newY && newY < M && !visited[newX][newY]) {
				visited[newX][newY] = true;
				DFS(arr, visited, depth + 1, sum + arr[newX][newY], newX, newY);
				findAnotherShape(arr, newX, newY);
				visited[newX][newY] = false;
			}
		}
	}

	public static void findAnotherShape(int[][] arr, int x, int y) {
		int flag = 4;
		int sum = arr[x][y];
		int minSum = Integer.MAX_VALUE;

		// 기준점 중심으로 최소 3개의 블록은 붙어있어야 한다
		for (int i = 0; i < dir.length && flag > 2; i++) {
			// 상 하 좌 우 를 돌면서 + 모양으로 더함
			int newX = x + dir[i][0];
			int newY = y + dir[i][1];

			if (0 <= newX && newX < N && 0 <= newY && newY < M) {
				// + 모양을 완성했을 때 제일 작은 값 하나를 빼야 최대의 ㅗ 모양을 만들 수 있음
				minSum = Math.min(minSum, arr[newX][newY]);
				sum += arr[newX][newY];
			} else {
				flag--;
				continue;
			}
		}

		if (flag > 2) {
			if (flag == 4) {
				sum -= minSum;
			}

			MAX = Math.max(MAX, sum);
		}
	}

}
