/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 19, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/15486
 */
class Work {
	int t, p;

	public Work(int t, int p) {
		super();
		this.t = t;
		this.p = p;
	}

}

public class Main_15486 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Work[] works = new Work[N + 2];

		for (int i = 1; i <= N; i++) {
			int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			works[i] = new Work(temp[0], temp[1]);
		}
		works[0] = works[works.length - 1] = new Work(0, 0);

		System.out.println(solution(works));
	}

	public static int solution(Work[] works) {
		// N인 날짜에 받을 수 있는 최대 돈
		// 배열의 크기 : N + 1(인덱스 1부터 시작하기 위해) + 1(다음날에 돈 받을 수 있음)
		int[] dp = new int[N + 2];
		// 총 금액 중 최대 값 저장
		int max = Integer.MIN_VALUE;

		// N일까지 일하면 일 한 금액 N + 1일날에 받을 수 있기 때문에 N + 1일까지
		for (int days = 1; days <= N + 1; days++) {
			max = Math.max(max, dp[days]);

			// 해당 날을 일할 수 있으면 그 일이 끝나는 날의 최대 돈을 갱신시켜줌
			int nextDay = works[days].t + days;
			if (nextDay <= N + 1) {
				// 현재 진행할 수 있는 일이 끝나는 날의 돈을 비교해서 갱신
				dp[nextDay] = Math.max(dp[nextDay], works[days].p + max);
			}
		}

		System.out.println(Arrays.toString(dp));

		return max;
	}

}
