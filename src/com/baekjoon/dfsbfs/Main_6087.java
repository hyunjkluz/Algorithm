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
 * @CretaedAt : Jan 25, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/6087 레이저 통신
 */
class Route {
	Point loc;
	int turnCount;
	int pastDir;

	public Route(int x, int y, int count, int dir) {
		this.loc = new Point(x, y);
		turnCount = count;
		this.pastDir = dir;
	}

}

public class Main_6087 {
	static int W, H;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };
	static char[][] map;
	static Point[] points;
	static int minCount = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] wh = br.readLine().split(" ");

		W = Integer.parseInt(wh[0]);
		H = Integer.parseInt(wh[1]);
		map = new char[H][W];
		points = new Point[2];

		int pointsSize = 0;
		int[][] visited = new int[H][W];

		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < W; j++) {
				visited[i][j] = Integer.MAX_VALUE;

				if (map[i][j] == 'C') {
					points[pointsSize++] = new Point(i, j);
				}
			}
		}

		connectPoint(visited);
		System.out.println(minCount);
	}

	static void connectPoint(int[][] visited) {
		Queue<Route> queue = new LinkedList<Route>();

		queue.add(new Route(points[0].x, points[0].y, 0, -1));
		// 해당 자리를 방문하였을 때 몇번의 거울 사용이 있었는지를 저장하기 위함
		visited[points[0].x][points[0].y] = 0;

		while (!queue.isEmpty()) {
			Route p = queue.poll();

			if (p.loc.x == points[1].x && p.loc.y == points[1].y) {
				minCount = Math.min(minCount, p.turnCount);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int newX = p.loc.x + dx[i];
				int newY = p.loc.y + dy[i];

				if (!isIn(newX, newY) || map[newX][newY] == '*')
					continue;

				int nextTurnCount = p.turnCount;

				// 방향이 이전과 다르면 거울 설치 = 거울 사용 횟수 증가
				if (p.pastDir != i && p.pastDir != -1) {
					nextTurnCount++;
				}

				// 이전에 방문했을때 보다( or 방문 전) 거울을 더 적게 쓰거나 동일하게 섰을 때 다음 칸 더 나아가보기
				if (nextTurnCount <= visited[newX][newY]) {
					queue.add(new Route(newX, newY, nextTurnCount, i));
					visited[newX][newY] = nextTurnCount;
				}

			}

		}

	}

	static boolean isIn(int x, int y) {
		if (0 <= x && x < H && 0 <= y && y < W) {
			return true;
		}
		return false;
	}
}
