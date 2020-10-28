/**
 * 
 */
package com.programmers.binarysearch;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 28, 2020
 * @문제 링크 :
 */
public class Immigration {

	public static void main(String[] args) {
		System.out.println(solution(6, new int[] { 10, 7 }));
	}

	/*
	 * 무엇을 이분탐색할 것인가 : 입국 시 걸리는 시간 이분 탐색의 타겟은 ? : 시간 안에 심사하는 사람의 수
	 * 
	 * 시작 : 1초(최적의 경우) / 끝 : 제일 오래 걸리는 사람이 모든 사람을 처리할 때(최악의 경우) 이 중간을 탐색
	 */
	public static long solution(int n, int[] times) {
		Arrays.sort(times);
		long start = 0;
		long end = (long)times[times.length - 1] * n;
		long mid = 0;
		long answer = end;

		while (start <= end) {
			mid = (start + end) / 2;
			long immigration = 0;

			for (int time : times) {
				immigration += mid / time;
			}

			// 목표보다 심사 덜 함 : 시간 부족
			if (immigration < n) {
				start = mid + 1;
			} else {
				// 목표보다 더 많이하거나 목표만큼 함 : 시간 남음
				end = mid - 1;
				answer = Math.min(answer, mid);
			}
		}

		return answer;
	}

}
