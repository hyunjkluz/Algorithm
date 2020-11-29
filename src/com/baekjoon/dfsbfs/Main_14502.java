/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 27, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14502
 */
public class Main_14502 {
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };
	static int safeZone = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = NM[0];
		M = NM[1];

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		makeWall(0, 0, 0, map);

		System.out.println(safeZone);

	}

	public static void makeWall(int x, int y, int wallCnt, int[][] map) {
		if (wallCnt == 3) {
			// 2. 바이러스 영역을 센다.
			int safe = solution(map);

			// 3. 최대 안전영역 값을 갱신한다.
			safeZone = Math.max(safeZone, safe);

			return;
		}

		// 1. 무작위 벽을 세운다.
		for (int i = x; i < N; i++) {
			int j = y;

			if (i != x) {
				j = 0;
			}

			for (; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					makeWall(x, y, wallCnt + 1, map);
					map[i][j] = 0;
				}
			}

		}
	}

	public static int solution(int[][] map) {
		boolean[][] visited = new boolean[N][M];
		int existWall = 0;

		int virusZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					existWall += 1;
				}
				if (map[i][j] == 2 && !visited[i][j]) {
					virusZone += bfs(i, j, visited, map);
				}
			}
		}

		// 안전영역 = 전체 넓이 - (기존의 벽 개수 + 새로운 벽 개수) - 바이러스 넓이
		return N * M - existWall - virusZone;

	}

	public static int bfs(int x, int y, boolean[][] visited, int[][] map) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		int zone = 1;

		while (!queue.isEmpty()) {
			Point virus = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = virus.x + dx[i];
				int newY = virus.y + dy[i];

				if (isIn(newX, newY) && map[newX][newY] == 0 && !visited[newX][newY]) {
					queue.add(new Point(newX, newY));
					visited[newX][newY] = true;
					zone += 1;
				}
			}
		}

		return zone;

	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}
}
