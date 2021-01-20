/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 20, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/14888
 */
public class Main_14888 {
	static int N;
	static long MAX_VAL = Integer.MIN_VALUE, MIN_VAL = Integer.MAX_VALUE;
	static int[] numbers, operator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 덧셈 - 빽셈 -곱셈 - 나눗셈
		operator = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		backTracking(0, numbers[0]);
		System.out.println(MAX_VAL + "\n" + MIN_VAL);
	}

	public static void backTracking(int cnt, long result) {
		if (cnt == N - 1) {
			MIN_VAL = Math.min(MIN_VAL, result);
			MAX_VAL = Math.max(MAX_VAL, result);
			return;
		}

		// 백트래킹의 대상 = 연산자
		// 계산되는 숫자는 차례대로 고정되어있고 순서가 바뀌어야하는 것은 연산자이기 때문
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i] -= 1;
				backTracking(cnt + 1, calculate(result, numbers[cnt + 1], i));
				operator[i] += 1;
			}
		}
	}

	public static long calculate(long n1, long n2, int op) {
		if (op == 0) {
			return n1 + n2;
		} else if (op == 1) {
			return n1 - n2;
		} else if (op == 2) {
			return n1 * n2;
		} else {
			if (n2 < 0) {
				n2 = Math.abs(n2);
				return (n1 / n2) * -1;
			} else if (n2 == 0) {
				return 0;
			} else {
				return n1 / n2;
			}
		}
	}

}
