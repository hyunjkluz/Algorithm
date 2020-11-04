/**
 * 
 */
package com.baekjoon.bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 4, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1300
 */
public class Main_1300 {
	public static int N, K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		K = Integer.parseInt(bf.readLine());
		long left = 1;
		long right = K;

		System.out.println(bSearch(left, right));
	}

	private static long result = 0;

	private static long bSearch(long left, long right) {
		int cnt = 0;
		long mid = (left + right) / 2;
		
		if (left > right)
			return result;
		
		// i : 행을 의미한다, 행마다 해당 mid와 작거나 같은 값의 개수를 더함
		for (int i = 1; i <= N; i++) {
			// 무조건 m/i값이거나 클 수 없음, 크면 배열의 길이 대입
			// 만약 나눈 몫이 n보다 크면 행의 모든 원소가 같거나 작다라는 뜻이므로 모든 원소의 개수인 n을 더해줌
			cnt += Math.min(mid / i, N);
		}

		// 개수가 찾으려하는 배열의 인덱스보다 초과한다 = 범위가 너무 크니깐 범위를 줄여야함
		// 같을 때 = mid
		if (K <= cnt) {
			result = mid;
			return bSearch(left, mid - 1);
		} else {
			// 개수 미달일 때
			return bSearch(mid + 1, right);
		}
	}
}
