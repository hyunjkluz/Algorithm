/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 2, 2020
 * @문제 링크 : https://www.acmicpc.net/submit/15653 구슬탈출4
 */

public class Main_15653 {
	public static int N, M;
	public static int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	public static boolean POSSIBLE = false;
	public static int minTurn = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		String[][] arr = new String[NM[0]][NM[1]];
		Point red = new Point();
		Point blue = new Point();

		N = NM[0];
		M = NM[1];

		for (int i = 0; i < N; i++) {
			arr[i] = bf.readLine().split("");

			for (int j = 0; j < M; j++) {
				if (arr[i][j].equals("R")) {
					red.setLocation(i, j);
					arr[i][j] = ".";
				}
				if (arr[i][j].equals("B")) {
					blue.setLocation(i, j);
					arr[i][j] = ".";
				}
			}
		}

		bfs(arr, red, blue);
		System.out.println(POSSIBLE ? minTurn : -1);

	}

	public static void bfs(String[][] arr, Point red, Point blue) {
		boolean[][][][] visited = new boolean[10][10][10][10];
		Queue<Points> queue = new LinkedList<Points>();
		Points points = new Points(red, blue, 0);
		queue.add(points);

		while (!queue.isEmpty()) {
			Points getPoint = queue.poll();

			red = getPoint.red;
			blue = getPoint.blue;

			visited[red.x][red.y][blue.x][blue.y] = true;

			for (int i = 0; i < dir.length; i++) {
				boolean[] isFin = new boolean[2];

				// 빨강공 굴리기
				Point newRed = rollRed(red, i, arr, isFin);
				// 파란공 굴리기
				Point newBlue = rollBlue(blue, i, arr, isFin);

				if (isFin[1]) {
					continue;
				}

				if (isFin[0]) {
					minTurn = Math.min(minTurn, getPoint.getTurnPlus1());
					POSSIBLE = true;
					return;
				}

				// 둘이 같은 위치에 있을 수 없음 : 이동거리 긴 구슬 뒤로 밀기
				isSamePosition(newRed, newBlue, red, blue, i);

				if (!visited[newRed.x][newRed.y][newBlue.x][newBlue.y]) {
					queue.add(new Points(newRed, newBlue, getPoint.getTurnPlus1()));
				}
			}

		}

	}

	public static Point rollRed(Point red, int dirCnt, String[][] arr, boolean[] isFin) {
		Point newRed = new Point(red.x, red.y);
		while (0 <= newRed.x && newRed.x < N && 0 <= newRed.y && newRed.y < M) {
			newRed.setLocation(newRed.x + dir[dirCnt][0], newRed.y + dir[dirCnt][1]);

			if (arr[newRed.x][newRed.y].equals("O")) {
				isFin[0] = true;
				break;
			}

			if (arr[newRed.x][newRed.y].equals("#")) {
				newRed.setLocation(newRed.x - dir[dirCnt][0], newRed.y - dir[dirCnt][1]);
				break;
			}

		}

		return newRed;
	}

	public static Point rollBlue(Point blue, int dirCnt, String[][] arr, boolean[] isFin) {
		Point newBlue = new Point(blue.x, blue.y);

		while (0 <= newBlue.x && newBlue.x < N && 0 <= newBlue.y && newBlue.y < M) {
			newBlue.setLocation(newBlue.x + dir[dirCnt][0], newBlue.y + dir[dirCnt][1]);

			// 파란 구슬은 구멍에 들어가면 안됨
			if (arr[newBlue.x][newBlue.y].equals("O")) {
				isFin[1] = true;
				break;
			}

			if (arr[newBlue.x][newBlue.y].equals("#")) {
				newBlue.setLocation(newBlue.x - dir[dirCnt][0], newBlue.y - dir[dirCnt][1]);
				break;
			}

		}

		return newBlue;
	}

	public static void isSamePosition(Point newRed, Point newBlue, Point red, Point blue, int dirCnt) {

		if (newRed.x == newBlue.x && newRed.y == newBlue.y) {
			switch (dirCnt) {
			case 0: // 오른쪽으로(동)
				if (red.y < blue.y) {
					newRed.setLocation(newRed.x, newRed.y - 1);
				} else {
					newBlue.setLocation(newBlue.x, newBlue.y - 1);
				}
				break;
			case 1: // 아래로(남)
				if (red.x < blue.x) {
					newRed.setLocation(newRed.x - 1, newRed.y);
				} else {
					newBlue.setLocation(newBlue.x - 1, newBlue.y);
				}
				break;
			case 2: // 왼쪽으로(서)
				if (red.y < blue.y) {
					newBlue.setLocation(newBlue.x, newBlue.y + 1);
				} else {
					newRed.setLocation(newRed.x, newRed.y + 1);
				}
				break;
			case 3: // 위로(북)
				if (red.x < blue.x) {
					newBlue.setLocation(newBlue.x + 1, newBlue.y);
				} else {
					newRed.setLocation(newRed.x + 1, newRed.y);
				}
				break;
			}
		}
	}

}
