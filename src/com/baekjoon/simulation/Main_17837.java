/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 25, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/17837
 */
class Chess implements Comparable<Chess> {
	int x, y, dir, floor;

	public Chess(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.floor = 1;
	}

	@Override
	public int compareTo(Chess o) {
		if (this.floor > o.floor)
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return "Chess [x=" + x + ", y=" + y + ", dir=" + dir + ", floor=" + floor + "]";
	}

}

public class Main_17837 {
	static int N, K;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[] changeDir = { 1, 0, 3, 2 };
	static int[][] board;
	static ArrayList<Chess> horses;
	static boolean fin = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		N = NK[0];
		K = NK[1];

		board = new int[N][N];
		horses = new ArrayList<Chess>();
		for (int i = 0; i < N; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for (int i = 0; i < K; i++) {
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			horses.add(new Chess(info[0] - 1, info[1] - 1, info[2] - 1));
		}

		System.out.println(solution());
	}

	public static int solution() {
		int turn = 0;

		while (turn <= 1000) {

			if (fin) {
				break;
			}

			for (int i = 0; i < horses.size(); i++) {
				int newX = horses.get(i).x + dx[horses.get(i).dir];
				int newY = horses.get(i).y + dy[horses.get(i).dir];

				// 범위 밖이거나 파란색일 때
				if (isNotIn(newX, newY) || board[newX][newY] == 2) {
					blue(horses.get(i));
				} else if (board[newX][newY] == 0) {
					// 흰색일 때
					redOrWhite(newX, newY, horses.get(i), true);
				} else if (board[newX][newY] == 1) {
					// 빨간색일 때
					redOrWhite(newX, newY, horses.get(i), false);
				}

			}

			turn++;
		}

		return turn == 1001 ? -1 : turn;
	}

	public static void redOrWhite(int newX, int newY, Chess chess, boolean isWhite) {
		int topFloor = 0;
		ArrayList<Chess> list = new ArrayList<>();
		list.add(chess);

		for (Chess h : horses) {
			// 해당 말에 업혀있는 말, 리스트에 쌓기
			if (h.x == chess.x && h.y == chess.y && h.floor > chess.floor) {
				list.add(h);
			}

			// 다음번에 이동할 칸에 몇번째에 쌓이게될지 구함
			if (h.x == newX && h.y == newY) {
				topFloor = Math.max(topFloor, h.floor);
			}
		}

		if (isWhite) {
			// 하얀색 : 층수대로 정렬
			Collections.sort(list);
		} else {
			// 빨간색 : 층수 역순으로 정렬
			Collections.sort(list, Collections.reverseOrder());
		}

		// 객체의 좌표와 층수 정보 이동
		for (Chess hl : list) {
			hl.x = newX;
			hl.y = newY;
			hl.floor = ++topFloor;

			if (hl.floor >= 4) {
				fin = true;
				return;
			}

		}

	}

	public static void blue(Chess chess) {
		// 방향 바꿈
		chess.dir = changeDir[chess.dir];

		int newX = chess.x + dx[chess.dir];
		int newY = chess.y + dy[chess.dir];

		if (isNotIn(newX, newY) || board[newX][newY] == 2)
			return;
		else if (board[newX][newY] == 0)
			redOrWhite(newX, newY, chess, true);
		else if (board[newX][newY] == 1)
			redOrWhite(newX, newY, chess, false);

	}

	public static boolean isNotIn(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return true;
		}
		return false;
	}

}
