/**
 * 
 */
package com.baekjoon.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 1, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/17144
 */
public class Main_17144 {
	static int R, T, C;
	static int[][] map;
	static List<Point> machine;
	static int[][] upDir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int[][] downDir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] RTC = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		R = RTC[0];
		C = RTC[1];
		T = RTC[2];

		map = new int[R][C];
		machine = new ArrayList<Point>();

		for (int i = 0; i < R; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) {
					machine.add(new Point(i, j));
				}
			}
		}

		while (T-- > 0) {
			spreadDust();
			cleanUpAir();
			cleanDownAir();
		}

		System.out.println(countDust());
	}

	public static void spreadDust() {
		// 미세먼지 들어있는 칸 다 수집
		// 미세먼지 사방으로 확산
		Queue<Point> queue = new LinkedList<Point>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != -1 && map[i][j] != 0) {
					queue.add(new Point(i, j));
				}
			}
		}

		int[][] newMap = new int[R][C];

		while (!queue.isEmpty()) {
			Point dust = queue.poll();
			int dustValue = map[dust.x][dust.y];
			int newDustValue = (int) Math.floor(dustValue / 5);
			int spreadArea = 0;

			for (int i = 0; i < 4; i++) {
				Point newDust = new Point(dust.x + upDir[i][0], dust.y + upDir[i][1]);

				if (isIn(newDust) && !machine.contains(newDust)) {
					spreadArea += 1;
					newMap[newDust.x][newDust.y] += newDustValue;
				}
			}

			dustValue -= newDustValue * spreadArea;
			newMap[dust.x][dust.y] += dustValue;
		}

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (map[x][y] != -1) {
					map[x][y] = newMap[x][y];
				}
			}
		}
	}

	public static void cleanUpAir() {
		Point up = machine.get(0);
		int flag = -1;
		int prevFlag = 0;

		// 남
		flag = map[up.x][C - 1];
		for (int y = C - 1; y > 0; y--) {
			if (y == 1) {
				map[up.x][y] = prevFlag;
			} else {
				map[up.x][y] = map[up.x][y - 1];
			}
		}

		// 동
		prevFlag = flag;
		flag = map[0][C - 1];
		for (int x = 0; x < up.x; x++) {
			if (x + 1 == up.x) {
				map[x][C - 1] = prevFlag;
			} else {
				map[x][C - 1] = map[x + 1][C - 1];
			}
		}

		// 북
		prevFlag = flag;
		flag = map[0][0];
		for (int y = 0; y < C - 1; y++) {
			if (y + 1 == C - 1) {
				map[0][y] = prevFlag;
			} else {
				map[0][y] = map[0][y + 1];
			}
		}

		// 서
		for (int x = up.x - 1; x >= 1; x--) {
			if (x == 1) {
				map[x][0] = flag;
			} else {
				map[x][0] = map[x - 1][0];
			}
		}
	}

	public static void cleanDownAir() {
		Point down = machine.get(1);
		int flag = -1;
		int prevFlag = 0;

		// 북
		flag = map[down.x][C - 1];
		for (int y = C - 1; y > 0; y--) {
			if (y == 1) {
				map[down.x][y] = prevFlag;
			} else {
				map[down.x][y] = map[down.x][y - 1];
			}
		}

		// 동
		prevFlag = flag;
		flag = map[R - 1][C - 1];
		for (int x = R - 1; x > down.x; x--) {
			if (x - 1 == down.x) {
				map[x][C - 1] = prevFlag;
			} else {
				map[x][C - 1] = map[x - 1][C - 1];
			}
		}

		// 남
		prevFlag = flag;
		flag = map[R - 1][0];
		for (int y = 0; y < C - 1; y++) {
			if (y + 1 == C - 1) {
				map[R - 1][y] = prevFlag;
			} else {
				map[R - 1][y] = map[R - 1][y + 1];
			}
		}

		// 서
		prevFlag = flag;
		for (int x = down.x + 1; x < R - 1; x++) {
			if (x + 1 == R - 1) {
				map[x][0] = prevFlag;
			} else {
				map[x][0] = map[x + 1][0];
			}
		}

	}

	public static int countDust() {
		int total = 0;

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (map[x][y] != -1 && map[x][y] != 0) {
					total += map[x][y];
				}
			}
		}

		return total;
	}

	public static void print() {
		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				System.out.print(map[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isIn(Point pos) {
		if (0 <= pos.x && pos.x < R && 0 <= pos.y && pos.y < C) {
			return true;
		}
		return false;
	}

}
