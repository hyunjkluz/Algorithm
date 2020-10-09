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
 * @문제 링크 : https://www.acmicpc.net/problem/11053
 */
public class Main_11053 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed()
				.collect(Collectors.toList());

		if (N >= 1) {
			System.out.println(solution(N, A));
		} else {
			System.out.println(0);
		}

	}

	public static int solution(int N, List<Integer> A) {
		int[] lens = new int[N];
		lens[0] = 1;

		for (int i = 1; i < N; i++) {
			lens[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A.get(j) < A.get(i) && lens[i] <= lens[j]) {
					lens[i] = lens[j] + 1;
				}
			}
		}

		return Arrays.stream(lens).max().getAsInt();
	}

	public static int solution2(int N, List<Integer> A) {
		int answer = 1;
		List<Integer> sortedA = A.stream().sorted().collect(Collectors.toList());

		int min = 0;

		for (int s : sortedA) {
			if (A.indexOf(s) < min) {
				min = A.indexOf(s);
			}
		}

		int in = A.get(min);

		for (int i = min + 1; i < A.size(); i++) {
			if (in < A.get(i)) {
				answer++;
				in = A.get(i);
			}
		}

		return answer;
	}

}
