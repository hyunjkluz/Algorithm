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
 * @CretaedAt : Nov 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14503
 */
class Clean {
	public int x, y, dir;

	public Clean(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public Point back() {
		if (this.dir == 0) {
			return new Point(x + 1, y);
		}
		if (this.dir == 1) {
			return new Point(x, y - 1);
		}
		if (this.dir == 2) {
			return new Point(x - 1, y);
		}
		return new Point(x, y + 1);
	}

	public String toString() {
		return "dir : " + this.dir + "( " + this.x + ", " + this.y + " )";
	}
}

public class Main_14503 {
	public static int N, M;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	public static int area = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] arr = new int[NM[0]][NM[1]];
		N = NM[0];
		M = NM[1];

		int[] info = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Clean robot = new Clean(info[0], info[1], info[2]);

		for (int i = 0; i < NM[0]; i++) {
			arr[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(robot, arr));

	}

	public static int solution(Clean robot, int[][] arr) {
		boolean[][] visited = new boolean[N][M];
		Queue<Clean> queue = new LinkedList<Clean>();

		queue.add(robot);
		visited[robot.x][robot.y] = true;

		go(queue, visited, arr);

		return countClean(visited);
	}

	public static void go(Queue<Clean> queue, boolean[][] visited, int[][] arr) {

		while (!queue.isEmpty()) {
			Clean here = queue.poll();
			System.out.println(here.toString());
			boolean flag = false;
			// 다음에 이동할 방방 결정하기 위해 현재 방향 받음
			int nextDir = here.dir;

			// 다음 이동할 위치 : 왼쪽으로 돌면서 청소할 방향을 찾는다
			for (int i = 0; i < 4; i++) {
				// 다음에 이동할 방향과 좌표 결정
				nextDir = (nextDir + 3) % 4;
				int nextX = here.x + dx[nextDir];
				int nextY = here.y + dy[nextDir];

				if (!isIn(nextX, nextY)) {
					continue;
				}

				// 청소할 방향(벽이 아니고 청소한 이력도 없음) 찾으면 그 쪽으로 전진
				if (arr[nextX][nextY] == 0 && !visited[nextX][nextY]) {
					queue.add(new Clean(nextX, nextY, nextDir));
					visited[nextX][nextY] = true;
					flag = true;
					break;
				}
			}

			if (!flag) {
				Point back = here.back();

				if (isIn(back.x, back.y) && arr[back.x][back.y] != 1) {
					visited[back.x][back.y] = true;
					queue.add(new Clean(back.x, back.y, here.dir));
				}
			}
		}

	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}

	public static int countClean(boolean[][] visited) {
		int cnt = 0;
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if (visited[i][j])
					cnt++;
			}
		}

		return cnt;
	}
}
