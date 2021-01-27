/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 27, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/20187 종이접기
 */
public class Main_20187 {
	static int K, H;
	static String[] ops;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		ops = br.readLine().split(" ");
		H = Integer.parseInt(br.readLine());

		// 2의 k제곱 크기만큼 배열 생성
		int mapSize = (int) Math.pow(2, K);
		map = new int[mapSize][mapSize];

		// 배열의 맨 끝 지점부터 가르킴
		int mw = mapSize - 1;
		int mh = mapSize - 1;

		// 맨 마지막 구역에 구멍 뚫음
		map[mw][mh] = H;

		// 펼치기
		unfold(mapSize, mh, mw);

		System.out.println(print());
	}

	public static void unfold(int mapSize, int mh, int mw) {
		for (int i = ops.length - 1; i >= 0; i--) {
			char op = ops[i].charAt(0);
			// 새롭게 이동되는 너비/높이 값을 가지고 있는 변수
			int gap = 1;

			switch (op) {
			case 'D':
				// 해당 칸의 반대쪽 값이 위로 생김
				// 가로 선을 기준으로 두고 안쪽부터 점차 바깥쪽으로 펼치면서 바뀌는 값 넣음
				for (int w = mw; w < mapSize; w++) {
					gap = mh - 1;
					for (int h = mh; h < mapSize && gap >= 0; h++, gap--) {
						map[gap][w] = change(map[h][w], op);
					}
				}

				// 위로 늘어났으니까 mh 값 바꾸기
				mh = gap + 1;

				break;
			case 'U':
				// 자신과 동일한 값을 위에 넣고, 원래의 칸은 반대 값을 가짐
				for (int w = mw; w < mapSize; w++) {
					gap = mh - 1;
					for (int h = mh; h < mapSize && gap >= 0; h++, gap--) {
						map[gap][w] = map[h][w];
						map[h][w] = change(map[h][w], op);
					}
				}

				mh = gap + 1;

				break;
			case 'R':
				// 해당 칸의 반대쪽 값이 왼쪽에 생김
				// 세로 선을 기준으로 두고 안쪽부터 점차 바깥쪽으로 펼치면서 바뀌는 값 넣음
				for (int h = mh; h < mapSize; h++) {
					gap = mw - 1;
					for (int w = mw; w < mapSize && gap >= 0; w++, gap--) {
						map[h][gap] = change(map[h][w], op);

					}
				}

				// 왼쪽으로 늘어났으니까 mw 값 바꾸기
				mw = gap + 1;

				break;
			case 'L':
				// 자신과 동일한 겂을 왼쪽에 넣고, 원래의 칸은 반대 값을 가짐
				for (int h = mh; h < mapSize; h++) {
					gap = mw - 1;
					for (int w = mw; w < mapSize && gap >= 0; w++, gap--) {
						map[h][gap] = map[h][w];
						map[h][w] = change(map[h][w], op);

					}
				}

				mw = gap + 1;

				break;
			}
		}

	}

	public static int change(int pos, char op) {
		if (pos == 0) {
			if (op == 'D' || op == 'U') {
				return 2;
			} else {
				return 1;
			}

		} else if (pos == 1) {
			if (op == 'D' || op == 'U') {
				return 3;
			} else {
				return 0;
			}

		} else if (pos == 2) {
			if (op == 'D' || op == 'U') {
				return 0;
			} else {
				return 3;
			}

		} else {
			if (op == 'D' || op == 'U') {
				return 1;
			} else {
				return 2;
			}

		}
	}

	public static String print() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
