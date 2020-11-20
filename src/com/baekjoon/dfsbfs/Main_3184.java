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
 * @CretaedAt : Nov 20, 2020
 * @문제 링크 :
 */
public class Main_3184 {

	static int R, C;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int sheeps, wolfs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] RC = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		R = RC[0];
		C = RC[1];

		String[][] map = new String[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().split("");

			for (String m : map[i]) {
				if (m.equals("v")) {
					wolfs += 1;
				}
				if (m.equals("o")) {
					sheeps += 1;
				}
			}
		}

		solution(map);
		System.out.println(sheeps + " " + wolfs);

	}

	public static void solution(String[][] map) {
		boolean[][] visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!map[i][j].equals("#") && !visited[i][j]) {
					bfs(map, visited, new Point(i, j));
				}
			}
		}

	}

	public static void bfs(String[][] map, boolean[][] visited, Point start) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(start);
		visited[start.x][start.y] = true;
		int sheepIn = 0;
		int wolfIn = 0;

		while (!queue.isEmpty()) {
			Point ping = queue.poll();

			if (map[ping.x][ping.y].equals("v"))
				wolfIn += 1;
			if (map[ping.x][ping.y].equals("o"))
				sheepIn += 1;

			for (int i = 0; i < dir.length; i++) {
				int newX = ping.x + dir[i][0];
				int newY = ping.y + dir[i][1];

				if (isIn(newX, newY) && !map[newX][newY].equals("#") && !visited[newX][newY]) {
					visited[newX][newY] = true;
					queue.add(new Point(newX, newY));
				}
			}
		}

		if (wolfIn < sheepIn) {
			wolfs -= wolfIn;
		} else {
			sheeps -= sheepIn;
		}
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

}
