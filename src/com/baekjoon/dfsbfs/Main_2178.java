/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 12, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2178
 */
public class Main_2178 {

	public static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		int[][] matrix = new int[N][M];

		for (int i = 0; i < N; i++) {
			matrix[i] = Arrays.stream(sc.next().split("")).mapToInt(Integer::parseInt).toArray();
		}

		solutionBfs(matrix);
		System.out.println(matrix[N - 1][M - 1]);

	}

	public static void solutionBfs(int[][] matrix) {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<Point>();
		int[][] loc = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		q.add(new Point(0, 0));

		while (!q.isEmpty()) {
			Point pick = q.poll();
			visited[pick.x][pick.y] = true;

			for (int i = 0; i < loc.length; i++) {
				int x = pick.x + loc[i][0];
				int y = pick.y + loc[i][1];

				if (0 <= x && x < N && 0 <= y && y < M) {
					if (matrix[x][y] != 0 && !visited[x][y]) {
						q.offer(new Point(x, y));
						visited[x][y] = true;
						matrix[x][y] = matrix[pick.x][pick.y] + 1;
					}
				}
			}
		}
	}

}
