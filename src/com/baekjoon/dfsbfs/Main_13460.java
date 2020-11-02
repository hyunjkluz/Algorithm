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
 * @문제 링크 : https://www.acmicpc.net/problem/13460 
 * 구슬 탈출2
 */
public class Main_13460 {
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

		solution(red, blue, arr);
		System.out.println(POSSIBLE ? minTurn : -1);

	}

	public static void solution(Point red, Point blue, String[][] arr) {
		for (int i = 0; i < dir.length; i++) {
			dfs(arr, red, blue, i, 0);
		}

		return;
	}

	public static void dfs(String[][] arr, Point red, Point blue, int dirCnt, int turn) {
		if (turn >= 10) {
			return;
		}

		Point newRed = new Point(red.x, red.y);
		Point newBlue = new Point(blue.x, blue.y);
		boolean flag = false;

		// 빨강공 굴리기
		while (0 <= newRed.x && newRed.x < N && 0 <= newRed.y && newRed.y < M) {
			newRed.setLocation(newRed.x + dir[dirCnt][0], newRed.y + dir[dirCnt][1]);

			if (arr[newRed.x][newRed.y].equals("O")) {
				flag = true;
				break;
			}

			if (arr[newRed.x][newRed.y].equals("#")) {
				newRed.setLocation(newRed.x - dir[dirCnt][0], newRed.y - dir[dirCnt][1]);
				break;
			}

		}

		// 파란공 굴리기
		while (0 <= newBlue.x && newBlue.x < N && 0 <= newBlue.y && newBlue.y < M) {
			newBlue.setLocation(newBlue.x + dir[dirCnt][0], newBlue.y + dir[dirCnt][1]);

			// 파란 구슬은 구멍에 들어가면 안됨
			if (arr[newBlue.x][newBlue.y].equals("O")) {
				return;
			}

			if (arr[newBlue.x][newBlue.y].equals("#")) {
				newBlue.setLocation(newBlue.x - dir[dirCnt][0], newBlue.y - dir[dirCnt][1]);
				break;
			}

		}

		// 10번 안에 파랑공이 들어가기 전에 빨간공이 들어감
		if (flag) {
			minTurn = Math.min(minTurn, turn + 1);
			POSSIBLE = true;
			return;
		}

		// 둘이 같은 위치에 있을 수 없음 : 이동거리 긴 구슬 뒤로 밀기
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

		for (int i = 0; i < dir.length; i++) {
			dfs(arr, newRed, newBlue, i, turn + 1);
		}
	}

}
