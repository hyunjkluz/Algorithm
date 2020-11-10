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
 * @CretaedAt : Nov 10, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/14442
 */
class Location {
	public Point loc;
	public int breakCnt, pathCnt;

	public Location(int x, int y, int breakCnt) {
		this.loc = new Point(x, y);
		this.breakCnt = breakCnt;
		this.pathCnt = 1;
	}

	public Location(int x, int y, int breakCnt, int pathCnt) {
		this.loc = new Point(x, y);
		this.breakCnt = breakCnt;
		this.pathCnt = pathCnt;
	}

}

public class Main_14442 {
	static int N, M, K;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NMK = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[NMK[0]][NMK[1]];
		N = NMK[0];
		M = NMK[1];
		K = NMK[2];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Arrays.stream(map[i]).forEach(x -> {
				if (x == 1)
					x = -1;
			});

		}

		System.out.println(bfs(map));
	}

	public static int bfs(int[][] map) {
		boolean[][][] visited = new boolean[N][M][K + 1];
		Queue<Location> queue = new LinkedList<Location>();
		Location start = new Location(0, 0, 0);

		visited[0][0][0] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			Location here = queue.poll();

			// 제일 먼저 끝에 도달한 경로 길이 리턴
			if (here.loc.x == N - 1 && here.loc.y == M - 1) {
				return here.pathCnt;
			}

			for (int i = 0; i < 4; i++) {
				int newX = here.loc.x + dx[i];
				int newY = here.loc.y + dy[i];
				int newPathCnt = here.pathCnt + 1;
				int breakCnt = here.breakCnt;

				if (isIn(newX, newY)) {
					// 아직 덜 부숨
					if (breakCnt < K) {
						if (map[newX][newY] == 0 && !visited[newX][newY][breakCnt]) {
							// 다음 경로가 벽 아닐 때 + 방문 전일 때
							queue.add(new Location(newX, newY, breakCnt, newPathCnt));
							visited[newX][newY][here.breakCnt] = true;
						}
						if (map[newX][newY] == 1 && !visited[newX][newY][breakCnt + 1]) {
							// 다음 경로가 벽일 때 + 벽뚫고 방문한 곳이 방문 전일 때
							queue.add(new Location(newX, newY, breakCnt + 1, newPathCnt));
							visited[newX][newY][here.breakCnt + 1] = true;
						}

					}

					// 다 부숨
					if (breakCnt == K && map[newX][newY] == 0 && !visited[newX][newY][breakCnt]) {
						// breakCnt가 K보다 커질 수 있기 때문에 같은 경우일 때 까지만 쳐주고 그 이상은 다 무시함
						// 다 부쉈기 때문에 벽은 못감
						queue.add(new Location(newX, newY, breakCnt, newPathCnt));
						visited[newX][newY][here.breakCnt] = true;
					}
				}

			}

		}

		return -1;

	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}
		return false;
	}

}
