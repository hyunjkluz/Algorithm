package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 28, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2798
 */
public class Main_2798 {
	public static int max = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int[] nm = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] cards = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(nm[1], cards));
	}

	public static int solution(int m, int[] cards) {
		p(new int[3], 0, m, cards, new boolean[cards.length]);
		return max;
	}

	public static void p(int[] pick, int cnt, int m, int[] cards, boolean[] visited) {
		if (cnt == 3) {
			int total = Arrays.stream(pick).sum();

			if (total <= m && total > max) {
				max = total;
			}
			return;
		}

		for (int i = 0; i < cards.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				pick[cnt] = cards[i];
				p(pick, cnt + 1, m, cards, visited);
				visited[i] = false;
			}
		}
	}
}
