/**
 * 
 */
package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 14, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1931
 */
public class Main_1931 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] meeting = new int[N][2];

		for (int i = 0; i < N; i++) {
			meeting[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(N > 0 ? solution(meeting) : 0);
	}

	public static int solution(int[][] meeting) {
		int answer = 1;

		Arrays.sort(meeting, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});

		int end = meeting[0][1];

		for (int i = 1; i < meeting.length; i++) {
			if (meeting[i][0] >= end) {
				end = meeting[i][1];
				answer++;
			}
		}

		return answer;
	}

}
