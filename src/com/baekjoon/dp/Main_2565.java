/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 9, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2565
 */
public class Main_2565 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] wire = new int[N][2];

		for (int i = 0; i < N; i++) {
			wire[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		System.out.println(solution(wire));
	}

	public static int solution(int[][] wire) {
		int[] lens = new int[wire.length];

		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		for (int i = 0; i < wire.length; i++) {
			lens[i] = 1;
			for (int j = 0; j < i; j++) {
				if (wire[j][1] < wire[i][1]) {
					lens[i] = Math.max(lens[i], lens[j] + 1);
				}
			}
		}

		return wire.length - Arrays.stream(lens).max().getAsInt();
	}

}