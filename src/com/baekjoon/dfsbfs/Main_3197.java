/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 21, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/3197
 */
public class Main_3197 {
	static int R, C;
	static Point[] swan;
	static char[][] map;
	static int[] dx = new int[] { 0, 0, 1, -1 }, dy = new int[] { 1, -1, 0, 0 };
	// 녹일 빙산이 있는 큐 / 바닷물의 위치를 담은 큐
	static Queue<Point> iceberg, water;
	// 백조의 이동 경로 : 다음번에 탐색할 큐
	static Queue<Point> swanRoute;
	// 백조의 이동 경로를 확인할 배열
	static boolean[][] swanVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);

		map = new char[R][C];
		swanVisited = new boolean[R][C];
		swan = new Point[2];
		int swanSize = 0;
//		iceberg = new LinkedList<Point>();
		swanRoute = new LinkedList<Point>();
		water = new LinkedList<Point>();

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'L') {
					swan[swanSize++] = new Point(i, j);
					map[i][j] = '.';
					// 백조도 물에 떠있으니깐 물로 취급
					water.add(new Point(i, j));
				}
//				if (map[i][j] == 'X') {
//					iceberg.add(new Point(i, j));
//				}
				if (map[i][j] == '.') {
					water.add(new Point(i, j));
				}
			}
		}

		System.out.println(solution());

	}

	public static int solution() {
		int days = 0;

		swanRoute.add(swan[0]);
		// 백조가 방문했는지 여부는 전역변수로 관리해야함
		// 지역변수로 관리하면 이 다음 턴이 되었을 때 방문했음에도 불구하고 방문한지 모르고 다시 방문할 수 있기 때문
		swanVisited[swan[0].x][swan[0].y] = true;

		while (true) {
			days++;

//			melting1();
			melting2();
			print();

			if (meeting()) {
				break;
			}
		}

		return days;

	}

	// 빙하에 초점
	// 시간초과
	public static void melting1() {
		int size = iceberg.size();
		Queue<Point> changeSea = new LinkedList<Point>();

		for (int i = 0; i < size; i++) {
			Point ice = iceberg.poll();

			int seaCount = 0;

			for (int j = 0; j < 4; j++) {
				int newX = ice.x + dx[j];
				int newY = ice.y + dy[j];

				if (isIn(newX, newY)) {
					if (map[newX][newY] == '.') {
						seaCount += 1;
					}
				}
			}

			if (seaCount == 0) {
				iceberg.add(ice);
			} else {
				changeSea.add(ice);
			}
		}

		while (!changeSea.isEmpty()) {
			Point p = changeSea.poll();
			map[p.x][p.y] = '.';
		}
	}

	// 바다에 초점
	public static void melting2() {
		int size = water.size();

		for (int i = 0; i < size; i++) {
			Point sea = water.poll();

			for (int j = 0; j < 4; j++) {
				int newX = sea.x + dx[j];
				int newY = sea.y + dy[j];

				if (isIn(newX, newY) && map[newX][newY] == 'X') {
					map[newX][newY] = '.';
					water.add(new Point(newX, newY));
				}
			}
		}
	}

	public static boolean meeting() {
		Queue<Point> nextSearch = new LinkedList<Point>();

		while (!swanRoute.isEmpty()) {
			Point current = swanRoute.poll();

			// 백조 찾음
			if (current.x == swan[1].x && current.y == swan[1].y) {
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int newX = current.x + dx[i];
				int newY = current.y + dy[i];

				if (isIn(newX, newY) && !swanVisited[newX][newY]) {
					swanVisited[newX][newY] = true;

					// 빙산에 부딛히면
					// 이 좌표 전까지는 방문 가능했다는 의미
					// 다음 턴에는 해당 좌표부터 탐색 시작하면 됨
					if (map[newX][newY] == 'X') {
						nextSearch.add(new Point(newX, newY));
					} else {
						// 바다이면 다음 탐색을 위해 지금 반복문으로 돌리고 있는 큐에 넣음
						// 현재 탐색할 수 있음을 의미
						swanRoute.add(new Point(newX, newY));
					}
				}
			}

		}

		// 다음번 방문 탐색을 위해 값 교체
		swanRoute = nextSearch;

		return false;
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

	public static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

}
