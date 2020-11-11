/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 11, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/15685
 */
public class Main_15685 {
	static boolean[][] maked = new boolean[101][101];
	static int dx[] = { 0, -1, 0, 1 }, dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());

		while (N-- > 0) {
			int[] line = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int x = line[1];
			int y = line[0];
			int dir = line[2];
			int gen = line[3];

			Stack<Integer> dirStack = new Stack<Integer>();
			// 0. 0세대는 먼저 만들어놓음 : 방향전환이 필요 없기 때문에
			int[] xy = new int[] { x + dx[dir], y + dy[dir] };

			maked[x][y] = true;
			maked[xy[0]][xy[1]] = true;
			dirStack.add(dir);

			// 1. 0세대부터 입력받은 세대까지 드래곤 커브 만듬 : 다음세대가 이전세대 이용
			for (int i = 0; i < gen; i++) {
				makeDragonCurve(dirStack, xy);
			}
		}

		// 2. 전체 드래곤 커브 확인
		System.out.println(checkDragonCurve());

	}

	public static void makeDragonCurve(Stack<Integer> dirStack, int[] xy) {
		Stack<Integer> copyStack = (Stack<Integer>) dirStack.clone();

		while (!copyStack.isEmpty()) {
			// 방향 : 이전세대 방향 역순 + 1 = 다음세대 시작 방향
			int newDir = (copyStack.pop() + 1) % 4;
			xy[0] += dx[newDir];
			xy[1] += dy[newDir];

			maked[xy[0]][xy[1]] = true;
			dirStack.add(newDir);

		}
	}

	public static int checkDragonCurve() {
		int cnt = 0;

		for (int i = 0; i < maked.length - 1; i++) {
			for (int j = 0; j < maked[i].length - 1; j++) {
				if (maked[i][j] && maked[i][j + 1] && maked[i + 1][j] && maked[i + 1][j + 1])
					cnt += 1;
			}
		}

		return cnt;
	}

}
