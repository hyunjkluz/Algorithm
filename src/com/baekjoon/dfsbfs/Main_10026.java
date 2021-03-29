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
 * @CretaedAt : Jan 8, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/10026
 */
public class Main_10026 {
	static int N;
	static char[][] picture;
	static int normal = 0, weak = 0;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		picture = new char[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			picture[i] = br.readLine().toCharArray();
		}

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (!visited[x][y]) {
					normal += 1;
					weak += 1;

					boolean isColorWeak = BFS(picture[x][y], x, y, visited);

					if (isColorWeak) {
						weak -= 1;
					}
				}
			}
		}

		System.out.println(normal + " " + weak);

	}

	public static boolean BFS(char color, int x, int y, boolean[][] visited) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean isColorWeak = false;

		queue.add(new Point(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = current.x + dx[i];
				int newY = current.y + dy[i];

				// 새로운 좌표가 범위 안이면
				if (isIn(newX, newY)) {
					// 주위의 방문한 죄표 중에 색약에 해당되는 색깔이 있으면
					if (visited[newX][newY]) {
						if ((color == 'R' && picture[newX][newY] == 'G')
								|| (color == 'G' && picture[newX][newY] == 'R')) {
							isColorWeak = true;
						}
					}

					// 방문하지 않았고, 색깔이 같은 때
					if (!visited[newX][newY] && picture[newX][newY] == color) {
						queue.add(new Point(newX, newY));
						visited[newX][newY] = true;
					}
				}
			}
		}

		return isColorWeak;
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;
	}

}
