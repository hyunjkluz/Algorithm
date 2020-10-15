/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 15, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2667
 */
public class Main_2667 {
	public static int[][] calc = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] village = new int[N][N];

		for (int i = 0; i < N; i++) {
			village[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(village));
	}

	public static String solution(int[][] village) {
		StringBuilder answer = new StringBuilder();
		List<Integer> count = new ArrayList<Integer>();

		for (int i = 0; i < village.length; i++) {
			for (int j = 0; j < village.length; j++) {
				if (village[i][j] == 1) {
					count.add(check(village, i, j));
				}
			}
		}

		count.sort(null);
		answer.append(count.size() + "\n");
		count.stream().forEach(x -> answer.append(x + "\n"));

		return answer.toString();
	}

	public static int check(int[][] village, int x, int y) {
		if (village[x][y] == 0) {
			return 0;
		}

		village[x][y] = 0;

		int count = 1;
		for (int i = 0; i < calc.length; i++) {
			int newX = x + calc[i][0];
			int newY = y + calc[i][1];

			if (0 <= newX && newX < village.length && 0 <= newY && newY < village.length) {
				if (village[newX][newY] == 1) {
					count += check(village, newX, newY);
				}
			}
		}

		return count;
	}

}
