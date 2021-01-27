/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 27, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/4991 로봇청소기
 */
public class Main_4991 {
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };
	static int W, H, result;
	static ArrayList<Point> list;
	static char[][] map;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();

		while (true) {
			String[] info = br.readLine().split(" ");

			W = Integer.parseInt(info[0]);
			H = Integer.parseInt(info[1]);

			if (W == 0 && H == 0)
				break;

			map = new char[H][W];
			// 먼지와 청소기의 위치를 저장해놓은 리스트
			list = new ArrayList<Point>();

			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();

				for (int j = 0; j < W; j++) {
					if (map[i][j] == 'o') {
						list.add(0, new Point(i, j));
					} else if (map[i][j] == '*') {
						list.add(new Point(i, j));
					}
				}
			}

			if (!isPossibleToClean()) {
				answer.append("-1\n");
				continue;
			}

			int size = list.size();
			// 먼지 중에서 방문할 순서대로 열거 : 순열(DFS)
			boolean[] picked = new boolean[size];

			// 출발 i ~ 도착 j 시 이동 거리
			dist = new int[size][size];

			for (int i = 0; i < list.size(); i++) {
				int[][] temp = calcDist(list.get(i).x, list.get(i).y);

				// 각 먼지 사이의 거리 구하기
				for (int j = i + 1; j < list.size(); j++) {
					dist[i][j] = temp[list.get(j).x][list.get(j).y];
					dist[j][i] = temp[list.get(j).x][list.get(j).y];
				}
			}

			result = Integer.MAX_VALUE;

			dfs(0, 0, 0, picked);

			answer.append(result + "\n");
		}

		System.out.println(answer.toString());
	}

	public static boolean isPossibleToClean() {
		ArrayList<Point> copyList = (ArrayList<Point>) list.clone();
		boolean[][] visited = new boolean[H][W];
		Queue<Point> queue = new LinkedList<Point>();
		Point start = copyList.remove(0);

		visited[start.x][start.y] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nX = cur.x + dx[i];
				int nY = cur.y + dy[i];

				if (!isIn(nX, nY) || map[nX][nY] == 'x' || visited[nX][nY])
					continue;

				if (copyList.contains(new Point(nX, nY))) {
					copyList.remove(new Point(nX, nY));
				}

				visited[nX][nY] = true;
				queue.add(new Point(nX, nY));
			}

		}

		return copyList.size() == 0 ? true : false;

	}

	public static int[][] calcDist(int x, int y) {
		int[][] temp = new int[H][W];
		boolean[][] visited = new boolean[H][W];
		Queue<Point> queue = new LinkedList<Point>();

		visited[x][y] = true;
		queue.add(new Point(x, y));

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nX = cur.x + dx[i];
				int nY = cur.y + dy[i];

				if (!isIn(nX, nY) || map[nX][nY] == 'x' || visited[nX][nY])
					continue;

				visited[nX][nY] = true;
				queue.add(new Point(nX, nY));
				temp[nX][nY] = temp[cur.x][cur.y] + 1;
				
				Map<String, String> study = new HashMap<String, String>();
				
				study.put("MONDAY", "9 to 12");
			}
		}

		return temp;
	}

	public static void dfs(int depth, int distSum, int from, boolean[] picked) {
		// 모든 지점을 방문했으면 최소값을 갱신하고 종료한다.
		if (depth == picked.length - 1) {
			result = Math.min(result, distSum);
			return;
		}

		for (int to = 1; to < picked.length; ++to) {
			if (!picked[to]) {
				picked[to] = true;
				// 거리인접행렬에서 값을 꺼내 더하고 다음 지점으로 향한다.
				dfs(depth + 1, distSum + dist[from][to], to, picked);
				picked[to] = false;
			}
		}
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < H && 0 <= y && y < W) {
			return true;
		}
		return false;
	}

}
