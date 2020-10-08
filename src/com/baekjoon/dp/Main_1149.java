/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1149
 */
public class Main_1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] colors = new int[T][3];

		for (int i = 0; i < T; i++) {
			colors[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		System.out.println(solution(T, colors));
	}

	public static int solution(int T, int[][] colors) {
		int[][] paints = new int[T][3];

		paints[0] = colors[0];

		for (int i = 1; i < paints.length; i++) {
			paints[i][0] = Math.min(paints[i - 1][1], paints[i - 1][2]) + colors[i][0];
			paints[i][1] = Math.min(paints[i - 1][0], paints[i - 1][2]) + colors[i][1];
			paints[i][2] = Math.min(paints[i - 1][0], paints[i - 1][1]) + colors[i][2];
		}

		return Math.min(Math.min(paints[T - 1][0], paints[T - 1][1]), paints[T - 1][2]);
	}

}
