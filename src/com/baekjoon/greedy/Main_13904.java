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
 * @CretaedAt : Nov 13, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/13904
 */
public class Main_13904 {
	static int N;

	public static void main(String[] agrs) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] works = new int[N][2];

		for (int i = 0; i < N; i++) {
			works[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		// 과제 기한이 많이 남은 순서대로 정렬
		Arrays.sort(works, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[0], o1[0]);
			}
		});

		System.out.println(solution(works));
	}

	public static int solution(int[][] works) {
		// 과제 기간별 최고 값 넣을 배열
		int[] score = new int[works[0][0] + 1];
		// 해당 과제 수행 여부 확인 배열
		boolean[] did = new boolean[N];
		
		// 제일 늦게까지 할 수 있는 날부터 거꾸로 돌아간다.
		for (int days = score.length - 1; days > 0; days--) {
			int maxScore = -1;
			int maxScoreIdx = -1;

			// N일자 최고 값 : 남은 기간이 N 이상인것들 중에서 최고값 구하기
			for (int j = 0; j < works.length; j++) {
				if (works[j][0] >= days) {
					if (!did[j] && maxScore < works[j][1]) {
						maxScore = works[j][1];
						maxScoreIdx = j;
					}
				} else {
					break;
				}
			}

			// maxScoreIdx == -1 : N일 이상의 아직 하지 않은 과제 없다는 의미
            // maxScoreIdx != -1 : 아직 하지 않은 과제 있음
			if (maxScoreIdx != -1) {
				score[days] = maxScore;
				did[maxScoreIdx] = true;
			}
		}


		return Arrays.stream(score).sum();
	}
}
