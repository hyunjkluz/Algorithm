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
 * @CretaedAt : Jan 28, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/3055 탈출
 */
public class Main_3055 {
	static int R, C;
	static char[][] map;
	static Point D;
	static Queue<Point> water, hedgehog;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		map = new char[R][C];
		water = new LinkedList<Point>();
		hedgehog = new LinkedList<Point>();

		boolean[][] waterVisited = new boolean[R][C];
		boolean[][] visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'D') {
					D = new Point(i, j);
				} else if (map[i][j] == 'S') {
					hedgehog.add(new Point(i, j));
					visited[i][j] = true;
				} else if (map[i][j] == '*') {
					water.add(new Point(i, j));
					waterVisited[i][j] = true;
				}
			}
		}

		System.out.println(solution(waterVisited, visited));
	}

	public static String solution(boolean[][] waterVisited, boolean[][] visited) {
		int days = 0;

		visited[hedgehog.peek().x][hedgehog.peek().y] = true;

		// while문의 조건을 isSurrounded()로 하면 시간초과
		// 움직일 고슴도치가 더이상 없을 때 종료하는 조건으로 바꿈
		while (!hedgehog.isEmpty()) {
			days++;

			// 다음 시간에 물이 찰 예정인 칸에 고슼도치 이동이 불가능하기 때문에
			// 물 먼저 채움
			fillWater(waterVisited);

			if (moveToCave(visited))
				return Integer.toString(days);

			print();
		}

		return "KAKTUS";

	}

	public static void fillWater(boolean[][] waterVisited) {
		int waterSize = water.size();

		for (int w = 0; w < waterSize; w++) {
			Point cur = water.poll();

			for (int i = 0; i < 4; i++) {
				int nX = cur.x + dx[i];
				int nY = cur.y + dy[i];

				if (isIn(nX, nY) && !waterVisited[nX][nY]) {
					if (map[nX][nY] != 'X' && map[nX][nY] != 'D') {
						// 돌이랑 동굴만 아니면 다 물로 채울 수 있음
						// = 고슴도치고 마찬가지
						map[nX][nY] = '*';
						waterVisited[nX][nY] = true;
						water.add(new Point(nX, nY));
					}
				}
			}
		}
	}

	public static boolean moveToCave(boolean[][] visited) {
		int size = hedgehog.size();

		for (int h = 0; h < size; h++) {
			Point cur = hedgehog.poll();

			for (int i = 0; i < 4; i++) {
				int nX = cur.x + dx[i];
				int nY = cur.y + dy[i];

				if (isIn(nX, nY)) {
					if (map[nX][nY] == 'D') {
						return true;
					}

					if (map[nX][nY] == '.' && !visited[nX][nY]) {
						map[cur.x][cur.y] = '.';
						map[nX][nY] = 'S';
						visited[nX][nY] = true;
						hedgehog.add(new Point(nX, nY));
					}
				}
			}

		}

		return false;

	}

	public static boolean isSurrounded() {
		for (int i = 0; i < 4; i++) {
			int nx = D.x + dx[i];
			int ny = D.y + dy[i];

			if (isIn(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'S'))
				return false;
		}

		return true;
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
			System.out.println();
		}
		System.out.println();
	}

}
