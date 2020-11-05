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
 * @CretaedAt : Nov 5, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2234
 */
public class Main_2234 {
	public static int N, M;
	public static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 남동북서
	public static Map<Integer, Integer[]> memo = new HashMap<Integer, Integer[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[NM[1]][NM[0]];

		N = NM[1];
		M = NM[0];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		System.out.println("\n" + solution(map));
	}

	public static String solution(int[][] map) {
		boolean[][] visited = new boolean[N][M];
		int[][] rooms = new int[N][M];
		Map<Integer, Integer> areas = new HashMap<Integer, Integer>();
		StringBuilder sb = new StringBuilder();
		int maxSize = -1;

		memo.put(0, new Integer[] { 0, 0, 0, 0 });

		int roomNum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					int size = BFS(new Point(i, j), visited, map, rooms, roomNum);
					areas.put(roomNum, size);
					roomNum += 1;
					maxSize = Math.max(maxSize, size);
				}
			}
		}

		Arrays.stream(rooms).forEach(x -> System.out.println(Arrays.toString(x)));
		System.out.println(areas.toString());

		sb.append(areas.size() + "\n");
		sb.append(maxSize + "\n");
		sb.append(searchAll(rooms, areas));

		return sb.toString();

	}

	public static Integer BFS(Point point, boolean[][] visited, int[][] map, int[][] rooms, int roomNum) {
		int area = 0;
		Queue<Point> queue = new LinkedList<Point>();
		visited[point.x][point.y] = true;
		queue.add(point);

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int areaNum = map[p.x][p.y];

			rooms[p.x][p.y] = roomNum;
			area += 1;

			if (!memo.containsKey(areaNum)) {
				getWalls(areaNum);
			}
			Integer[] wall = memo.get(areaNum);

			for (int i = 0; i < dir.length; i++) {
				Point newPoint = new Point(p.x + dir[i][0], p.y + dir[i][1]);

				if (wall[i] == 0 && isIn(newPoint) && !visited[newPoint.x][newPoint.y]) {
					visited[newPoint.x][newPoint.y] = true;
					queue.add(newPoint);
				}
			}

		}

		return area;
	}

	public static int searchAll(int[][] rooms, Map<Integer, Integer> areas) {
		int max = 1;

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				for (int k = 0; k < dir.length; k++) {
					Point next = new Point(i + dir[k][0], j + dir[k][1]);
					if (isIn(next) && areas.get(rooms[i][j]) != areas.get(rooms[next.x][next.y])) {
						max = Math.max(max, areas.get(rooms[i][j]) + areas.get(rooms[next.x][next.y]));
					}

				}
			}
		}

		return max;
	}

	public static void getWalls(int n) {
		Integer[] walls = new Integer[] { n & 8, n & 4, n & 2, n & 1 };

		memo.put(n, walls);
	}

	public static boolean isIn(Point p) {
		if (0 <= p.x && p.x < N && 0 <= p.y && p.y < M) {
			return true;
		}
		return false;
	}
}
