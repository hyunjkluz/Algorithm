/**
 * 
 */
package com.programmers.dp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 5, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42898
 */
public class SchoolRoad {
	public static int M, N;
	public static List<Integer> routes = new ArrayList<Integer>();

	public static void main(String[] args) {
		System.out.println(solution(4, 3, new int[][] { { 2, 2 } }));
	}

	public static int solution(int m, int n, int[][] puddles) {
		int[][] roads = new int[m][n];

		for (int[] puddle : puddles) {
			roads[puddle[0] - 1][puddle[1] - 1] = -1;
		}

		roads[0][0] = 1;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (roads[i][j] == -1) {
					roads[i][j] = 0;
					continue;
				}

				if (i != 0)
					roads[i][j] += roads[i - 1][j] % 1000000007;

				if (j != 0)
					roads[i][j] += roads[i][j - 1] % 1000000007;
			}
		}

		return roads[m - 1][n - 1] % 1000000007;
	}

	public static int solution2(int m, int n, int[][] puddles) {
		int answer = 0;
		int[][] roads = new int[n][m];
		M = m;
		N = n;

		for (int[] puddle : puddles) {
			roads[puddle[0] - 1][puddle[1] - 1] = 1;
		}

		findRoad(0, 0, 0, roads);

		int min = routes.stream().min(Integer::compare).get();
		answer = (int) routes.stream().filter(x -> x == min).count();

		return answer % 1000000007;
	}

	public static void findRoad(int cntRoad, int mm, int nn, int[][] roads) {
		if (M - 1 == mm && N - 1 == nn) {
			routes.add(cntRoad);
			return;
		}
		if (mm == M || nn == N || roads[mm][nn] == 1) {
			return;
		}

		cntRoad += 1;
		findRoad(cntRoad, mm + 1, nn, roads);

		findRoad(cntRoad, mm, nn + 1, roads);
	}

}
