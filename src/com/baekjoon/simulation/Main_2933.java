/**
 * 
 */
package com.baekjoon.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 25, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2933 미네랄
 */
public class Main_2933 {
	static int R, C;
	static char[][] map;
	static int N;
	static int[] floors;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");

		R = Integer.parseInt(rc[0]);
		C = Integer.parseInt(rc[1]);
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		N = Integer.parseInt(br.readLine());
		floors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		simulation();
		System.out.println(print());

	}

	public static void simulation() {
		boolean left = true;

		for (int floor : floors) {
			// 미네랄 부수기
			// 층수는 밑에서부터 내려오지만 배열 인덱스를 위해 위에서부터 내려오도록
			breakMineral(R - floor, left);

			// 땅에 붙어있는 미네랄 덩어리 방문
			boolean[][] visited = new boolean[R][C];

			visitFloorMineral(visited);

			// 땅에 붙어있지 않은 미네랄 덩어리 확인
			Queue<Point> floatList = getFloatingMineral(visited);

			// 땅에 붙어있지 않은 미네랄이 있으면 끌어내리기
			if (floatList.size() > 0) {
				moveMineral(floatList, visited);
			}

			// 방향 바꾸기
			left = !left;
		}

	}

	public static void breakMineral(int floor, boolean left) {
		if (left) {
			// 왼쪽에서 던져서 부수기
			for (int i = 0; i < C; i++) {
				if (map[floor][i] == 'x') {
					map[floor][i] = '.';
					break;
				}
			}
		} else {
			// 오른쪽에서 던져서 부수기
			for (int i = C - 1; i >= 0; i--) {
				if (map[floor][i] == 'x') {
					map[floor][i] = '.';
					break;
				}
			}

		}
		return;
	}

	public static void visitFloorMineral(boolean[][] visited) {
		Queue<Point> queue = new LinkedList<Point>();

		// 바닥에 붙어있는 미네랄 담음
		for (int y = 0; y < C; y++) {
			if (map[R - 1][y] == 'x') {
				queue.add(new Point(R - 1, y));
				visited[R - 1][y] = true;
			}
		}

		while (!queue.isEmpty()) {
			Point m = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = m.x + dx[i];
				int newY = m.y + dy[i];

				// 주위를 방문하면서 붙어있는 미네랄 방문
				if (isIn(newX, newY) && map[newX][newY] == 'x' && !visited[newX][newY]) {
					visited[newX][newY] = true;
					queue.add(new Point(newX, newY));
				}
			}

		}
	}

	public static Queue<Point> getFloatingMineral(boolean[][] visited) {
		Queue<Point> list = new LinkedList<Point>();

		// 밑에서부터 담으면 안됨
		// 밑으로 끌어내려야하기 때문에
		// 바닥과 가까운 미네랄부터 방문 + 담음
		for (int i = R - 1; i >= 0; i--) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] == 'x') {
					list.add(new Point(i, j));
				}
			}
		}

		return list;
	}

	public static void moveMineral(Queue<Point> list, boolean[][] visited) {
		// 층을 내릴 때 테두리에 있는 + 바닥과 가장 빨리
		int moveFloor = Integer.MAX_VALUE;

		for (int i = R - 1; i >= 0; i--) {
			for (int j = 0; j < C; j++) {

				// = 붕 떠있는 미네랄 덩어리일 때
				if (map[i][j] == 'x' && !visited[i][j]) {

					int move = 1;

					// 한 칸씩 움직이면서 몇 칸 내려갈 수 있지 계산
					while (isIn(i + move, j) && map[i + move][j] == '.') {
						move++;
					}

					move--;
					// 제일 먼저 걸릴 때 멈춰야하기 때문에 최소값 저장
					moveFloor = move != 0 ? Math.min(moveFloor, move) : moveFloor;

					// 해당 열에서 중간에 빈칸이 있는 부분에서 움직일 층을 계산해버리면 안되니깐
					// 해당 열은 이미 계산 완료 되었다는 표시 함
					// if 특정 열 (위).x..xxx...(밑) 이면두칸 이동하는게 맞지만
					// 모두 다 방문하면 위 얼음에서도 이동하는게 계산되어 최솟값이 갱신됨
					for (int k = i; k >= 0; k--) {
						visited[k][j] = true;
					}
				}
			}
		}

		// 초기화한 값이 그대로인건 이동 못한다는 의미
		if (moveFloor == Integer.MAX_VALUE)
			return;

		// 계산된 이동 칸 수 만큼 이동
		while (!list.isEmpty()) {
			Point mineral = list.poll();

			map[mineral.x + moveFloor][mineral.y] = 'x';
			map[mineral.x][mineral.y] = '.';
		}
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

	public static String print() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
