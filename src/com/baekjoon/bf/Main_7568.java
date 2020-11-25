/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 25, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/7568
 */
public class Main_7568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] profiles = new int[N][2];

		for (int i = 0; i < N; i++) {
			profiles[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(profiles));

	}

	public static String solution(int[][] profiles) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < profiles.length; i++) {
			int count = 0;

			for (int j = 0; j < profiles.length; j++) {
				if (i != j && profiles[i][0] < profiles[j][0] && profiles[i][1] < profiles[j][1]) {
					count += 1;
				}
			}

			sb.append((count + 1) + " ");
		}

		return sb.toString();
	}

}
