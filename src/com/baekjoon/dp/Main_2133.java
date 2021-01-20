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
 * @문제 링크 : https://www.acmicpc.net/problem/2133
 * @참고 링크 : https://yabmoons.tistory.com/536
 */
public class Main_2133 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		// N+1로 하면 ArrayIndexOutOfBounds 에러 뜸 왜?
		int[] square = new int[35];
		square[0] = 1;
		square[2] = 3;

		if (N % 2 == 0) {
			// Buttom-Up 방식
			for (int s = 4; s <= N; s += 2) {
				for (int i = 0; i < s; i += 2) {
					if (i == s - 2) {
						// 제일 일반적인 방식 : [n-2] * [2];
						square[s] += square[i] * square[2];
					} else {
						// 일반적인 방식에서 중복되지 않기 위한 방법
						// [나올 수 있는 크기를 만드는 방법] * [나머지 크기에서의 특별한 크기를 반드는 방법 = 2가지]
						square[s] += square[i] * 2;
					}
				}

			}

			answer = square[N];
		}

		System.out.println(answer);

	}

}
