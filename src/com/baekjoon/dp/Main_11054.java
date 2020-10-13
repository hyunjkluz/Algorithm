/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 13, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/11054
 */
public class Main_11054 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		int[] S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(S));
	}

	public static int solution(int[] S) {
		int[] lis = new int[S.length];
		lis[0] = 1;

		for (int i = 1; i < S.length; i++) {
			lis[i] = 1;

			for (int j = 0; j < i; j++) {
				if (S[j] < S[i] && lis[i] <= lis[j]) {
					lis[i] = lis[j] + 1;
				}
			}
		}

		int[] lds = new int[S.length];
		lds[S.length - 1] = 1;

		for (int i = lds.length - 2; i >= 0; i--) {
			lds[i] = 1;

			for (int j = lds.length - 1; j > i; j--) {
				if (S[j] < S[i] && lds[i] <= lds[j]) {
					lds[i] = lds[j] + 1;
				}
			}
		}

		int max = lis[0] + lds[0];
		for (int i = 1; i < S.length; i++) {
			max = max < lis[i] + lds[i] ? lis[i] + lds[i] : max;
		}

		return max - 1;
	}

}
