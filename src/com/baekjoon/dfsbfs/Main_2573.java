/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 18, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2573
 */
public class Main_2573 {
	static int N, M;
	static int[][] map;
	static int[] dx = new int[] { 0, 0, 1, -1 }, dy = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution());

	}

	public static int solution() {
		int year = 0;

		while (true) {
			year++;
			// 빙하 녹이기
			melting();

			// 덩어리를 크기를 담을 리스트
			ArrayList<Integer> area = new ArrayList<Integer>();
			boolean[][] visited = new boolean[N][M];
			boolean isAllMelt = true;

			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < M - 1; j++) {
					// 좌표가 바다가 아닌 빙산이 존재할 때
					if (map[i][j] != 0 && !visited[i][j]) {
						area.add(checkSeparate(i, j, visited));
						// 덩어리가 하나라도 존재하면 다 녹지 않았다는 의미
						isAllMelt = false;
					}
				}
			}

			if (area.size() >= 2) {
				return year;
			}

			if (isAllMelt) {
				return 0;
			}
		}

	}

	public static void melting() {
		// 녹일 빙산 정보
		Queue<Point> iceberg = new LinkedList<Point>();
		// 빙산 주위에 있는 바닷물의 개수
		Queue<Integer> border = new LinkedList<Integer>();

		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				// 좌표가 바다가 아닌 빙산이 존재할 때
				if (map[i][j] != 0) {
					int countSea = 0;

					// 동 서 남 북 바다가 있는지 확인
					for (int k = 0; k < 4; k++) {
						int newX = i + dx[k];
						int newY = j + dy[k];

						// 새로운 좌표가 범위 안이고 바다면
						if (isIn(newX, newY) && map[newX][newY] == 0) {
							countSea += 1;
						}
					}

					// 주위에 바다가 하나라도 존재한다면 녹일 빙산 리스트에 추가
					// 차례대로 큐에 담음
					if (countSea > 0) {
						iceberg.add(new Point(i, j));
						border.add(countSea);
					}
				}
			}
		}

		// 빙산 녹이기
		while (!iceberg.isEmpty()) {
			Point p = iceberg.poll();
			int sea = border.poll();

			map[p.x][p.y] -= sea;
			if (map[p.x][p.y] < 0) {
				// 뺀 값이 음수일 경우 0으로 바꿔줌
				map[p.x][p.y] = 0;
			}
		}

	}

	public static int checkSeparate(int x, int y, boolean[][] visited) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		int size = 1;

		while (!queue.isEmpty()) {
			Point point = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = point.x + dx[i];
				int newY = point.y + dy[i];

				// 같은 덩어리에 존재하는 좌표 방문 표시
				if (isIn(newX, newY) && map[newX][newY] != 0 && !visited[newX][newY]) {
					queue.add(new Point(newX, newY));
					visited[newX][newY] = true;
					size++;
				}
			}
		}

		return size;
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}
}
