/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1904
 */
public class Main_1904 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(solution(N));
	}

	public static int solution(int n) {
		if (n <= 1)
			return 1;

		int[] seq = new int[n + 1];
		seq[0] = 1;
		seq[1] = 1;

		for (int i = 2; i < seq.length; i++) {
			seq[i] = (seq[i - 1] + seq[i - 2]) % 15746;
		}

		return seq[n];
	}

}
