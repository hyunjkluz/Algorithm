/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 15, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2839
 * @참고 풀이 : https://st-lab.tistory.com/72
 */
public class Main_2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = -1;

		if (N != 4 && N != 7) {
			if (N % 5 == 0) {
				count = N / 5;
			} else if ((N % 5 == 1) || (N % 5) == 3) {
				count = (N / 5) + 1;
			} else if (N % 5 == 2 || N % 5 == 4) {
				count = (N / 5) + 2;
			}
		}

		System.out.println(count);

	}

}
