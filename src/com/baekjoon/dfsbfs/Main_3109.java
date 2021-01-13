/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 13, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/3109
 */
public class Main_3109 {
	static int R, C;
	static char[][] map;
	static int[] dx = { -1, 0, 1 };
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		solution();

		System.out.println(count);

	}

	public static void solution() {
		for (int i = 0; i < R; i++) {
			DFS(i, 0);
		}
	}

	public static boolean DFS(int x, int y) {
		// 마지막 지점에 도달했을 때
		if (y == C - 1) {
			count++;
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int newX = x + dx[i];
			int newY = y + 1;

			if (isIn(newX, newY) && map[newX][newY] != 'x') {
				// 방문한 곳이란걸 표시
				map[newX][newY] = 'x';

				// 다음 좌표에서 배관이 만들어졌으면 끝
				if (DFS(newX, newY)) {
					return true;
				}
			}

		}

		return false;
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

}
