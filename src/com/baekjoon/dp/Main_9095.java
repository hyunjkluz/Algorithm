/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 7, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/9095
 */
public class Main_9095 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		int[] dict = new int[12];

		dict[0] = 0;
		dict[1] = 1;
		dict[2] = 2;
		dict[3] = 4;

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			answer.append(calc(n, dict) + "\n");
		}

		System.out.print(answer.toString());
	}

	public static int calc(int n, int[] dict) {
		if (dict[n] != 0) {
			return dict[n];
		}

		// dict[n] 
		// = n인 숫자를 만드는 방법
		// = 정수(1, 2, 3) + n-정수인 숫자를 만드는 방법 
		return dict[n] = calc(n - 1, dict) + calc(n - 2, dict) + calc(n - 3, dict);
	}

}
