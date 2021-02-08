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
 * @CretaedAt : Feb 1, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2636 치즈1
 */
public class Main_2636 {
	static int N, M;
	static int[][] map;
	static int[][] airMap;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };
	static int hours, lastCheeses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");

		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N][M];
		airMap = new int[N][M];
		hours = 0;
		lastCheeses = 0;

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					// 치즈 표시
					airMap[i][j] = -1;
				}
			}
		}

		solution();

		System.out.println(hours + "\n" + lastCheeses);

	}

	static void solution() {
		seperateAir();

		Queue<Point> nearAir = findAirNearCheese();

		while (true) {
			if (isAllMelt()) {
				break;
			}

			meltingCheese(nearAir);
			lastCheeses = nearAir.size();

			mergeAir(nearAir);

			hours++;
		}

	}

	static void seperateAir() {
		// 치즈를 둘러싸고 있는 바깥 공기와, 치르가 둘러싸고 있는 안쪽 공기를 나눔
		Queue<Point> queue = new LinkedList<Point>();

		queue.add(new Point(0, 0));
		airMap[0][0] = 1;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (isIn(nx, ny) && map[nx][ny] == 0 && airMap[nx][ny] == 0) {
					// 외부 공기만 1로 바꿔줌
					airMap[nx][ny] = 1;
					queue.add(new Point(nx, ny));
				}
			}
		}
	}

	static Queue<Point> findAirNearCheese() {
		Queue<Point> nearAir = new LinkedList<Point>();

		// 치즈와 외부에서 맞닿은 공기를 담음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 외부 공기일 때
				if (airMap[i][j] == 1) {

					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];

						// 주위에 치즈가 하나라도 있으면
						if (isIn(nx, ny) && map[nx][ny] == 1) {
							nearAir.add(new Point(i, j));
							break;
						}
					}
				}
			}
		}

		return nearAir;
	}

	static void meltingCheese(Queue<Point> nearAir) {
		// 치즈를 녹인다
		// = 인접한 공기 중에서 주위에 치즈가 있으면 그 치즈를 녹임
		// 녹인 치즈(=공기 됨)의 좌표는 다음에 녹여질 치즈와 인접한 공기의 좌표이기 때문에
		// 인접 공기 큐에 넣어준다

		int size = nearAir.size();

		for (int i = 0; i < size; i++) {
			Point a = nearAir.poll();

			for (int j = 0; j < 4; j++) {
				int nx = a.x + dx[j];
				int ny = a.y + dy[j];

				// 사방으로 둘러보았을 때 치즈면 녹여서 공기로 만들기
				if (isIn(nx, ny) && map[nx][ny] == 1) {
					map[nx][ny] = 0;
					nearAir.add(new Point(nx, ny));
				}
			}
		}
	}

	static void mergeAir(Queue<Point> nearAir) {
		// 내부와 외부 공기를 합침
		// 근처 공기 좌표를 돌면서 공기를 발견
		// = 치즈 내부 공기 근처에 있는 치즈가 다 녹아서 외부 공기로 변함
		// 내부 공기를 만나게 되면 그 내부공기 또한 외부공기와 같은 역할을 할 수 있으므로 큐에 넣음

		Queue<Point> copyQueue = new LinkedList<Point>(nearAir);

		while (!copyQueue.isEmpty()) {
			Point a = copyQueue.poll();

			for (int j = 0; j < 4; j++) {
				int nx = a.x + dx[j];
				int ny = a.y + dy[j];

				// 사방으로 둘러보았을 때 내부 공기를 마주쳤을 때
				if (isIn(nx, ny) && airMap[nx][ny] == 0) {
					// 발견한 내부공기를 외부 공기로 쳐줌
					airMap[nx][ny] = 1;
					// 발견한 공기도 탐색 대상으로 선정
					copyQueue.add(new Point(nx, ny));
					nearAir.add(new Point(nx, ny));
				}
			}
		}
	}

	static boolean isAllMelt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}

}
