/**
 * 
 */
package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 29, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2217
 */
public class Main_2217 {
	static int maxWeight = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] ropes = new Integer[N];

		for (int i = 0; i < N; i++) {
			ropes[i] = Integer.parseInt(br.readLine());
		}

		solution(ropes);

		System.out.println(maxWeight);
	}

	public static void solution(Integer[] ropes) {

		// 풀이방법 1 : 조합 -> 시간초과
		// 쓸데없는 경우의 수까지 다 고려한다.
		// 어차피 최대 무게를 결정짓는건 제일 작은 중량이기 때문에 작은 중량은 늦게 붙을수록 유리함
		// 풀이방법 2 : 즉, 무게를 내림차순으로 정렬한 후, 하나씩 꺼내면서 연결한다는 가정하게 최대값 갱신

		Arrays.sort(ropes, Collections.reverseOrder());

		for (int i = 0; i < ropes.length; i++) {
			maxWeight = Math.max(maxWeight, ropes[i] * (i + 1));
		}
	}

}
