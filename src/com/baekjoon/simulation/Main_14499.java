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
 * @CretaedAt : Dec 21, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14499
 */
public class Main_14499 {
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = info[0];
		M = info[1];

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		int[] commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(info[2], info[3], map, commands));
	}

	public static String solution(int x, int y, int[][] map, int[] commands) {
		StringBuilder sb = new StringBuilder();
		// 주사위의 도면을 저장하는 배열
		// 땅과 닿아있는 면 = 동/서, 남/북 돌릴 때 모두 포함되기 때문에 4*4 배열 선언
		// 돌릴 때 맨 마지막 면 저장하고 있는 위치의 값 동기화 시켜줘야함
		int[][] dice = new int[4][4];

		for (int dir : commands) {
			int newX = x + dx[dir - 1];
			int newY = y + dy[dir - 1];

			if (!isIn(newX, newY))
				continue;

			if (map[newX][newY] == 0) {
				map[newX][newY] = dice[1 + dx[dir - 1]][1 + dy[dir - 1]];
			} else {
				dice[1 + dx[dir - 1]][1 + dy[dir - 1]] = map[newX][newY];
				map[newX][newY] = 0;
			}

			switch (dir) {
			case 1:
				right(dice);
				break;
			case 2:
				left(dice);
				break;
			case 3:
				up(dice);
				break;
			case 4:
				down(dice);
				break;
			}

			sb.append(dice[1][1] + "\n");
			x = newX;
			y = newY;
		}

		return sb.toString();
	}

	public static void print(int[][] dice) {
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(dice[i]));
		}
		System.out.println();
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}

	public static void down(int[][] dice) {
		int temp = 0, prevTemp = 0;

		for (int x = 0; x < 4; x++) {
			prevTemp = temp;
			temp = dice[x][1];

			if (x == 0) {
				dice[x][1] = dice[3][1];
			} else {
				dice[x][1] = prevTemp;
			}
		}

		// 남/북 방향으로 돌릴 때 맨 밑바닥 면(위치 : [1][3])이 바뀌었기 대문에
		// 같은 값을 저장하고있는 [3][1]에 새로운 값 동기화시켜줘야함
		dice[1][3] = dice[3][1];
	}

	public static void up(int[][] dice) {
		int temp = 0, prevTemp = 0;

		for (int x = 3; x >= 0; x--) {
			prevTemp = temp;
			temp = dice[x][1];

			if (x == 3) {
				dice[x][1] = dice[0][1];
			} else {
				dice[x][1] = prevTemp;
			}
		}

		dice[1][3] = dice[3][1];
	}

	public static void right(int[][] dice) {
		int temp = 0, prevTemp = 0;

		for (int y = 0; y < 4; y++) {
			prevTemp = temp;
			temp = dice[1][y];

			if (y == 0) {
				dice[1][y] = dice[1][3];
			} else {
				dice[1][y] = prevTemp;
			}
		}

		// 동/서 방향으로 돌릴 때 맨 밑바닥 면(위치 : [3][1])이 바뀌었기 대문에
		// 같은 값을 저장하고있는 [1]31]에 새로운 값 동기화시켜줘야함
		dice[3][1] = dice[1][3];
	}

	public static void left(int[][] dice) {
		int temp = 0, prevTemp = 0;

		for (int y = 3; y >= 0; y--) {
			prevTemp = temp;
			temp = dice[1][y];

			if (y == 3) {
				dice[1][y] = dice[1][0];
			} else {
				dice[1][y] = prevTemp;
			}
		}

		dice[3][1] = dice[1][3];
	}
}
