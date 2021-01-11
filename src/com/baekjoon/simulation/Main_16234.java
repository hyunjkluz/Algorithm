/**
 * 
 */
package com.baekjoon.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 11, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/16234
 */
public class Main_16234 {
	static int N, L, R;
	static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = info[0];
		L = info[1];
		R = info[2];

		int[][] country = new int[N][N];
		for (int i = 0; i < N; i++) {
			country[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(country));

	}

	public static int solution(int[][] country) {
		// 연합의 번호와 엽합의 평균 인구 값을 저장한 Map
		Map<Integer, Integer> unionMap = new HashMap<Integer, Integer>();
		int count = 0;

		while (true) {
			unionMap.clear();
			// 연합 지도 생성
			int[][] union = openBorder(country, unionMap);

			// 연합이 만들어지지 않고 각자 다 다른 나라로 존재할 때 : 연합의 크기 = 나라의 총 개수
			if (unionMap.size() == N * N) {
				break;
			}

			// 인구 이동
			move(country, union, unionMap);
			count++;

		}

		return count;
	}

	public static int[][] openBorder(int[][] country, Map<Integer, Integer> unionMap) {
		int[][] union = new int[N][N];
		int unionFlag = 0;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (union[x][y] == 0) {
					unionFlag += 1;
					// 국경선 열어서 연합 생성
					int areaAvg = BFS(x, y, country, union, unionFlag, unionMap);
					// 연합 정보 저장
					unionMap.put(unionFlag, areaAvg);
				}
			}
		}

		return union;

	}

	public static int BFS(int x, int y, int[][] country, int[][] union, int unionFlag, Map<Integer, Integer> unionMap) {
		Queue<Point> queue = new LinkedList<Point>();
		int total = 0;
		int count = 1;

		queue.add(new Point(x, y));
		union[x][y] = unionFlag;
		total += country[x][y];

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = current.x + dx[i];
				int newY = current.y + dy[i];

				// 범위 안이고 다른 연합에 들지 않았을 때
				if (isIn(newX, newY) && union[newX][newY] == 0) {
					int diff = Math.abs(country[current.x][current.y] - country[newX][newY]);

					// 인구의 차이가 범위 안에 들 때
					if (L <= diff && diff <= R) {
						// 다음 탐색을 위해 큐에 넣고
						queue.add(new Point(newX, newY));
						// 연합 표시하고
						union[newX][newY] = unionFlag;
						// 평균 인구 수 계산을 위해 더함
						total += country[newX][newY];
						count++;
					}
				}
			}
		}

		return (int) Math.floor(total / count);
	}

	public static void move(int[][] country, int[][] union, Map<Integer, Integer> unionMap) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// 해당 위치에 해당하는 연합 번호에 해당하는 평균 인구 수 입력
				country[x][y] = unionMap.get(union[x][y]);
			}
		}
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;
	}

}
