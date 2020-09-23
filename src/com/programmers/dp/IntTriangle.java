/**
 * 
 */
package com.programmers.dp;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 23, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/43105
 */
public class IntTriangle {

	public static void main(String[] args) {
		int[][] triangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
		System.out.println("Result : " + solution(triangle));
	}

	public static int solution(int[][] triangle) {
		int[][] L = new int[triangle.length][];

		for (int i = 0; i < L.length; i++) {
			L[i] = new int[triangle[i].length];
		}

		return max(triangle, L);
	}

	public static int max(int[][] t, int[][] L) {
		L[0][0] = t[0][0];

		for (int i = 1; i < L.length; i++) {

			for (int j = 0; j < L[i].length; j++) {
				if (j == 0) {
					L[i][j] = t[i][j] + L[i - 1][0];
				} else if (j == L[i].length - 1) {
					L[i][j] = t[i][j] + L[i - 1][j - 1];
				} else {
					L[i][j] = t[i][j] + Math.max(L[i - 1][j - 1], L[i - 1][j]);
				}
			}
		}

		int max = -1;
		for (int i = 0; i < L[L.length - 1].length; i++) {
			max = L[L.length - 1][i] > max ? L[L.length - 1][i] : max;
		}

		return max;
	}

}
